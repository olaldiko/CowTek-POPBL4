#include "DHT11.h"
#define PRESC 16
#define DHTPIN 10


uint8_t timeout = 0;

/**************************************//**
*Sensor initialization function. 
*Initializes the TIM and GPIOs and waits the recommended time to start reading the sensor.
*
*******************************************/
void DHT11_init(void){
	int i = 0;
	DHT11_initTimer();
	DHT11_initGPIO();
	for(i = 0; i <= 15; i++){
		delay(65000);
	}
}
/*************************************//**
*Initializes the timer with the propper setup for the sensor.
*
******************************************/
void DHT11_initTimer(void){
	aktTimer();
	setDebugMode(1);
	setTime(PRESC, ARR_VAL);
	setOnePulse(1);
	setUpdateMode(1);	
}
/*************************************//**
*Initializes the GPIO with the propper setup for the sensor.
*
******************************************/
void DHT11_initGPIO(void){
	aktdesgpio(DHTPORT, 1);
	setpinmode(DHTPORT, DHTPIN, 2);
	setOutputMode(DHTPORT, DHTPIN, GPIO_OUT_OD);
	piztupinout(DHTPORT, DHTPIN);
}
/*************************************//**
*Sends preamble to read sensor.
*
******************************************/
void DHT11_sendPreamble(void){
	itzalipinout(DHTPORT, DHTPIN);
	delay(18000);
	piztupinout(DHTPORT, DHTPIN);
	delay(30);
	
}
/*************************************//**
*Reads the raw data from the sensor.
*@param data The array where the data will be written.
******************************************/
void DHT11_readDHTData(uint8_t data[]){
	int i = 0;
	int j = 0;
	setpinmode(DHTPORT, DHTPIN, 1);
	resetCounter();
	setTime(PRESC, ARR_VAL);
	startCounter();
	while((irakurripin(DHTPORT, DHTPIN) == 0) & !isUpdate(KEEP_UPDATE));
	while((irakurripin(DHTPORT, DHTPIN) == 1) & !isUpdate(KEEP_UPDATE));
	for(i = 0; (i < 5) & !isUpdate(CLEAR_UPDATE);i++){
		for(j = 7; (j >=0) & !isUpdate(KEEP_UPDATE);j--){
			data[i] |= (DHT11_readBit()<<j);
		}
	}
	delay(50);
	setpinmode(DHTPORT, DHTPIN, 2);
	piztupinout(DHTPORT, DHTPIN);
}
/*************************************//**
*Reads a single bit from the sensor.
*@return The bit.
******************************************/
int DHT11_readBit(void){
	uint8_t value = 2;
	uint8_t pinState = 0;
	uint8_t oldPinState = 0;
	uint16_t startTime = 0;
	uint16_t endTime = 0;
	while((value > 1) & !isUpdate(KEEP_UPDATE)){
		pinState = irakurripin(DHTPORT, DHTPIN);
		if((pinState == 1) &&(oldPinState == 0)){
			oldPinState = 1;
			startTime = getCounter();
		}else if((pinState == 0)&&(oldPinState == 1)){
				endTime = getCounter();
				oldPinState = 0;
			if((endTime - startTime) > 40){
				value = 1;
			}else{
				value = 0;
			}
		}	
	}
	return value;
}
/*************************************//**
*Reads the temperature and the humidity from the sensor.
*@return The struct that contains the values.
******************************************/
DHT_DATA DHT11_readSensor(void){
	uint8_t data[5] = {0,0,0,0,0};
	DHT_DATA values;
	DHT11_sendPreamble();
	DHT11_readDHTData(data);
	setpinmode(DHTPORT, DHTPIN, 2);
	piztupinout(DHTPORT, DHTPIN);
	
	values.humd = data[0];
	values.temp = data[2];
	return values;
	
}
