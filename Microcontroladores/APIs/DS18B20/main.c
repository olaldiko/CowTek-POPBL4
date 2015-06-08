#include "DS18B20_API.h"
int main(void){
	DS18_DATA temp;
	DS18_Init();
	while(1){
		temp = DS18_readSensor();
		delay(65000);
		
	}
	
}