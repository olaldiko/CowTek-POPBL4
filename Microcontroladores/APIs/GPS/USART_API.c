/** @file The USART_API itself */
#ifndef USART_API_C
#define USART_API_C
#include "USART_API.h"


uint8_t usart3_buffer[BUFFERSIZE]; /** The buffer of the USART3. The data received from the USART3 will be saved here. */
uint8_t usart6_buffer[BUFFERSIZE]; /** The buffer of the USART6. The data received from the USART6 will be saved here. */
uint8_t bufflen3 = 0; /** The lenght of the USART3 buffer */
uint8_t bufflen6 = 0; /** The lenght of the USART3 buffer */

func_address_t irqUSART3; /** The function pointer to the function to be executed inside the USART3 IRQ. */
func_address_t irqUSART6; /** The function pointer to the function to be executed inside the USART6 IRQ. */

/******************************************//**
*Initializes USART6. Initializes USART6 with default settings and desired speed.
*@param speed The speed in bauds;
*
********************************************/
void initUSART6(uint16_t speed) {
	enableGPIO_RCC(GPIOC);
	enableGPIO_RCC(GPIOG);
	enableUSART_RCC(USART6);
	confGPIO_USART(USART6);
	confUSART(USART6, speed);
	confUSART_IRQ_RX(USART6);
}

/******************************************//**
*Initializes USART3. Initializes USART3 with default settings and desired speed.
*@param speed The speed in bauds;
*
********************************************/
void initUSART3(uint16_t speed) {
	enableGPIO_RCC(GPIOC);
	enableUSART_RCC(USART3);
	confGPIO_USART(USART3);
	confUSART(USART3, speed);
	confUSART_IRQ_RX(USART3);
	
}

/******************************************//**
*Enables the USART clock on the RCC device.
*@param usart The port to enable.
*
********************************************/
void enableUSART_RCC(USART_TypeDef *usart) {
	
	if(usart == USART6) {
		RCC->APB2ENR |= RCC_APB2ENR_USART6EN;
	}else if(usart == USART3) {
		RCC->APB1ENR |= RCC_APB1ENR_USART3EN;	
	}
}

/******************************************//**
*Enables the GPIOx clock on the RCC device.
*@param gpio The GPIO to enable.
*
********************************************/
void enableGPIO_RCC(GPIO_TypeDef *gpio) {
	if(gpio == GPIOA) {
		RCC->AHB1ENR |= RCC_AHB1ENR_GPIOAEN;
	} else if(gpio == GPIOB) {
		RCC->AHB1ENR |= RCC_AHB1ENR_GPIOBEN;
	} else if(gpio == GPIOC) {
		RCC->AHB1ENR |= RCC_AHB1ENR_GPIOCEN;
	} else if(gpio == GPIOD) {
		RCC->AHB1ENR |= RCC_AHB1ENR_GPIODEN;
	} else if(gpio == GPIOE) {
		RCC->AHB1ENR |= RCC_AHB1ENR_GPIOEEN;
	} else if(gpio == GPIOF) {
		RCC->AHB1ENR |= RCC_AHB1ENR_GPIOFEN;
	} else if(gpio == GPIOG) {
		RCC->AHB1ENR |= RCC_AHB1ENR_GPIOGEN;
	} else if(gpio == GPIOH) {
		RCC->AHB1ENR |= RCC_AHB1ENR_GPIOHEN;
	} else if(gpio == GPIOI) {
		RCC->AHB1ENR |= RCC_AHB1ENR_GPIOIEN;
	}
}

/******************************************//**
*Configures the USART on the GPIO. Configures the USART on the GPIO ports, 
*setting the outputs and their mode. 
* - USART3 Port and Pins:
*	+ TX: PC10 - UEXT Connector Pin 9
*	+ RX: PC11 - UEXT Connector Pin 7
* - USART6 Port and Pins:
*	+ TX: PC6 - UEXT Connector Pin 3
*	+ RX: PG9 - UEXT Connector Pin 4
*@param usart The USART to configure.
*
********************************************/
void confGPIO_USART(USART_TypeDef *usart) {
	if(usart == USART3) {
		GPIOC->MODER |= (GPIO_MODER_ALTERNATE << (USART3_TX_PIN * MODER_SIZE));
		GPIOC->MODER |= (GPIO_MODER_ALTERNATE << (USART3_RX_PIN * MODER_SIZE));
		GPIOC->AFR[1] |= (ALTERNATE_USART3 << (AF_USART3_TX * AF_SIZE));
		GPIOC->AFR[1] |= (ALTERNATE_USART3 << (AF_USART3_RX * AF_SIZE));
	}else if(usart == USART6) {
		GPIOC->MODER |= (GPIO_MODER_ALTERNATE << (USART6_TX_PIN * MODER_SIZE));
		GPIOG->MODER |= (GPIO_MODER_ALTERNATE << (USART6_RX_PIN * MODER_SIZE));
		GPIOC->AFR[0] |= (ALTERNATE_USART6 << (AF_USART6_TX * AF_SIZE));
		GPIOG->AFR[1] |= (ALTERNATE_USART6 << (AF_USART6_RX * AF_SIZE));
	}
}

