#ifndef DS18B20_API
#define DS18B20_API
#include <stm32f4xx.h>
#include <stdint.h>
#include "GPIOAPI.h"
#include "TIMERAPI.h"

#define DS18_PORT 4
#define DS18_PIN 8
#define DS18_CMD_SKIPROM 0xCC
#define DS18_CMD_CONVERT 0x44
#define DS18_CMD_READSP 0xBE

typedef struct{
	float temp;
}DS18_DATA;

void DS18_Init(void);
void DS18_GPIOInit();
void DS18_TIMInit(void);
uint8_t DS18_ReadBit(void);
void DS18_WriteBit(uint8_t bit);
uint8_t DS18_ReadByte(void);
void DS18_ReadData(uint8_t data[], uint8_t count);
void DS18_WriteByte(uint8_t byte);
void DS18_convertTemp(void);
DS18_DATA DS18_readSensor(void);
uint8_t DS18_Reset(void);
#endif
