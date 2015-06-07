
#include "GPS_API.h"
#include "USART_API.h"
#include <stdint.h>
int main(void){
	int i = 0;
	GPS_Pos position;
	initUSART3(38400);
	GPS_setBuffer(usart3_buffer, &bufflen3);
	setIRQ_USART(USART3, getMsg_GPS);
	while(1){
	position = getLocation_GPS();
	}	
}
