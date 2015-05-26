#ifndef ETHERNETAPI_C
#define ETHERNETAPI_C
#include "ethernetApi.h"
#include <stm32f407xx.h>
#include <stm32f4xx_hal.h>
#include <stm32f4xx_hal_cortex.h>
#include <stm32f4xx_hal_gpio.h>
#include <stm32f4xx_hal_eth.h>
#include <stm32f4xx_hal_usart.h>

void USART_Configuration (void)
{
    GPIO_InitTypeDef GPIO_InitStructure;
	  USART_InitTypeDef USART_InitStructure;
    
    
    RCC_AHB1PeriphClockCmd (Open_USART_TX_GPIO_CLK, ENABLE);
    RCC_AHB1PeriphClockCmd (Open_USART_RX_GPIO_CLK, ENABLE);
    
    
# ifdef   USART1_OPEN
    RCC_APB2PeriphClockCmd (Open_USART_CLK, ENABLE);
# else
    RCC_APB1PeriphClockCmd (Open_USART_CLK, ENABLE);
# endif
    
    
    
    
    GPIO_PinAFConfig (Open_USART_TX_GPIO_PORT, Open_USART_TX_SOURCE, Open_USART_TX_AF);
    GPIO_PinAFConfig (Open_USART_RX_GPIO_PORT, Open_USART_RX_SOURCE, Open_USART_RX_AF);
    
	
    //Open_USART_TX -> PA9, Open_USART_RX-PA10
    
		/*
		GPIO_InitStructure.GPIO_Pin = Open_USART_TX_PIN;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AF;
		GPIO_InitStructure.GPIO_OType = GPIO_OType_PP;
		*/
		
    GPIO_InitStructure.GPIO_Pin = Open_USART_TX_PIN;
		
		GPIO_InitStructure.Mode = GPIO_MODE_AF_PP;
		
		GPIO_InitStructure.GPIO_OType = GPIO_OType_PP;
    
    
    GPIO_InitStructure.GPIO_Speed ??= GPIO_Speed_50MHz;
    GPIO_InitStructure.GPIO_PuPd = GPIO_PuPd_UP;
    GPIO_Init (Open_USART_TX_GPIO_PORT, & GPIO_InitStructure);
		
		
		
		
    GPIO_InitStructure.GPIO_Pin = Open_USART_RX_PIN;
    GPIO_InitStructure.GPIO_OType = GPIO_OType_OD;
    GPIO_InitStructure.GPIO_PuPd = GPIO_PuPd_NOPULL;
    GPIO_Init (Open_USART_RX_GPIO_PORT, & GPIO_InitStructure);
    
    
    /*
    USARTx configured as follow:
    - BaudRate = 115200 baud
    - Word Length = 8 Bits
    - One Stop Bit
    - No parity
    - Hardware flow control disabled (RTS and CTS signals)
    - Receive and transmit
    */
    
    
    USART_InitStructure.USART_BaudRate = 115200;
    USART_InitStructure.USART_WordLength = USART_WordLength_8b;
    USART_InitStructure.USART_StopBits = USART_StopBits_1;
    USART_InitStructure.USART_Parity = USART_Parity_No;
    USART_InitStructure.USART_HardwareFlowControl = USART_HardwareFlowControl_None;
    USART_InitStructure.USART_Mode = USART_Mode_Rx | USART_Mode_Tx;
    USART_Init (Open_USART, & USART_InitStructure);
    /*
		Enable the Open_USART Transmit interrupt: this interrupt is generated when the
    Open_USART transmit data register is empty
		*/
    USART_ITConfig (Open_USART, USART_IT_RXNE, ENABLE);
    
    
    USART_Cmd (Open_USART, ENABLE);
    
    
}


void USART_NVIC_Config (void)
{
    NVIC_InitTypeDef NVIC_InitStructure;
    
    
    / * Enable the USARTx Interrupt * /
    NVIC_InitStructure.NVIC_IRQChannel = Open_USART_IRQn;
    NVIC_InitStructure.NVIC_IRQChannelPreemptionPriority = 0;
    NVIC_InitStructure.NVIC_IRQChannelSubPriority = 0;
    NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;
    NVIC_Init (& NVIC_InitStructure);
}

void udp_send_data (uint8_t * pData, uint16_t len)
{
    struct udp_pcb * upcb;
    struct pbuf * buff;
    struct ip_addr ipaddr;
    err_t err;
    
    buff = pbuf_alloc (PBUF_TRANSPORT, 1024, PBUF_ROM);
    buff-> payload = pData; 
    buff-> len = len; 
    buff-> tot_len = len; 
    
    upcb = udp_new () ;// create a new UDP packet 
    udp_bind (upcb, IP_ADDR_ANY, 7); 
    IP4_ADDR (& ipaddr, 192, 168, 1, 11); // Remember, where IP is the IP of the PC, using the PC-cut package software to receive 
    
    
    err = udp_connect (upcb, & ipaddr, 7); 
    
    if (err == ERR_OK) 
    { 
        err = udp_send (upcb, buff); 
        the if (ERR_IS_FATAL (err)) 
            printf ("udp_send ...% d \ r \ n", err); 
    } 
    
    udp_disconnect (upcb); 
    pbuf_free (buff); 
    udp_remove (upcb); 
}