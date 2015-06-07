#ifndef WATCHDOG_API_H
#define WATCHDOG_API_H

#include <stm32f4xx.h>
#include <stdint.h>

#define WD_RESET_KR 0xAAAA
#define WD_START_KR 0xCCCC
#define WD_PRSC_256 7

void WD_Start(void);
void WD_ResetCounter(void);
void WD_SetPreescaler(uint8_t psc);
void WD_SetDebugMode(uint8_t mode);

#endif
