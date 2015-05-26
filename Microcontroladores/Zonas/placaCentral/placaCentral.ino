#include <Ethernet.h>
#include <SPI.h>
#include <SD.h>
#include <SoftwareSerial.h>
#include <stdio.h>
#include <string.h>


EthernetClient client;

byte mac[] = { 0x1E, 0xAD, 0x1E, 0xE6, 0x1E, 0xED };
byte ip[] = { 192, 168, 0, 10 };
byte server[] = { 192, 168, 0, 13 };
char url[] =  "mondra.olaldiko.mooo.com";
int port = 6000;
String xbeeData;

File file;

SoftwareSerial xbee(2, 3); //RX, TX

void setup() {
  
  Serial.begin(9600); 
  Serial.println("Initiation");
  
  setupEthernet();
  setupXBee();
  //setupSD();
  Serial.println("Setup ended");
}

void setupEthernet() {
  Ethernet.begin(mac);
  
  delay(1000);
  
  // print your local IP address:
  Serial.print("My IP address: ");
  for (byte thisByte = 0; thisByte < 4; thisByte++) {
    // print the value of each byte of the IP address:
    ip[thisByte] = Ethernet.localIP()[thisByte];
    Serial.print(Ethernet.localIP()[thisByte], DEC);
    Serial.print("."); 
  }
  Serial.println("");
}

void setupXBee() {
  xbee.begin(9600);
}

void setupSD() {
  pinMode(10, OUTPUT);
  
  if(!SD.begin(4)) {
    Serial.println("SD initiation failed");
    return;
  }
}

void loop() {
  
  while(connectEthernet() == false){
    delay(5000);
  }
  Serial.println("Sending...");
  sendData("3", "0", "0", "0", "0");
  delay(100);
  sendData("1", "2", "3", "4.0", "0");
  delay(100);
  sendData("4", "0", "0", "0", "0");
  delay(100);
  closeConnection();
  delay(1000);
  Serial.println("Loop...");
  
  /*
  loopXBee();
  delay(1);
  */
}

boolean connectEthernet() {
   switch (client.connect(url, port)) {
    case 1:
     Serial.println("Connection successfull");
     return true;
    break;
    
    case -1:
    Serial.println("Connection timed out");
    break;
    
    case -2:
     Serial.println("Connection invalid server");
    break;
    
    case -3:
     Serial.println("Connection trunkated");
    break;
    
    case -4:
     Serial.println("Connection invalid response");
    break;
    
    default:
      return true;
    break;
   }
   return false;
}

void sendData(String modo, String idPlaca, String idSensor, String valor_1, String valor_2){
  if(modo == "1") client.println("$" + modo + "%" + idPlaca + "%" + idSensor + "%" + valor_1 + "$");
  if(modo == "2") client.println("$" + modo + "%" + idPlaca + "%" + valor_1 + "%" + valor_2 + "$");
  if(modo == "3") client.println("$" + modo + "$");
  if(modo == "4") client.println("$" + modo + "$");
}

void closeConnection(){
  client.stop();
}

boolean writeSD() {
  file = SD.open("data.dat", FILE_WRITE);
  
  if(file) {
    file.println("");
  }
  return true;
}

boolean readSD() {
  
}

void loopXBee(){
  if(xbee.available()) {
    char caracter = xbee.read();
    xbeeData = xbeeData + caracter;
    if(caracter == '$') {
      if(xbeeData != "$") {
        while(!connectEthernet()) delay(100);
        client.println(xbeeData);
        Serial.println(xbeeData);
        xbeeData = "$";
      }
    }
  }
}
