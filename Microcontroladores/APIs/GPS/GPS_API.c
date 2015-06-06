#include "USART_API.h"
#include <string.h>
#include <stdlib.h>

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

#define MSG_SIZE 80

uint8_t gpsMsg[MSG_SIZE];
uint8_t gps_MsgLenght = 0;
uint8_t inMsg = 0;
uint8_t *usart_buffer;
uint8_t *usart_bufflen;
GPS_DATA_TypeDef gpsData;

void getMsg_GPS(){
	if(usart_buffer[*usart_bufflen-1] == '$'){
		inMsg = 1;
		gps_MsgLenght = 0;
	}else if((inMsg == 1) && (usart_buffer[*usart_bufflen-1] != '\n')){
		gpsMsg[gps_MsgLenght++] = usart_buffer[*usart_bufflen-1]; 
	}else if((inMsg == 1) && (usart_buffer[*usart_bufflen-1] == '\n')){
		inMsg = 0;
		gpsMsg[gps_MsgLenght++] = '\n';
		parseData_GPS();
	}
	
}

void parseData_GPS(){
	strtok((char *)gpsMsg, ",\n");
	if(strcmp(strtok(NULL, ",\n"), "GPGGA") == 0){
		strcpy(&gpsData.time[0], strtok(NULL, ",\n"));
		strcpy(&gpsData.lat[0], strtok(NULL, ",\n"));
		strcpy(&gpsData.dirV, strtok(NULL, ",\n"));
		strcpy(&gpsData.lon[0], strtok(NULL, ",\n"));
		strcpy(&gpsData.dirH, strtok(NULL, ",\n"));
		strcpy(&gpsData.quality, strtok(NULL, ",\n"));
		strcpy(&gpsData.nsat[0], strtok(NULL, ",\n"));
		strcpy(&gpsData.dilution[0], strtok(NULL, ",\n"));
		strcpy(&gpsData.altitude[0], strtok(NULL, ",\n"));
		strtok(NULL, ",\n");
		strcpy(&gpsData.height[0], strtok(NULL, ",\n"));
		strtok(NULL, ",\n");
	}
}
GPS_Pos getLocation_GPS(){
	GPS_Pos position;
	if(atoi(&gpsData.quality) == 0){
		position.lat = 0;
		position.lon = 0;
		position.dirH = 0;
		position.dirV = 0;
		position.altitude = 0;
	}else{
		position.lat = atof(&gpsData.lat[0]);
		position.lon = atof(&gpsData.lon[0]);
		position.dirH = atoi(&gpsData.dirH);
		position.dirV = atoi(&gpsData.dirV);
		position.altitude = atoi(&gpsData.altitude[0]);
	}
	return position;
}