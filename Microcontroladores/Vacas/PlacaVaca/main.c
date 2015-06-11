#include "main.h"




char msg[40];
uint8_t msgLen = 0;
fsm_type state = INIT_S;
DS18_DATA ds18;
GPS_Pos position;
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
	SL_Init();
	DS18_Init();
	USART3_Init(34800);
	USART6_Init(9600);
	GPS_setBuffer(usart3_buffer, &bufflen3);
	USART_setIRQ(USART3, GPS_getMsg);
	WD_ResetCounter();
	state = IDLE_S;
}


void idleState(){
	int i;
	WD_ResetCounter();
	disableIRQs();
	SL_sleepForSecs(25);
	WD_ResetCounter();
	enableIRQs();
	for(i = 0; i < 30;i++){
		delay(65000);
	}
	WD_ResetCounter();
	state = CAPTURE_S;
}
void captureState(){
	int i;
	disableIRQs();
	ds18 = DS18_readSensor();
	position = GPS_getLocation();
	enableIRQs();
	state = SEND_S;
}
void sendState(){
	prepareMsg(msg, SEN_TEMPC_ID, ds18.temp);
	USART_transmitString(USART6, (uint8_t *)msg, msgLen);
	prepareMsg(msg, SEN_GPS_LAT_ID, position.lat);
	USART_transmitString(USART6, (uint8_t *)msg, msgLen);
	prepareMsg(msg, SEN_GPS_LON_ID, position.lon);
	USART_transmitString(USART6, (uint8_t *)msg, msgLen);
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
	NVIC_EnableIRQ(USART3_IRQn);
	NVIC_EnableIRQ(USART6_IRQn);
}
void disableIRQs(){
	NVIC_DisableIRQ(USART3_IRQn);
	NVIC_DisableIRQ(USART6_IRQn);
}
void prepareMsg(char *msg, uint8_t sensor, float value){
	msgLen = sprintf(msg, "$%i%%%i%%%f$\n", BOARD_ID, sensor, value);
}