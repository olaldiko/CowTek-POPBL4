#include "DS18B20_API.h"
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
	//setUpdateMode(1);
	setOnePulse(1);
}
uint8_t DS18_ReadBit(void){
	uint8_t bit = 0;
	//Low
	GPIOE->BSRRH |= (1<<DS18_PIN);
	//Output
	GPIOE->MODER |= (1<<(DS18_PIN*2));
	GPIOE->MODER &= ~(2<<(DS18_PIN*2));
	delay(1);
	//High
	//GPIOE->BSRRL |= (1<<DS18_PIN);
	//delay(1);
	//Input
	GPIOE->MODER &= ~(3<<(DS18_PIN*2));
	delay(5);
	bit = (GPIOE->IDR & (1<<DS18_PIN))>>DS18_PIN;
	delay(80);

	return bit;
}

void DS18_WriteBit(uint8_t bit){
	if(bit & 1){
		//Low
		GPIOE->BSRRH |= (1<<DS18_PIN);
		//Output
		GPIOE->MODER |= (1<<(DS18_PIN*2));
		GPIOE->MODER &= ~(2<<(DS18_PIN*2));
		delay(5);
		//High
		GPIOE->BSRRL |= (1<<DS18_PIN);
		delay(80);
	}else{
		//Low
		GPIOE->BSRRH |= (1<<DS18_PIN);
		//Output
		GPIOE->MODER |= (1<<(DS18_PIN*2));
		GPIOE->MODER &= ~(2<<(DS18_PIN*2));
		delay(80);
		//High
		GPIOE->BSRRL |= (1<<DS18_PIN);
		//High
		delay(5);
	}
}

uint8_t DS18_ReadByte(void){
	uint8_t value = 0;
	uint8_t bitMask = 0x01;
	uint8_t i;
	for(i = 0; i < 8; i++){
		if(DS18_ReadBit() == 1){
			value |= bitMask;
		}
		bitMask <<= 1;
	}
	return value;
}
void DS18_ReadData(uint8_t data[], uint8_t count){
	int i = 0;
	for(i = 0; i < count ;i++){
		data[i] = DS18_ReadByte();
	}
}
void DS18_WriteByte(uint8_t byte){
	uint8_t bitMask = 0x01;
	uint8_t i;
	for(i = 0; i < 8;i++){
		if(byte & bitMask){
			DS18_WriteBit(1);
		}else{
			DS18_WriteBit(0);
		}
		bitMask <<= 1;
	}
}
void DS18_convertTemp(void){
	int i = 0;
	if(DS18_Reset() == 1){
		DS18_WriteByte(DS18_CMD_SKIPROM); //Skip ROM
		DS18_WriteByte(DS18_CMD_CONVERT); //Convert temp
		for(i = 0; i <= 20;i++){
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
		DS18_Reset();
		sensorData.temp = 0;
		sensorData.temp += (data[0] & 0x01)*0.0625; 	
		sensorData.temp += ((data[0] & 0x02)>>1)*0.125; 
		sensorData.temp += ((data[0] & 0x04)>>2)*0.25; 	
		sensorData.temp += ((data[0] & 0x08)>>3)*0.5;  	
		sensorData.temp += ((data[0] & 0x10)>>4)*1;    
		sensorData.temp += ((data[0] & 0x20)>>5)*2;   
		sensorData.temp += ((data[0] & 0x40)>>6)*4;   
		sensorData.temp += ((data[0] & 0x80)>>7)*8;   
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
	uint8_t low;
	uint8_t high;
	delay(100);
	//Low
	GPIOE->BSRRH |= (1<<DS18_PIN);
	//Output
	GPIOE->MODER |= (1<<(DS18_PIN*2));
	GPIOE->MODER &= ~(2<<(DS18_PIN*2));
	delay(560);
	GPIOE->BSRRL |= (1<<DS18_PIN);
	delay(80);
	//Input
	GPIOE->MODER &= ~(3<<(DS18_PIN*2));
	low = ((GPIOE->IDR & (1<<DS18_PIN))>>DS18_PIN);
	delay(80);
	high = ((GPIOE->IDR & (1<<DS18_PIN))>>DS18_PIN);
	delay(50);
	if((low == 0) && (high == 1)){
		return 1;
	}else{
		return 0;
	}
}
