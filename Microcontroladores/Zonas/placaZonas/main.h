/** @file */
#ifndef MAIN_H
#define MAIN_H

#include <stm32f4xx.h>
#include <stdint.h>
#include <stdio.h>
#include "inc/USART_API.h"
#include "inc/dht11.h"
#include "inc/WATCHDOG_API.h"

typedef enum {INIT_S, IDLE_S, CAPTURE_S, SEND_S, ERROR_S}fsm_type; 	/**< Finite state machine possible states enum. */

#define BOARD_ID 1			/**< Board ID. */
#define SEN_TEMP_ID 1       /**< DHT11 temperature sensor ID. */
#define SEN_HUM_ID 2		/**< DHT11 humidity sensor ID. */

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

