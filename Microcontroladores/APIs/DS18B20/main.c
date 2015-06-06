#include "DS18B20_API.h"
int main(void){
	DS18_DATA temp;
	DS18_Init();
	while(1){
		temp = DS18_readSensor();
		temp.temp = temp.temp+0.25;
		delay(65000);
		
	}
	
}