#include "main.h"
#include "ethernetApi.h"
#include "ethernetApi.h"
#include <stm32f407xx.h>

unsigned char udp_data [] = "hello world!";

int main (void)
{
    
    USART_Configuration ();
    USART_NVIC_Config ();
    
    
    
    //Initialize LCD and Leds
    LCD_LED_Init ();
    
    //Configure ethernet
    ETH_BSP_Config ();
    
    //Initilaize the LwIP stack
    LwIP_Init ();
    
    //UDP echoserver
    Udp_echoserver_init ();
    
    
    //Infinite loop
    while (1)
    {
        udp_send_data (udp_data, sizeof (udp_data));
        delay (100);
        //Check if any packet received
        if (ETH_CheckFrameReceived ())
        {
						//Process received ethernet packet * /
            LwIP_Pkt_Handle (); 
        } 
        //Handle periodic timers for LwIP * / 
        LwIP_Periodic_Handle (localtime); 
    } 
}