/******************************************//**
*Enables the RS232_2 port connected to the USART3. 
*Configures the board to send and receive data(no flow control) through the RS232_port from USART3.
*
*
********************************************/
void enableUSART3_RS232Port() {
		enableGPIO_RCC(GPIOD);
		
		GPIOD->MODER |= (GPIO_MODER_ALTERNATE << (USART3_RS232_TX_PIN * MODER_SIZE));
		GPIOD->MODER |= (GPIO_MODER_ALTERNATE << (USART3_RS232_RX_PIN * MODER_SIZE));
		
		GPIOD->AFR[1] |= (ALTERNATE_USART3 << (AF_USART3_RS232_TX * AF_SIZE));
		GPIOD->AFR[1] |= (ALTERNATE_USART3 << (AF_USART3_RS232_RX * AF_SIZE));
}

/******************************************//**
*Configures USART3 or 6 with the desired speed, 8 data bits, 1 stop bit, no parity and in TX and RX mode.
*@param usart The port to configure.
*@param speed The baud rate of the port.
*
********************************************/
void confUSART(USART_TypeDef *usart, uint16_t speed) {
	switch(speed) {
		case 1200: usart->BRR = 0x3415; break; //1200 bps @PCLK 16Mhz
		case 4800: usart->BRR = 0x222E; break; //4800 bps @PCLK 16Mhz
		case 9600: usart->BRR = 0x683; break;  //9600 bps @PCLK 16Mhz
		case 38400: usart->BRR = 0x1A1; break; //36400 bps @PCLK 16Mhz
	}
	usart->CR1 = USART_CR1_UE | USART_CR1_TE | USART_CR1_RE;
}

/******************************************//**
*Sends a string or uint8 array through the UART. 
*@param usart The port to use.
*@param data The address of the array of data to send.
*@param size The size of the array or the number of characters to send.
********************************************/
void transmitString_USART(USART_TypeDef *usart, uint8_t *data, uint8_t size) {
	int i = 0;
	while(i < size) {
		while(( usart->SR & USART_SR_TXE ) == 0 );
		usart->DR = data[i++];
	}
}

/******************************************//**
*Configure usart to generate Interrupts on RX events.
*@param usart The port to configure.
*
********************************************/
void confUSART_IRQ_RX(USART_TypeDef *usart) {
	if(usart == USART3) {
		USART3->CR1 |= USART_CR1_RXNEIE;
		NVIC_EnableIRQ(USART3_IRQn);
	} else if(usart == USART6) {
		USART6->CR1 |= USART_CR1_RXNEIE;
		NVIC_EnableIRQ(USART6_IRQn);
	}
}

/******************************************//**
*Set function to execute on USART3 or 6 IRQs. Sets the function that will be called inside the USART IRQ Handler.
*@param usart The port where the function will be set.
*@param func The function that will be assigned to the IRQ.
*
********************************************/
void setIRQ_USART(USART_TypeDef *usart, func_address_t func) {
	if(usart == USART3) {
		irqUSART3 = func;
	} else if(usart == USART6) {
		irqUSART6 = func;
	}		
}

/******************************************//**
*The USART6 IRQ Handler. This function is called when an interrupt occurs on the USART6. 
*Saves the received data into a buffer for later use, and calls the function that was previously set with setIRQ_USART function.
*
*
********************************************/
void USART6_IRQHandler() {
	if(bufflen6 < BUFFERSIZE) {
	usart6_buffer[bufflen6++] = USART6->DR;
	irqUSART6();
	} else {
	bufflen6 = 0;
	}
	USART6->SR &= ~USART_SR_RXNE;
}

/******************************************//**
*The USART3 IRQ Handler. This function is called when an interrupt occurs on the USART3. 
*Saves the received data into a buffer for later use, and calls the function that was previously set with setIRQ_USART function.
*
********************************************/
void USART3_IRQHandler() {
	if(bufflen3 < BUFFERSIZE) {
	usart3_buffer[bufflen3++] = USART3->DR;
	irqUSART3();
	} else {
	bufflen3 = 0;
	}
	USART3->SR &= ~USART_SR_RXNE;
}

#endif
