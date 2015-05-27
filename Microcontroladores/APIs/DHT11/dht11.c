#include <stdint.h>
#include <time.h>
#include "stm32f4xx.h"
#include "RTE_Components.h" 
#include "stm32f4xx.h"
#include "TIMERAPI.H"
#include "GPIOAPI.H"
#include "dht11.h"
 
uint8_t bGlobalErr;  //Para guardar el codigo de error de vuelta de las funciones
uint8_t data[5];  //Array para almacenar los bytes enviados por el sensor
uint16_t denbora_kont; //timerraren interrupziorako
uint8_t hmax=20,hmin=80,tmax=0,tmin=50; //variables para ir guardando las maximas y minimas de humedad y temperatura

void initDHT(void){
  initGPIO();  //Inicializamos el pin empleado para leer el sensor
	initTimer(); //Inicializar el timer
  delay(1000);  //Este delay es para esperar el tiempo recomendado para acceder al sensor (1 segundo) 
}
 
DHT_DATA getData(void){
  DHT_DATA dhtdata;
	ReadDHT(); // Leemos el sensor y almacenamos los resultados en variables globales
  
  switch (bGlobalErr){
     case 0:
        //Sin decimales
        dhtdata.h=data[0];
        dhtdata.t=data[2];
        break;
     default:
        //error tipo 1
			 dhtdata.h=255;
       dhtdata.t=255;
        break;
  }
	return dhtdata;
}
 
 
// Initilize pin gpio
void initGPIO(void){			
	aktdesgpio(PORTPIN, 1); //piztu gpioa
	setResistorMode(PORTPIN,DHTPIN, 2); //pull-up beharrezkoa 1-wiren
	setpinmode(PORTPIN,DHTPIN, 2); //irteera
	
}
// Inicializar timer
void initTimer(void){
	aktTimer();
	setDebugMode(1);
	setTime(PRESC, ARR);
}
// Leer datos del sensor
void ReadDHT(void){
  uint8_t dht_in;
  uint8_t i;
	uint8_t DHTCHECKSUM;
	bGlobalErr=0;
  // Enviamos el comando "start read and report" al sensor
  // Primero: ponemos a "0" el pin durante 18ms
  itzalipinout(PORTPIN, DHTPIN);
  delay(18);
  //delay(5);//TKB, frm Quine at Arduino forum
  //Segundo: ponemos a "1" el pin durante 40us,enviamos el comando de "start read" al sensor
  piztupinout(PORTPIN, DHTPIN);
  delay(40);
  //Tercero: Cambiamos el pin a modo entrada
  setpinmode(PORTPIN,DHTPIN, 1); //sarrera
  delay(40); //Esperamos 40 us
  dht_in=irakurripin(PORTPIN , DHTPIN);
	
  //si hay un 1 en la lectura del pin, indicamos que hay error de tipo 1
  if(dht_in){
    bGlobalErr=1;
    return;
  }
  
  delay(80); //Esperamos 80us
  dht_in=irakurripin(PORTPIN , DHTPIN);
  
  //si no hay un 1 en la lectura del pin, indicamos que hay error de tipo 2
  if(!dht_in){
    bGlobalErr=2;
    return;
  }
  /*Despues de 40us a 0, el pin deberia de estar durante 80us a 1.
  Despues de esto comienza el envio del primer bit hasta alcanzar los 40 bits enviados.
  Hay que llamar a "read_dht_dat()" cuando este a 0.*/
  
  delay(80); //Esperamos 80us
  //Ahora comienza la recepcion de datos, son 5 bytes de datos, es decir 40 bits, que los almacenamos en un array de 5 bytes
  for (i=0; i<5; i++){
    data[i] = read_data();
	}
  //Cuarto: Volvemos a configurar el pin del como salida
	setpinmode(PORTPIN,DHTPIN, 2); //irteera
  //Quinto:Ponemos a "1" el pin de salida
  piztupinout(PORTPIN, DHTPIN);
  //Comprobamos si los datos recibidos coinciden con el checksum recibido
  //DHTCHECKSUM = data[0]+data[1]+data[2]+data[3]; //si tomamos en cuenta los decimales
	DHTCHECKSUM = data[0]+data[2];
  //Si no coincide el byte recibido de checksum con la suma de los 4 primeros bytes enviamos error tipo 3
  if(data[4]!= DHTCHECKSUM)
    bGlobalErr=3;
  };
 
uint8_t read_data(void)
{
  //Conversion de bits a byte: si recibimos 00000100 , devolvemos en decimal 4
  uint8_t i = 0;
  uint8_t result=0;
  for(i=0; i< 8; i++){
    //Entramos mientras dura el primer bit de start (a 0 durante 50us) del byte
    //Esperamos hasta que el pin se pone a 1 señalizando fin del la transmision del bit de start
    while(irakurripin(PORTPIN , DHTPIN)==1);
    //La linea de datos debera estar ahora a 1 durante 27 o 70us, dependiendo si un "0" o un "1" esta siendo enviado, respectivamente.
    delay(30);  //Esperamos 30 us
    if (irakurripin(PORTPIN , DHTPIN)==1)
      result |=(1<<(7-i));  //Si despues de los 30us el pin permanece a "1" añadimos un 1 al byte, sino queda un "0" 
    //Esperamos hasta que el pin se vuelve a poner a nivel bajo, el cual indica la señal de start del siguiente bit de la transmision
    while (irakurripin(PORTPIN , DHTPIN)==1);
			
  }
  return result;
}

void delay (uint16_t dTime){	
	uint16_t hasierako_counter=getCounter();
	while(getCounter()!=(hasierako_counter+dTime));
}



