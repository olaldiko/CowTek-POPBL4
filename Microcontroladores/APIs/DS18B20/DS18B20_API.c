/** @file DS18B20 sensor API main code

#include "DS18B20_API.h"

/*********************************//**
*DS18 sensor init fucntion. Initializes the basics for the usage of the sensor.
*************************************/
void DS18_Init(void){

	DS18_GPIOInit();
	DS18_TIMInit();
	
}
/*********************************//**
*DS18 sensor GPIO init function. 
* Initializes the GPIO port for the usage with the DS18B20 sensor.
*************************************/
void DS18_GPIOInit(){
	aktdesgpio(DS18_PORT, 1);
	//GPIOE->OSPEEDR = (0x01<<DS18_PIN);
	//GPIOE->PUPDR = (0x01<<DS18_PIN);
	setOutputMode(DS18_PORT, DS18_PIN, 1);
	//Output
	GPIOE->MODER |= (1<<(DS18_PIN*2));
	GPIOE->MODER &= ~(2<<(DS18_PIN*2));
	
}
/*********************************//**
*DS18 sensor timer init function. 
* Initializes the TIM6 timer for the usage 
* with the delay function that will be used 
* to control the wait times when using the sensor.
*************************************/
void DS18_TIMInit(void){
	aktTimer();
	setDebugMode(1);
	//setUpdateMode(1);
	setOnePulse(1);
}
/*********************************//**
*Read a bit from the OneWire line. Reads a single bit from the OneWire line and returns it. 
*@return 1-0 the bit the was read.
*************************************/
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
/*********************************//**
*Write a bit to the OneWire line. Writes a single bit in the OneWire line.
*@param bit the bit to send.
*************************************/
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
/*********************************//**
* Read a byte from the OneWire line. Reads a byte from the OneWire line.
* Internally uses the DS18_ReadBit() function.
* @see DS18_ReadBit()
* @return The byte that has been read.
*************************************/
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
/*********************************//**
* Read a data array from the OneWire line. Reads a data array of n size from the OneWire line.
* Internally uses the DS18_ReadByte() function.
* @param data The pointer to the array of data where the data will be written.
* @param count The number of bytes to read.
* @see DS18_ReadByte()
*************************************/
void DS18_ReadData(uint8_t data[], uint8_t count){
	int i = 0;
	for(i = 0; i < count ;i++){
		data[i] = DS18_ReadByte();
	}
}
/*********************************//**
* Write a byte to the OneWire line. Writes a byte to the OneWire line. 
* Internally uses the DS18_WriteBit() function.
* @param byte The byte to write.
* @see DS18_WriteBit();
*************************************/
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
/*********************************//**
* Send the convert temp to all DS18B20 sensors. 
* First sends a reset pulse to the sensors, 
* then writes the skip ROM command and finally the convert command. 
* Waits 750ms to complete the conversion.
* Uses the DS18_WriteByte() and DS18_Reset() functions internally.
* @see DS18_WriteByte()
* @see DS18_Reset()
*************************************/
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
/*********************************//**
* Read the temperature from the DS18B20 sensor. Does the full process of reading the temperature from the sensor.
* First it sends the reset command to the sensor. After that, sends the skip rom and convert temp commands and 
* waits to complete the conversion. Finally reads the scratchpad from the sensor and converts the values to float.
* Internally uses the DS18_Reset(), DS18_WriteByte and DS18_ReadData functions.
* @return The temperature reading of the sensor.
* @see DS18_Reset()
* @see DS18_WriteByte()
* @see DS18_ReadData()
*************************************/
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
/*********************************//**
* Send reset signal to the sensor. 
* Sends the reset signal to the sensor, to prepare it for later commands.
*
*
*************************************/
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
