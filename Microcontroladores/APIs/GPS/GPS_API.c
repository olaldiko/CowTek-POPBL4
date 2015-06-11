/** @file */
#ifndef GPS_API_C
#define GPS_API_C
#include "GPS_API.h"




uint8_t gpsMsg[MSG_SIZE]; /**< The buffer where the GPS messages will be stored. */
uint8_t gps_MsgLenght = 0; /**< The length of the message buffer. */
uint8_t inMsg = 0; /**< Boolean that indicates if the data received from the USART will be stored on the buffer or not. */
uint8_t *gps_usart_buffer; /**< The pointer to the USART buffer where the GPS messages are being received. */
uint16_t *gps_usart_bufflen; /**< The pointer to the length variable of the USART buffer. */
GPS_DATA_TypeDef gpsData; /**< Struct where the location data will be stored after conversion. */

/***********************************//**
*Set the buffer where the GPS messages are being received. Sets the buffer of the USART
* where the messages are arriving for posterior reading.
* @param gps_buffer The pointer to the buffer.
* @param gps_buffer_length The pointer to the variable that stores the length of the buffer.
***************************************/
void GPS_setBuffer(uint8_t *gps_buffer, uint16_t *gps_buffer_length){
	gps_usart_buffer = gps_buffer;
	gps_usart_bufflen = gps_buffer_length;
}


/***********************************//**
* Read the message from the USART buffer. Reads the last character that has been read by the USART,
* and if it detects that is a GPS message, stores it to the buffer of GPS messages. If detect that the full message has been received,
* it will call the GPS_parseData() function to process it.
* Must be called from the USART IRQ.
* @see USART6_IRQHandler()
* @see USART3_IRQHandler()
* @see GPS_parseData()
***************************************/
void GPS_getMsg(void){
	if(gps_usart_buffer[*gps_usart_bufflen-1] == '$'){ 							/**< Detect the start of message */
		inMsg = 1;
		gps_MsgLenght = 0;
	}else if((inMsg == 1) && (gps_usart_buffer[*gps_usart_bufflen-1] != '\n')){ /**< If inside a message and is not the ending char store the character. */
		gpsMsg[gps_MsgLenght++] = gps_usart_buffer[*gps_usart_bufflen-1]; 
	}else if((inMsg == 1) && (gps_usart_buffer[*gps_usart_bufflen-1] == '\n')){ /**< If inside a message and the received char is the ending char process the message */
		inMsg = 0;
		gpsMsg[gps_MsgLenght++] = '\n';
		GPS_parseData();
	}
	
}


/***********************************//**
* Parse the message and store it in GPS_DATA_TypeDef struct. 
* If the received message is of GPGGA type, splits it into fields and stores it in the GPS_Data struct.
* @see GPS_DATA_TypeDef
***************************************/
void GPS_parseData(void){
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


/***********************************//**
* Get the last stored location. Convert the data in the GPS_Data struct to useful location data. 
* Then stores it in in a GPS_Pos struct.
* @return The struct that contains the location info.
* @see GPS_DATA_TypeDef
* @see GPS_Pos
***************************************/
GPS_Pos GPS_getLocation(void){
	GPS_Pos position;
	if(atoi(&gpsData.quality) == 0){
		position.lat = 0;
		position.lon = 0;
		position.dirH = 0;
		position.dirV = 0;
		position.altitude = 0;
	}else{
		position.lat = atof(&gpsData.lat[0])/10;
		position.lon = atof(&gpsData.lon[0])/10;
		position.dirH = atoi(&gpsData.dirH);
		position.dirV = atoi(&gpsData.dirV);
		position.altitude = atoi(&gpsData.altitude[0]);
	}
	return position;
}

#endif
