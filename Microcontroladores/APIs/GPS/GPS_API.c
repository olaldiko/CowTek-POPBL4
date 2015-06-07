#ifndef GPS_API_C
#define GPS_API_C
#include "GPS_API.h"


#define MSG_SIZE 150

uint8_t gpsMsg[MSG_SIZE];
uint8_t gps_MsgLenght = 0;
uint8_t inMsg = 0;
uint8_t *gps_usart_buffer;
uint8_t *gps_usart_bufflen;
GPS_DATA_TypeDef gpsData;

void GPS_setBuffer(uint8_t *gps_buffer, uint8_t *gps_buffer_length){
	gps_usart_buffer = gps_buffer;
	gps_usart_bufflen = gps_buffer_length;
}
void getMsg_GPS(void){
	if(gps_usart_buffer[*gps_usart_bufflen-1] == '$'){
		inMsg = 1;
		gps_MsgLenght = 0;
	}else if((inMsg == 1) && (gps_usart_buffer[*gps_usart_bufflen-1] != '\n')){
		gpsMsg[gps_MsgLenght++] = gps_usart_buffer[*gps_usart_bufflen-1]; 
	}else if((inMsg == 1) && (gps_usart_buffer[*gps_usart_bufflen-1] == '\n')){
		inMsg = 0;
		gpsMsg[gps_MsgLenght++] = '\n';
		parseData_GPS();
	}
	
}

void parseData_GPS(void){
	if(strcmp(strtok((char *)gpsMsg, ",\n"), "GPGGA") == 0){
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
GPS_Pos getLocation_GPS(void){
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

#endif
