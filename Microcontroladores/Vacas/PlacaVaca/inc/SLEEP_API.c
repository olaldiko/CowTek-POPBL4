#include "SLEEP_API.h"
void SL_sleepForSecs(uint16_t secs){
	if(secs <=268){
		TIM7->ARR = secs*244;
		TIM7->CR1 |= TIM_CR1_CEN;
		__WFI();
	}
}
void SL_Init(){
	RCC->APB1ENR |= RCC_APB1ENR_TIM7EN;
	TIM7->PSC = 0xFFFF;
	TIM7->CR1 |= TIM_CR1_OPM;
	TIM7->CR1 |= TIM_CR1_URS;
	TIM7->DIER |= TIM_DIER_UIE;
	NVIC_EnableIRQ(TIM7_IRQn);
}
void TIM7_IRQHandler(){
	TIM7->SR &= ~TIM_SR_UIF;
	NVIC_ClearPendingIRQ(TIM7_IRQn);
}