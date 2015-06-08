#include "DS18B20_API.h"
#include "stm32f4xx_hal_rcc.h"
#include "stm32f4xx_hal_pwr.h"
#include "stm32f4xx_hal_flash.h"
void DS18_Init(void){

	DS18_GPIOInit();
	DS18_TIMInit();
	
}

void DS18_GPIOInit(){
	aktdesgpio(DS18_PORT, 1);
	//GPIOE->OSPEEDR = (0x01<<DS18_PIN);
	//GPIOE->PUPDR = (0x01<<DS18_PIN);
	setOutputMode(DS18_PORT, DS18_PIN, 1);
	//Output
	GPIOE->MODER |= (1<<(DS18_PIN*2));
	GPIOE->MODER &= ~(2<<(DS18_PIN*2));
	
}
void DS18_TIMInit(void){
	aktTimer();
	setDebugMode(1);
	setUpdateMode(1);
	setOnePulse(1);
}
uint8_t DS18_ReadBit(void){
	uint8_t bit = 0;
	//Output
	GPIOE->MODER |= (1<<(DS18_PIN*2));
	GPIOE->MODER &= ~(2<<(DS18_PIN*2));
	//Low
	GPIOE->BSRRH |= (1<<DS18_PIN);
	delay(3);
	//Input
	GPIOE->MODER &= ~(3<<(DS18_PIN*2));
	delay(10);
	bit = (GPIOE->IDR & (1<<DS18_PIN))>>DS18_PIN;
	delay(53);
	return bit;
}

void DS18_WriteBit(uint8_t bit){
	if(bit & 1){
		//Low
		GPIOE->BSRRH |= (1<<DS18_PIN);
		//Output
		GPIOE->MODER |= (1<<(DS18_PIN*2));
		GPIOE->MODER &= ~(2<<(DS18_PIN*2));
		delay(10);
		//High
		GPIOE->BSRRL |= (1<<DS18_PIN);
		delay(55);
	}else{
		//Low
		GPIOE->BSRRH |= (1<<DS18_PIN);
		//Output
		GPIOE->MODER |= (1<<(DS18_PIN*2));
		GPIOE->MODER &= ~(2<<(DS18_PIN*2));
		delay(65);
		//High
		GPIOE->BSRRL |= (1<<DS18_PIN);
		delay(5);
	}
}

uint8_t DS18_ReadByte(void){
	uint8_t byte = 0;
	uint8_t bitMask;
	for(bitMask = 0x01; bitMask ; bitMask <<= 1){
		if( DS18_ReadBit()) byte |= bitMask;	
	}
	return byte;
}
void DS18_ReadData(uint8_t data[], uint8_t count){
	int i = 0;
	for(i = 0; i < count ;i++){
		data[i] = DS18_ReadByte();
	}
}
void DS18_WriteByte(uint8_t byte){
	uint8_t bitMask;
	for(bitMask = 0x01; bitMask; bitMask <<=1){
		DS18_WriteBit((bitMask & byte)?1:0);
	}
	//Input
	GPIOE->MODER &= ~(3<<(DS18_PIN*2));
	//Low
	GPIOE->BSRRH |= (1<<DS18_PIN);
}
void DS18_convertTemp(void){
	int i = 0;
	if(DS18_Reset() == 1){
		DS18_WriteByte(DS18_CMD_SKIPROM); //Skip ROM
		DS18_WriteByte(DS18_CMD_CONVERT); //Convert temp
		for(i = 0; i <= 12;i++){
		delay(62500);
		}
	}
	
}
DS18_DATA DS18_readSensor(void){
	DS18_DATA sensorData;
	uint8_t data[9];
	DS18_convertTemp();
	if(DS18_Reset() == 1){
		DS18_WriteByte(DS18_CMD_SKIPROM); 
		DS18_WriteByte(DS18_CMD_READSP);
		DS18_ReadData(data, 9);
		sensorData.temp = 0;
		sensorData.temp += (data[0] & 0x01)*0.0625;
		sensorData.temp += ((data[0] & 0x02)>>1)*0.125;
		sensorData.temp += ((data[0] & 0x04)>>2)*0.25;
		sensorData.temp += ((data[0] & 0x08)>>3)*0.5;
		sensorData.temp += ((data[0] & 0x16)>>4)*1;
		sensorData.temp += ((data[0] & 0x32)>>5)*2;
		sensorData.temp += ((data[0] & 0x64)>>6)*4;
		sensorData.temp += ((data[0] & 0x128)>>7)*8;
		sensorData.temp += ((data[1] & 0x01))*16;
		sensorData.temp += ((data[1] & 0x02)>>1)*32;
		sensorData.temp += ((data[1] & 0x04)>>2)*64;
		if((data[1] & 0x08) == 0x08){
			sensorData.temp = -sensorData.temp;
		}
	}
	return sensorData;
}

uint8_t DS18_Reset(void){
	uint8_t presence = 0;
	uint8_t retries = 125;
	//Input
	GPIOE->MODER &= ~(3<<(DS18_PIN*2));
	do {
		if (--retries == 0) return 1;
		delay(2);
	} while (!(GPIOE->IDR & (1<<DS18_PIN)));
	//Low
	GPIOE->ODR &= ~(1<<DS18_PIN);
	//Output
	GPIOE->MODER |= (1<<(DS18_PIN*2));
	GPIOE->MODER &= ~(2<<(DS18_PIN*2));
	delay(480);
	//Input
	GPIOE->MODER &= ~(3<<(DS18_PIN*2));
	delay(70);
	//presence = !((GPIOE->IDR & (1<<DS18_PIN))>>DS18_PIN);
	if((GPIOE->IDR & (1<<DS18_PIN)) == 0){
			presence = 1;
	}
	delay(410);
	
	return presence;
}
