#include "dht11.h"

int main(){
	DHT_DATA data;
	initDHT();
	while(1){
		data=getData();
		delay(60000);
	}
}
