#include "main.h"




char msg[40];
uint8_t msgLen = 0;
DHT_DATA dht;
fsm_type state = INIT_S;
int main(void){
	while (1){
		switch(state){
			case INIT_S: initState(); break;
			case IDLE_S: idleState(); break;
			case CAPTURE_S: captureState(); break;
			case SEND_S: sendState(); break;
			case ERROR_S: errState(); break;
		}
		
	}


	
}
void initState(){
	initWatchDog();
	DHT11_init();
	USART6_Init(9600);
	WD_ResetCounter();
	state = IDLE_S;
}


void idleState(){
	int i;
	WD_ResetCounter();
	for(i = 0; i < 200; i++){ 
	delay(65000);
	}
	WD_ResetCounter();
	state = CAPTURE_S;
}
void captureState(){
	disableIRQs();
	dht = DHT11_readSensor();
	enableIRQs();
	state = SEND_S;
}
void sendState(){
	prepareMsg(msg, SEN_TEMP_ID, dht.temp);
	USART_transmitString(USART6, (uint8_t *)msg, msgLen);
	delay(1000);
	prepareMsg(msg, SEN_HUM_ID, dht.humd);
	USART_transmitString(USART6, (uint8_t *)msg, msgLen);
	delay(1000);
	state = IDLE_S;
}
void errState(){
	
}
void initWatchDog(){
	WD_SetPreescaler(7);
	WD_SetDebugMode(1);
	WD_Start();
}
void enableIRQs(){
	NVIC_EnableIRQ(USART6_IRQn);
}
void disableIRQs(){
	NVIC_DisableIRQ(USART6_IRQn);
}
void prepareMsg(char *msg, uint8_t sensor, float value){
	msgLen = sprintf(msg, "$%i%%%i%%%f$\n", BOARD_ID, sensor, value);
}