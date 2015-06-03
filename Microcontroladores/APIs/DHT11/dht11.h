#ifndef DHT11_H
#define DHT11_H
#include <stdint.h>
#include "GPIOAPI.h"
#include "TIMERAPI.h"

#define PRESC 16
#define ARR_VAL 60000
#define DHTPIN 10
#define DHTPORT 4

typedef struct{
	float temp;
	float humd;
}DHT_DATA;

void DHT11_init(void);
void DHT11_initTimer(void);
void DHT11_initGPIO(void);
void DHT11_sendPreamble(void);
void DHT11_readDHTData(uint8_t data[]);
int DHT11_readBit(void);
DHT_DATA DHT11_readSensor(void);



#endif
