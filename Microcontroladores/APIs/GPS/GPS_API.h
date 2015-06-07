#ifndef GPS_API_H
#define GPS_API_H
#include <stm32f4xx.h>
#include <string.h>
#include <stdlib.h>
#include <stdint.h>
#include "USART_API.h"

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

void GPS_setBuffer(uint8_t *gps_buffer, uint8_t *gps_buffer_length);
void getMsg_GPS(void);
void parseData_GPS(void);
GPS_Pos getLocation_GPS(void);

#endif
