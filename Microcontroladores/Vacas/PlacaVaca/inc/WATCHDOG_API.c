#include "WATCHDOG_API.h"

void WD_Start(void){
	IWDG->KR = 0xCCCC;
}
void WD_ResetCounter(void){
	IWDG->KR = 0xAAAA;	
}
void WD_SetPreescaler(uint8_t psc){
	if((psc >= 0) && (psc <= 7)){
		IWDG->KR = 0x5555;
		IWDG->PR = psc;
	}
}
void WD_SetDebugMode(uint8_t mode){
	if(mode == 1){
		DBGMCU->APB1FZ |= (1<<12); 
	}else{
		DBGMCU->APB1FZ &= ~(1<<12);
	}
}
