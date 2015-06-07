#ifndef GPS_API_H
#define GPS_API_H
#include <stm32f4xx.h>
#include <string.h>
#include <stdlib.h>
#include <stdint.h>
#include "USART_API.h"

#define MSG_SIZE 150

typedef struct{
	char time[10];
	char lat[10];
	char dirV;
	char lon[10];
	char dirH;
	char quality;
	char nsat[2];
	char dilution[3];
	char altitude[6];
	char height[6];
}GPS_DATA_TypeDef;

typedef struct{
	float lat;
	int dirV;
	float lon;
	int dirH;
	int altitude;
}GPS_Pos;

extern uint8_t gpsMsg[];
extern uint8_t gps_MsgLenght;
extern uint8_t *gps_usart_buffer;
extern uint16_t *gps_usart_bufflen;
extern GPS_DATA_TypeDef gpsData;


void GPS_setBuffer(uint8_t *gps_buffer, uint16_t *gps_buffer_length);
void GPS_getMsg(void);
void GPS_parseData(void);
GPS_Pos GPS_getLocation(void);

#endif
