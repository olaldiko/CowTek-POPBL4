#include "USART_API.h"
int main(void){
	uint8_t datos[18] = {88, 66, 69, 66, 32, 70, 85, 67, 75, 32, 89, 69, 65, 72, 33, 33, 33, 13};
	int i = 0;
	//enableUSART3_RS232Port();
	initUSART6(9600);
	
	
	while(1){
		transmitString_USART(USART6, datos, 18);
	}
}
