///Sloppy_Switches 

int LEDstate = 0;
int buttonval;
int oldbuttonval;

void setup() {
  pinMode(9,INPUT);
  pinMode(10, OUTPUT);
}

 void loop() {
  buttonval = digitalRead(9);
  if((buttonval == LOW) && (oldbuttonval == HIGH)){
    ++LEDstate;
  }
 oldbuttonval = buttonval;
   if(LEDstate >= 2){
    LEDstate = 0;
   }
   if(LEDstate == 1){
    digitalWrite(10,HIGH);
   }else{
    digitalWrite(10,LOW);
   }
 //delay(10);
 }

