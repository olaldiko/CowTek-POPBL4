#include "DHT11.h"

int main(void){
	DHT_DATA values;
	DHT11_init();
	while(1){
		values = DHT11_readSensor();
		delay(1000);
	}
}
