#ifndef ETHERNETAPI_H
#define ETHERNETAPI_H
#include <stdint.h>

void USART_Configuration (void);

void USART_NVIC_Config (void);

void udp_send_data (uint8_t * pData, uint16_t len);

#endif
