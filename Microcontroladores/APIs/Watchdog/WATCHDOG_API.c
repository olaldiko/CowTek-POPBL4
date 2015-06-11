/** @file */
#include "WATCHDOG_API.h"

/*************************************//**
*Starts the downcount of the WatchDog. 
* Writes the start countdown value into the 
* key register and the Watchdog starts the 
* downcount to 0. After this, a reset signal
* will be sent to the microcontroller.
*****************************************/
void WD_Start(void){
	IWDG->KR = WD_START_KR;
}

/*************************************//**
* Reset the countdown. Resets the countdown
* of the watchdog.
*****************************************/
void WD_ResetCounter(void){
	IWDG->KR = WD_RESET_KR;	
}

/*************************************//**
* Set the preescaler divisor of the Watchdog.
* Unlocks the preescaler register by writing 
* 0x5555 to the key register and then writes
* the desired value to the preescaler register.
* - 0 - LSI/4
* - 1 - LSI/8
* - 2 - LSI/16
* - 3 - LSI/32
* - 4 - LSI/64
* - 5 - LSI/128
* - 6 - LSI/256
* @param psc Preescaler divisor
*****************************************/
void WD_SetPreescaler(uint8_t psc){
	if((psc >= 0) && (psc <= 7)){
		IWDG->KR = WD_UNLOCK_KR;
		IWDG->PR = psc;
	}
}


/*************************************//**
* Set debug operation of the WatchDog. Sets
* the behaviour of the Watchdog when entering
* a debug stop.
* + 0. Stop the watchdog.
* + 1. Continue the countdown.
* @param mode The operation mode.
*****************************************/
void WD_SetDebugMode(uint8_t mode){
	if(mode == 1){
		DBGMCU->APB1FZ |= (1<<12); 
	}else{
		DBGMCU->APB1FZ &= ~(1<<12);
	}
}
