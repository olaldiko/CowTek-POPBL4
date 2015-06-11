#ifndef GPS_API_H
#define GPS_API_H
#include <stm32f4xx.h>
#include <string.h>
#include <stdlib.h>
#include <stdint.h>
#include "USART_API.h"

#define MSG_SIZE 80 /**< The size of the GPS message buffer */

typedef struct{
	char time[10]; 		/**< UTC time of the last GPS fix. */
	char lat[10]; 		/**< Latitude. */
	char dirV; 			/**< Latitude direction(N/S). */
	char lon[10]; 		/**< Longitude. */
	char dirH; 			/**< Longitude direction(W/E). */
	char quality;		/**< Quality of the fix. */
	char nsat[2];		/**< Number of satellites being tracked. */
	char dilution[3];	/**< Horizontal dilution *//
	char altitude[6];	/**< Altitude above the Mean Sea Level. */
	char height[6];		/**< Height of the mean sea level above WGS84 */
}GPS_DATA_TypeDef;		/**< Struct that will contain all the received GPS data. */

typedef struct{
	float lat;			/**< Latitude. */
	int dirV;			/**< Latitude direction(N/S). */
	float lon;          /**< Longitude. */
	int dirH;           /**< Longitude direction(W/E). */
	int altitude;		/**< Altitude above the Mean Sea Level. */
}GPS_Pos;				/**< Structure that will contain the GPS position. */

extern uint8_t gpsMsg[];				/**< Extern definition of the GPS buffer. */
extern uint8_t gps_MsgLenght;			/**< Extern definition of the GPS buffer length. */
extern uint8_t *gps_usart_buffer;		/**< Extern definition of the USART buffer to use with the GPS. */
extern uint16_t *gps_usart_bufflen;		/**< Extern definition of the length of the USART buffer. */
extern GPS_DATA_TypeDef gpsData;		/**< Extern definition of the GPS Data struct. */


void GPS_setBuffer(uint8_t *gps_buffer, uint16_t *gps_buffer_length);
void GPS_getMsg(void);
void GPS_parseData(void);
GPS_Pos GPS_getLocation(void);

#endif
