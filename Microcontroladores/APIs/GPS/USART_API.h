/** @file Headers of the USART_API */
#include <stm32f4xx.h>
#include <stdint.h>

#ifndef USART_API_H
#define USART_API_H


#define AF_SIZE 4 /**< Size of each pin inside the Alternate Function register. */
#define ALTERNATE_USART3 7 /**< Alternate function corresponding to the USART3. */
#define ALTERNATE_USART6 8 /**< Alternate function corresponding to the USART6. */
#define AF_USART3_TX 2 /**< The position of the USART3 TX pin inside the Alternate Function register. */
#define AF_USART3_RX 3 /**< The position of the USART3 RX pin inside the Alternate Function register. */
#define AF_USART3_RS232_TX 0 /**< The position of the USART3 TX pin for RS232 port inside the Alternate Function register. */
#define AF_USART3_RS232_RX 1 /**< The position of the USART3 RX pin for RS232 port inside the Alternate Function register. */
#define AF_USART6_TX 6 /**< The position of the USART6 TX pin inside the Alternate Function register. */
#define AF_USART6_RX 1 /**< The position of the USART6 RX pin inside the Alternate Function register. */

#define MODER_SIZE 2 /**< Size of each pin inside of the MODER register of the GPIO. */
#define GPIO_MODER_ALTERNATE 2 /**< Set the pin in Alternate function mode in the MODER register. */
#define USART6_TX_PIN 6 /**< PIN of the USART6 TX on the GPIO. */ 
#define USART6_RX_PIN 9 /**< PIN of the USART6 RX on the GPIO. */
#define USART3_RX_PIN 11 /**< PIN of the USART3 TX on the GPIO. */
#define USART3_TX_PIN 10 /**< PIN of the USART3 RX on the GPIO. */
#define USART3_RS232_TX_PIN 8 /**< PIN of the USART3 TX for the RS232 port on the GPIO. */
#define USART3_RS232_RX_PIN 9 /**< PIN of the USART3 RX for the RS232 port on the GPIO. */

#define BUFFERSIZE 300 /**< Size of the buffer for the received data. */


extern uint8_t usart3_buffer[BUFFERSIZE]; /** The buffer of the USART3. The data received from the USART3 will be saved here. */
extern uint8_t usart6_buffer[BUFFERSIZE]; /** The buffer of the USART6. The data received from the USART6 will be saved here. */
extern uint16_t bufflen3; /** The lenght of the USART3 buffer */
extern uint16_t bufflen6; /** The lenght of the USART3 buffer */ 

typedef void (*func_address_t)(void); /**< Void function pointer typeDef */

void USART3_Init(uint16_t speed);
void USART6_Init(uint16_t speed);
void USART_Enable_RCC(USART_TypeDef *usart);
void enableGPIO_RCC(GPIO_TypeDef *gpio);


void USART_ConfGPIO(USART_TypeDef *usart);
void USART3_EnableRS232Port(void);

void USART_Conf(USART_TypeDef *usart, uint16_t speed);
void USART_ConfIRQ_RX(USART_TypeDef *usart);

void USART_setIRQ(USART_TypeDef *usart, func_address_t func);

void USART_transmitString(USART_TypeDef *usart, uint8_t *data, uint8_t size);



#endif
