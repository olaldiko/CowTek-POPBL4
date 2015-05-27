#ifndef dht11_h
#define dht11_h

#include <stdint.h>
#include <time.h>
#include "stm32f4xx.h"
#include "RTE_Components.h" 
#include "stm32f4xx.h"
#include "TIMERAPI.H"
#include "GPIOAPI.H"

#define PORTPIN 5 //Indicamos el puerto al que conectamos el sensor
#define DHTPIN 2 // Indicamos el pin donde conectamos el sensor
#define PRESC 15
#define ARR 0xFFFF

typedef struct{
	uint8_t t;
	uint8_t h;
}DHT_DATA;

//Sentsorea konektatuko den gpioa hasieratu
void initGPIO(void);
//Timerra hasieratu
void initTimer(void);
//Hasierako konfigurazioa
void initDHT(void);
//Funtzio nagusia
DHT_DATA getData(void);
//Sentsoretik datuak irakurri
void ReadDHT(void);
//bitak bytetara pasatzeko funtzioa
uint8_t read_data(void);
//Delay funtzioa
void delay(uint16_t dTime);

#endif
