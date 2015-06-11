#ifndef SLEEP_API_H
#define SLEEP_API_H
#include <stdint.h>
#include <stm32f4xx.h>
void SL_sleepForSecs(uint16_t secs);
void SL_Init();
void TIM7_IRQHandler();
#endif