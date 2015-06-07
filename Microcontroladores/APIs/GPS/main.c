
#include "GPS_API.h"
#include "USART_API.h"
#include <stdint.h>
int main(void){
	int i = 0;
	GPS_Pos position;
	USART3_Init(38400);
	GPS_setBuffer(usart3_buffer, &bufflen3);
	USART_setIRQ(USART3, GPS_getMsg);
	while(1){
	position = GPS_getLocation();
	}	
}
