/** @file */
#include "SLEEP_API.h"
/****************************************//**
* Sleep the microcontroller for x seconds. 
* Puts the board in low power sleep mode and waits for the 
* interrupt that will come from TIM7 after the time elapses. 
* @param secs The seconds that the board will be in sleep mode.
********************************************/
void SL_sleepForSecs(uint16_t secs){
	if(secs <=268){
		TIM7->ARR = secs*244;
		TIM7->CR1 |= TIM_CR1_CEN;
		__WFI();
	}
}

/****************************************//**
* Stars the timer needed for the SL_sleepForSecs() function. 
* Starts the timer and sets the preescaler at the max setting.
* It also enables the TIM7 interrupt.
********************************************/
void SL_Init(){
	RCC->APB1ENR |= RCC_APB1ENR_TIM7EN;
	TIM7->PSC = 0xFFFF;
	TIM7->CR1 |= TIM_CR1_OPM;
	TIM7->CR1 |= TIM_CR1_URS;
	TIM7->DIER |= TIM_DIER_UIE;
	NVIC_EnableIRQ(TIM7_IRQn);
}

/****************************************//**
* TIM7 IRQ Handler function. Handles the interrupt generated 
* by the TIM7. In this case, it just clears the interrupt.
********************************************/
void TIM7_IRQHandler(){
	TIM7->SR &= ~TIM_SR_UIF;
	NVIC_ClearPendingIRQ(TIM7_IRQn);
}