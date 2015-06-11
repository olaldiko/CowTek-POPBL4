#ifndef MAIN_H
#define MAIN_H

#include <stm32f4xx.h>
#include <stdint.h>
#include <stdio.h>
#include "inc/USART_API.h"
#include "inc/DS18B20_API.h"
#include "inc/GPS_API.h"
#include "inc/WATCHDOG_API.h"
#include "inc/SLEEP_API.h"

typedef enum {INIT_S, IDLE_S, CAPTURE_S, SEND_S, ERROR_S}fsm_type;

#define BOARD_ID 20
#define SEN_TEMPC_ID 3
#define SEN_GPS_LAT_ID 4
#define SEN_GPS_LON_ID 5

void initState(void);
void idleState(void);
void captureState(void);
void sendState(void);
void errState(void);
void initWatchDog(void);
void enableIRQs(void);
void disableIRQs(void);
void prepareMsg(char *msg, uint8_t sensor, float value);
#endif