### DJ Leamen
### Snakes & Ladders

from cmu_graphics import *

app.stepsPerSecond = 1
app.background = gradient('brown', 'saddleBrown')
timerLabel = Label('Timer: ',30,15,size=20)
timer = Label(45,70,15,size=20)


firstRow = Group(
    Rect(20, 320, 40, 40, fill='lightBlue'),
    Rect(60, 320, 40, 40, fill='blue'),
    Rect(100, 320, 40, 40, fill='lightBlue'),
    Rect(140, 320, 40, 40, fill='blue'),
    Rect(180, 320, 40, 40, fill='lightBlue'),
    Rect(220, 320, 40, 40, fill='blue'),
    Rect(260, 320, 40, 40, fill='lightBlue'),
    Rect(300, 320, 40, 40, fill='blue'),
    Rect(340, 320, 40, 40, fill='lightBlue'),
    )
    
secondRow = Group(
    Rect(20, 280, 40, 40, fill='green'),
    Rect(60, 280, 40, 40, fill='lightGreen'),
    Rect(100, 280, 40, 40, fill='green'),
    Rect(140, 280, 40, 40, fill='lightGreen'),
    Rect(180, 280, 40, 40, fill='green'),
    Rect(220, 280, 40, 40, fill='lightGreen'),
    Rect(260, 280, 40, 40, fill='green'),
    Rect(300, 280, 40, 40, fill='lightGreen'),
    Rect(340, 280, 40, 40, fill='green'),
    )

thirdRow = Group(
    Rect(20, 240, 40, 40, fill='lightBlue'),
    Rect(60, 240, 40, 40, fill='blue'),
    Rect(100, 240, 40, 40, fill='lightBlue'),
    Rect(140, 240, 40, 40, fill='blue'),
    Rect(180, 240, 40, 40, fill='lightBlue'),
    Rect(220, 240, 40, 40, fill='blue'),
    Rect(260, 240, 40, 40, fill='lightBlue'),
    Rect(300, 240, 40, 40, fill='blue'),
    Rect(340, 240, 40, 40, fill='lightBlue'),
    )
    
fourthRow = Group(
    Rect(20, 200, 40, 40, fill='deepPink'),
    Rect(60, 200, 40, 40, fill='lightPink'),
    Rect(100, 200, 40, 40, fill='deepPink'),
    Rect(140, 200, 40, 40, fill='lightPink'),
    Rect(180, 200, 40, 40, fill='deepPink'),
    Rect(220, 200, 40, 40, fill='lightPink'),
    Rect(260, 200, 40, 40, fill='deepPink'),
    Rect(300, 200, 40, 40, fill='lightPink'),
    Rect(340, 200, 40, 40, fill='deepPink')
    )

 
fifthRow = Group(
    Rect(20, 160, 40, 40, fill='lightBlue'),
    Rect(60, 160, 40, 40, fill='blue'),
    Rect(100, 160, 40, 40, fill='lightBlue'),
    Rect(140, 160, 40, 40, fill='blue'),
    Rect(180, 160, 40, 40, fill='lightBlue'),
    Rect(220, 160, 40, 40, fill='blue'),
    Rect(260, 160, 40, 40, fill='lightBlue'),
    Rect(300, 160, 40, 40, fill='blue'),
    Rect(340, 160, 40, 40, fill='lightBlue'),
    )


sixthRow = Group(
    Rect(20, 120, 40, 40, fill='green'),
    Rect(60, 120, 40, 40, fill='lightGreen'),
    Rect(100, 120, 40, 40, fill='green'),
    Rect(140, 120, 40, 40, fill='lightGreen'),
    Rect(180, 120, 40, 40, fill='green'),
    Rect(220, 120, 40, 40, fill='lightGreen'),
    Rect(260, 120, 40, 40, fill='green'),
    Rect(300, 120, 40, 40, fill='lightGreen'),
    Rect(340, 120, 40, 40, fill='green')
    )
   

seventhRow = Group(
    Rect(20, 80, 40, 40, fill='lightBlue'),
    Rect(60, 80, 40, 40, fill='blue'),
    Rect(100, 80, 40, 40, fill='lightBlue'),
    Rect(140, 80, 40, 40, fill='blue'),
    Rect(180, 80, 40, 40, fill='lightBlue'),
    Rect(220, 80, 40, 40, fill='blue'),
    Rect(260, 80, 40, 40, fill='lightBlue'),    
    Rect(300, 80, 40, 40, fill='blue'),
    Rect(340, 80, 40, 40, fill='lightBlue'),
    )
       
       
eighthRow = Group(
    Rect(20, 40, 40, 40, fill='deepPink'),
    Rect(60, 40, 40, 40, fill='lightPink'),
    Rect(100, 40, 40, 40, fill='deepPink'),
    Rect(140, 40, 40, 40, fill='lightPink'),
    Rect(180, 40, 40, 40, fill='deepPink'),
    Rect(220, 40, 40, 40, fill='lightPink'),
    Rect(260, 40, 40, 40, fill='deepPink'),
    Rect(300, 40, 40, 40, fill='lightPink'),
    Rect(340, 40, 40, 40, fill='deepPink'),
    )
    
boardOutline = Rect(20, 40, 360, 320, fill=None, border='black', borderWidth=3)
gameOverLabel = Label('Game over. Play again?', 200, 200, size=30, visible = False)

dieCube =  Rect(190,5,30,30,fill='white', border='black')
die = Label('',205,20,size = 10)
dieDots = Group(
    Circle(205, 15, 3),
    Circle(200, 25, 3),
    Circle(210, 25, 3)
    )

ladders1 = Group(
    Line(60,240,140,125,fill='brown'),
    Line(100,240,180,120,fill='brown'),
    Line(130,140,166,140,fill='brown'),
    Line(110,170,147,170,fill='brown'),
    Line(120,155,157,155,fill='brown'),
    Line(101,183,137,183,fill='brown'),
    Line(90,195,130,195,fill='brown'),
    Line(75,215,115,215,fill='brown'))
    
ladders2 = Group(
    Line(340,160,260,80,fill='brown'),
    Line(380,160,300,80,fill='brown'),
    Line(270,91,310,91,fill='brown'),
    Line(325,104,285,104,fill='brown'),
    Line(300, 122, 340, 122, fill='brown'),
    Line(315, 135, 355, 135, fill='brown'),
    )
    
ladders3 = Group(
    Line(220,360,180,320,fill='brown'),
    Line(260,360,220,320,fill='brown'),
    Line(190,330,230,330,fill='brown'),
    Line(205,345,245,345,fill='brown'))
    
ladders4 = Group(
    Line(220,280,180,240,fill='brown'),
    Line(260,280,220,240,fill='brown'),
    Line(190,250,230,250,fill='brown'),
    Line(205,265,245,265,fill='brown'))
    

snakes = Group(
    Polygon(266, 129, 280, 124, 290, 131, 279, 138, 290, 147, 278, 146, 289, 171, 282, 202, 280, 265, 268, 207, 274, 177, fill='orange', border='black'),
    Circle(273, 131, 3),
    Polygon(70, 47, 88, 51, 93, 54, 81, 62, 92, 65, 80, 66, 90, 91, 81, 148, 71, 126, 73, 104, 65, 54, fill='teal', border='black'),
    Circle(73, 55, 3),
    Polygon(146, 214, 157, 206, 172, 213, 169, 221, 155, 222,164, 227, 139, 240,125, 268, 124, 302,108, 264, 143, 222, fill='darkRed', border='black'),
    Circle(155, 213, 3),
    Polygon(188, 96, 193, 87, 209, 87, 209, 92, 201, 97, 208, 102, 198, 103, 204, 190, 195, 168, fill='darkOliveGreen', border='black'),
    Circle(193, 92, 3),
    Polygon(348, 254, 353, 246, 367, 246, 370, 255, 359, 257, 365, 263, 356, 262, 364, 283, 355, 306, fill='hotPink', border='black'),
    Circle(354, 252, 3)
    )

player = Circle(40,340,10,fill='red', border='white', borderWidth=3)


def onMousePress(mouseX,mouseY):
    number.value = randrange(1,7)
    if(number.value == 1):
        player.centerX += 40

    if(number.value == 2):
        player.centerX += 80
    
def onMousePress(mouseX, mouseY):
    die.value = randrange(1, 7)
    die.size = 20
    dieDots.visible = False
    
    if gameOverLabel.visible == True:
        die.value = ''
        player.centerX = 40
        player.centerY = 340
        app.background = gradient('brown', 'saddleBrown')
        firstRow.visible = True
        secondRow.visible = True
        thirdRow.visible = True
        fourthRow.visible = True
        fifthRow.visible = True
        sixthRow.visible = True
        seventhRow.visible = True
        eighthRow.visible = True
        player.visible = True
        dieCube.visible = True
        die.visible = True
        boardOutline.visible = True
        snakes.visible = True
        ladders1.visible = True
        ladders2.visible = True
        ladders3.visible = True
        ladders4.visible = True
        gameOverLabel.visible = False
        timerLabel.visible = True
        timer.visible = True
        dieDots.visible = True
        timer.value = 45
    
def onStep():
    
    # timer decrease
    timer.value -= 1
    
    # snake wiggle
    for snake in snakes.children:
        snake.rotateAngle += 5
        if snake.rotateAngle == 5:
            snake.rotateAngle -= 10
    
# FIRST ROW
    # ROLL A 1  ROW 1
    if die.value == 1 and player.hitsShape(firstRow) == True:
        if player.centerX == 360 and player.centerY == 340:
            player.centerY -= 40
        else:
            player.centerX += 40
        die.value = ''
        
    # ROLL A 2  ROW 1
    if die.value == 2 and player.hitsShape(firstRow) == True:
        if player.centerX == 360 and player.centerY == 340:
            player.centerY -= 40
            player.centerX -= 40
            
        elif player.centerX == 320 and player.centerY == 340:
            player.centerX += 40
            player.centerY -= 40
            
        else:
            player.centerX += 80
        die.value = ''
    
    # ROLL A 3  ROW 1
    if die.value == 3 and player.hitsShape(firstRow) == True:
        if player.centerX == 360 and player.centerY == 340:
            player.centerX = 280
            player.centerY = 300
            
        elif player.centerX == 320 and player.centerY == 340:
            player.centerX = 320
            player.centerY = 300
            
        elif player.centerX == 280 and player.centerY == 340:
            player.centerX = 360
            player.centerY = 300
            
        else:
            player.centerX += 120
        die.value = ''
    
    # ROLL A 4  ROW 1
    if die.value == 4 and player.hitsShape(firstRow) == True:
        if player.centerX == 360 and player.centerY == 340:
            player.centerX = 240
            player.centerY = 300
            
        elif player.centerX == 320 and player.centerY == 340:
            player.centerX = 280
            player.centerY = 300
            
        elif player.centerX == 280 and player.centerY == 340:
            player.centerX = 320
            player.centerY = 300
            
        else:
            player.centerX += 160
        die.value = ''
    
    # ROLL A 5  ROW 1  
    if die.value == 5 and player.hitsShape(firstRow) == True:
        if player.centerX == 360 and player.centerY == 340:
            player.centerX = 200
            player.centerY = 300
            
        elif player.centerX == 320 and player.centerY == 340:
            player.centerX = 240
            player.centerY = 300
            
        elif player.centerX == 280 and player.centerY == 340:
            player.centerX = 280
            player.centerY = 300
            
        elif player.centerX == 200 and player.centerY == 340:
            player.centerX = 360
            player.centerY = 300
            
        else:
            player.centerX += 200
        die.value = ''  
        
    # ROLL A 6  ROW 1
    if die.value == 6 and player.hitsShape(firstRow) == True:
        if player.centerX == 360 and player.centerY == 340:
            player.centerX = 160
            player.centerY = 300
            
        elif player.centerX == 320 and player.centerY == 340:
            player.centerX = 200
            player.centerY = 300
            
        elif player.centerX == 280 and player.centerY == 340:
            player.centerX = 240
            player.centerY = 300
            
        elif player.centerX == 200 and player.centerY == 340:
            player.centerX = 320
            player.centerY = 300
            
        elif player.centerX == 160 and player.centerY == 340:
            player.centerX = 360
            player.centerY = 300
            
        else:
            player.centerX += 240
        die.value = ''  
        
# SECOND ROW
    # ROLL A 1  ROW 2
    if die.value == 1 and player.hitsShape(secondRow) == True:
        if player.centerX == 40 and player.centerY == 300:
            player.centerY -= 40
        else:
            player.centerX -= 40
        die.value = ''
    
    # ROLL A 2  ROW 2
    if die.value == 2 and player.hitsShape(secondRow) == True:
        if player.centerX == 40 and player.centerY == 300:
            player.centerY -= 40
            player.centerX += 40
            
        elif player.centerX == 80 and player.centerY == 300:
            player.centerX = 40
            player.centerY = 260
            
        else:
            player.centerX -= 80
        die.value = ''
        
    # ROLL A 3  ROW 2
    if die.value == 3 and player.hitsShape(secondRow) == True:
        if player.centerX == 40 and player.centerY == 300:
            player.centerY -= 40
            player.centerX += 80
            
        elif player.centerX == 80 and player.centerY == 300:
            player.centerY -= 40
            
        elif player.centerX == 120 and player.centerY == 300:
            player.centerX = 40
            player.centerY = 260
            
        else:
            player.centerX -= 120
        die.value = ''    
        
    # ROLL A 4  ROW 2
    if die.value == 4 and player.hitsShape(secondRow) == True:
        if player.centerX == 40 and player.centerY == 300:
            player.centerX = 160
            player.centerY = 260
            
        elif player.centerX == 80 and player.centerY == 300:
            player.centerX = 120
            player.centerY = 260
            
        elif player.centerX == 120 and player.centerY == 300:
            player.centerX = 80
            player.centerY = 260
            
        elif player.centerX == 160 and player.centerY == 300:
            player.centerX = 40
            player.centerY = 260
            
        else:
            player.centerX -= 160
        die.value = ''
    
    # ROLL A 5  ROW 2
    if die.value == 5 and player.hitsShape(secondRow) == True:
        if player.centerX == 40 and player.centerY == 300:
            player.centerX = 200
            player.centerY = 260
            
        elif player.centerX == 80 and player.centerY == 300:
            player.centerX = 160
            player.centerY = 260
            
        elif player.centerX == 120 and player.centerY == 300:
            player.centerX = 120
            player.centerY = 260
            
        elif player.centerX == 160 and player.centerY == 300:
            player.centerX = 80
            player.centerY = 260
            
        elif player.centerX == 200 and player.centerY == 300:
            player.centerX = 40
            player.centerY = 260
            
        else:
            player.centerX -= 200
        die.value = ''
    
    # ROLL A 6  ROW 2
    if die.value == 6 and player.hitsShape(secondRow) == True:
        if player.centerX == 40 and player.centerY == 300:
            player.centerX = 240
            player.centerY = 260
            
        elif player.centerX == 80 and player.centerY == 300:
            player.centerX = 200
            player.centerY = 260
            
        elif player.centerX == 120 and player.centerY == 300:
            player.centerX = 160
            player.centerY = 260
            
        elif player.centerX == 160 and player.centerY == 300:
            player.centerX = 120
            player.centerY = 260
            
        elif player.centerX == 200 and player.centerY == 300:
            player.centerX = 80
            player.centerY = 260
            
        elif player.centerX == 240 and player.centerY == 300:
            player.centerX = 40
            player.centerY = 260
            
        else:
            player.centerX -= 240
        die.value = ''
        
# THIRD ROW
    # ROLL A 1  ROW 3
    if die.value == 1 and player.hitsShape(thirdRow) == True:
        if player.centerX == 360 and player.centerY == 260:
            player.centerY -= 40
        else:
            player.centerX += 40
        die.value = ''
    
    # ROLL A 2  ROW 3
    if die.value == 2 and player.hitsShape(thirdRow) == True:
        if player.centerX == 360 and player.centerY == 260:
            player.centerY -= 40
            player.centerX -= 40
            
        elif player.centerX == 320 and player.centerY == 260:
            player.centerX = 360
            player.centerY = 220
            
        else:
            player.centerX += 80
        die.value = ''
        
    # ROLL A 3  ROW 3
    if die.value == 3 and player.hitsShape(thirdRow) == True:
        if player.centerX == 360 and player.centerY == 260:
            player.centerX = 280
            player.centerY = 220
            
        elif player.centerX == 320 and player.centerY == 260:
            player.centerY -= 40
            
        elif player.centerX == 280 and player.centerY == 260:
            player.centerX = 360
            player.centerY = 220
            
        else:
            player.centerX += 120
        die.value = ''    
        
    # ROLL A 4  ROW 3
    if die.value == 4 and player.hitsShape(thirdRow) == True:
        if player.centerX == 360 and player.centerY == 260:
            player.centerX = 240
            player.centerY = 220
            
        elif player.centerX == 320 and player.centerY == 260:
            player.centerX = 280
            player.centerY = 220
            
        elif player.centerX == 280 and player.centerY == 260:
            player.centerX = 320
            player.centerY = 220
            
        else:
            player.centerX += 160
        die.value = ''
    
    # ROLL A 5  ROW 3
    if die.value == 5 and player.hitsShape(thirdRow) == True:
        if player.centerX == 360 and player.centerY == 260:
            player.centerX = 200
            player.centerY = 220
            
        elif player.centerX == 320 and player.centerY == 260:
            player.centerX = 240
            player.centerY = 220
            
        elif player.centerX == 280 and player.centerY == 260:
            player.centerX = 280
            player.centerY = 220
            
        elif player.centerX == 200 and player.centerY == 260:
            player.centerX = 360
            player.centerY = 220
            
        else:
            player.centerX += 200
        die.value = ''
    
    # ROLL A 6  ROW 3
    if die.value == 6 and player.hitsShape(thirdRow) == True:
        if player.centerX == 360 and player.centerY == 260:
            player.centerX = 160
            player.centerY = 220
            
        elif player.centerX == 320 and player.centerY == 260:
            player.centerX = 200
            player.centerY = 220
            
        elif player.centerX == 280 and player.centerY == 260:
            player.centerX = 240
            player.centerY = 220
            
        elif player.centerX == 200 and player.centerY == 260:
            player.centerX = 320
            player.centerY = 220
            
        elif player.centerX == 160 and player.centerY == 260:
            player.centerX = 360
            player.centerY = 220
            
        else:
            player.centerX += 240
        die.value = ''
        
# FOURTH ROW
    # ROLL A 1  ROW 4
    if die.value == 1 and player.hitsShape(fourthRow) == True:
        if player.centerX == 40 and player.centerY == 220:
            player.centerY -= 40
        else:
            player.centerX -= 40
        die.value = ''
    
    # ROLL A 2  ROW 4
    if die.value == 2 and player.hitsShape(fourthRow) == True:
        if player.centerX == 40 and player.centerY == 220:
            player.centerY -= 40
            player.centerX += 40
            
        else:
            player.centerX -= 80
        die.value = ''
        
    # ROLL A 3  ROW 4
    if die.value == 3 and player.hitsShape(fourthRow) == True:
        if player.centerX == 40 and player.centerY == 220:
            player.centerY -= 40
            player.centerX += 80
            
        elif player.centerX == 120 and player.centerY == 220:
            player.centerX = 40
            player.centerY = 180
            
        else:
            player.centerX -= 120
        die.value = ''    
        
    # ROLL A 4  ROW 4
    if die.value == 4 and player.hitsShape(fourthRow) == True:
        if player.centerX == 40 and player.centerY == 220:
            player.centerX = 160
            player.centerY = 180
            
        elif player.centerX == 120 and player.centerY == 220:
            player.centerX = 80
            player.centerY = 180
            
        else:
            player.centerX -= 160
        die.value = ''
    
    # ROLL A 5  ROW 4
    if die.value == 5 and player.hitsShape(fourthRow) == True:
        if player.centerX == 40 and player.centerY == 220:
            player.centerX = 200
            player.centerY = 180
            
        elif player.centerX == 120 and player.centerY == 220:
            player.centerX = 120
            player.centerY = 180
            
        elif player.centerX == 200 and player.centerY == 220:
            player.centerX = 40
            player.centerY = 180
            
        else:
            player.centerX -= 200
        die.value = ''
    
    # ROLL A 6  ROW 4
    if die.value == 6 and player.hitsShape(fourthRow) == True:
        if player.centerX == 40 and player.centerY == 220:
            player.centerX = 240
            player.centerY = 180
            
        elif player.centerX == 120 and player.centerY == 220:
            player.centerX = 160
            player.centerY = 180
            
        elif player.centerX == 200 and player.centerY == 220:
            player.centerX = 80
            player.centerY = 180
            
        elif player.centerX == 240 and player.centerY == 220:
            player.centerX = 40
            player.centerY = 180
            
        else:
            player.centerX -= 240
        die.value = ''
        
# FIFTH ROW
    # ROLL A 1  ROW 5
    if die.value == 1 and player.hitsShape(fifthRow) == True:
        if player.centerX == 360 and player.centerY == 180:
            player.centerY -= 40
        else:
            player.centerX += 40
        die.value = ''
    
    # ROLL A 2  ROW 5
    if die.value == 2 and player.hitsShape(fifthRow) == True:
        if player.centerX == 360 and player.centerY == 180:
            player.centerY -= 40
            player.centerX -= 40
            
        elif player.centerX == 320 and player.centerY == 180:
            player.centerX = 360
            player.centerY = 140
            
        else:
            player.centerX += 80
        die.value = ''
        
    # ROLL A 3  ROW 5
    if die.value == 3 and player.hitsShape(fifthRow) == True:
        if player.centerX == 360 and player.centerY == 180:
            player.centerX = 280
            player.centerY = 140
            
        elif player.centerX == 320 and player.centerY == 180:
            player.centerY -= 40
            
        elif player.centerX == 280 and player.centerY == 180:
            player.centerX = 360
            player.centerY = 140
            
        else:
            player.centerX += 120
        die.value = ''    
        
    # ROLL A 4  ROW 5
    if die.value == 4 and player.hitsShape(fifthRow) == True:
        if player.centerX == 360 and player.centerY == 180:
            player.centerX = 240
            player.centerY = 140
            
        elif player.centerX == 320 and player.centerY == 180:
            player.centerX = 280
            player.centerY = 140
            
        elif player.centerX == 280 and player.centerY == 180:
            player.centerX = 320
            player.centerY = 140
            
        elif player.centerX == 240 and player.centerY == 180:
            player.centerX = 360
            player.centerY = 140
            
        else:
            player.centerX += 160
        die.value = ''
    
    # ROLL A 5  ROW 5
    if die.value == 5 and player.hitsShape(fifthRow) == True:
        if player.centerX == 360 and player.centerY == 180:
            player.centerX = 200
            player.centerY = 140
            
        elif player.centerX == 320 and player.centerY == 180:
            player.centerX = 240
            player.centerY = 140
            
        elif player.centerX == 280 and player.centerY == 180:
            player.centerX = 280
            player.centerY = 140
            
        elif player.centerX == 240 and player.centerY == 180:
            player.centerX = 320
            player.centerY = 140
            
        elif player.centerX == 200 and player.centerY == 180:
            player.centerX = 360
            player.centerY = 140
            
        else:
            player.centerX += 200
        die.value = ''
    
    # ROLL A 6  ROW 5
    if die.value == 6 and player.hitsShape(fifthRow) == True:
        if player.centerX == 360 and player.centerY == 180:
            player.centerX = 160
            player.centerY = 140
            
        elif player.centerX == 320 and player.centerY == 180:
            player.centerX = 200
            player.centerY = 140
            
        elif player.centerX == 280 and player.centerY == 180:
            player.centerX = 240
            player.centerY = 140
            
        elif player.centerX == 240 and player.centerY == 180:
            player.centerX = 280
            player.centerY = 140
            
        elif player.centerX == 200 and player.centerY == 180:
            player.centerX = 320
            player.centerY = 140
            
        elif player.centerX == 160 and player.centerY == 180:
            player.centerX = 360
            player.centerY = 140
            
        else:
            player.centerX += 240
        die.value = ''
        
# SIXTH ROW
    # ROLL A 1  ROW 6
    if die.value == 1 and player.hitsShape(sixthRow) == True:
        if player.centerX == 40 and player.centerY == 140:
            player.centerY -= 40
        else:
            player.centerX -= 40
        die.value = ''
    
    # ROLL A 2  ROW 6
    if die.value == 2 and player.hitsShape(sixthRow) == True:
        if player.centerX == 40 and player.centerY == 140:
            player.centerY -= 40
            player.centerX += 40
            
        elif player.centerX == 80 and player.centerY == 140:
            player.centerX = 40
            player.centerY = 100
            
        else:
            player.centerX -= 80
        die.value = ''
        
    # ROLL A 3  ROW 6
    if die.value == 3 and player.hitsShape(sixthRow) == True:
        if player.centerX == 40 and player.centerY == 140:
            player.centerY -= 40
            player.centerX += 80
            
        elif player.centerX == 80 and player.centerY == 140:
            player.centerY -= 40
            
        elif player.centerX == 120 and player.centerY == 140:
            player.centerX = 40
            player.centerY = 100
            
        else:
            player.centerX -= 120
        die.value = ''    
        
    # ROLL A 4  ROW 6
    if die.value == 4 and player.hitsShape(sixthRow) == True:
        if player.centerX == 40 and player.centerY == 140:
            player.centerX = 160
            player.centerY = 100
            
        elif player.centerX == 80 and player.centerY == 140:
            player.centerX = 120
            player.centerY = 100
            
        elif player.centerX == 120 and player.centerY == 140:
            player.centerX = 80
            player.centerY = 100
            
        elif player.centerX == 160 and player.centerY == 140:
            player.centerX = 40
            player.centerY = 100
            
        else:
            player.centerX -= 160
        die.value = ''
    
    # ROLL A 5  ROW 6
    if die.value == 5 and player.hitsShape(sixthRow) == True:
        if player.centerX == 40 and player.centerY == 140:
            player.centerX = 200
            player.centerY = 100
            
        elif player.centerX == 80 and player.centerY == 140:
            player.centerX = 160
            player.centerY = 100
            
        elif player.centerX == 120 and player.centerY == 140:
            player.centerX = 120
            player.centerY = 100
            
        elif player.centerX == 160 and player.centerY == 140:
            player.centerX = 80
            player.centerY = 100
            
        elif player.centerX == 200 and player.centerY == 140:
            player.centerX = 40
            player.centerY = 100
            
        else:
            player.centerX -= 200
        die.value = ''
    
    # ROLL A 6  ROW 6
    if die.value == 6 and player.hitsShape(sixthRow) == True:
        if player.centerX == 40 and player.centerY == 140:
            player.centerX = 240
            player.centerY = 100
            
        elif player.centerX == 80 and player.centerY == 140:
            player.centerX = 200
            player.centerY = 100
            
        elif player.centerX == 120 and player.centerY == 140:
            player.centerX = 160
            player.centerY = 100
            
        elif player.centerX == 160 and player.centerY == 140:
            player.centerX = 120
            player.centerY = 100
            
        elif player.centerX == 200 and player.centerY == 140:
            player.centerX = 80
            player.centerY = 100
            
        elif player.centerX == 240 and player.centerY == 140:
            player.centerX = 40
            player.centerY = 100
            
        else:
            player.centerX -= 240
        die.value = ''

# SEVENTH ROW
    # ROLL A 1  ROW 7
    if die.value == 1 and player.hitsShape(seventhRow) == True:
        if player.centerX == 360 and player.centerY == 100:
            player.centerY -= 40
        else:
            player.centerX += 40
        die.value = ''
    
    # ROLL A 2  ROW 7
    if die.value == 2 and player.hitsShape(seventhRow) == True:
        if player.centerX == 360 and player.centerY == 100:
            player.centerY -= 40
            player.centerX -= 40
            
        elif player.centerX == 320 and player.centerY == 100:
            player.centerX = 360
            player.centerY = 60
            
        else:
            player.centerX += 80
        die.value = ''
        
    # ROLL A 3  ROW 7
    if die.value == 3 and player.hitsShape(seventhRow) == True:
        if player.centerX == 360 and player.centerY == 100:
            player.centerX = 280
            player.centerY = 60
            
        elif player.centerX == 320 and player.centerY == 100:
            player.centerY -= 40
            
        elif player.centerX == 280 and player.centerY == 100:
            player.centerX = 360
            player.centerY = 60
            
        else:
            player.centerX += 120
        die.value = ''    
        
    # ROLL A 4  ROW 7
    if die.value == 4 and player.hitsShape(seventhRow) == True:
        if player.centerX == 360 and player.centerY == 100:
            player.centerX = 240
            player.centerY = 60
            
        elif player.centerX == 320 and player.centerY == 100:
            player.centerX = 280
            player.centerY = 60
            
        elif player.centerX == 280 and player.centerY == 100:
            player.centerX = 320
            player.centerY = 60
            
        elif player.centerX == 240 and player.centerY == 100:
            player.centerX = 360
            player.centerY = 60
            
        else:
            player.centerX += 160
        die.value = ''
    
    # ROLL A 5  ROW 7
    if die.value == 5 and player.hitsShape(seventhRow) == True:
        if player.centerX == 360 and player.centerY == 100:
            player.centerX = 200
            player.centerY = 60
            
        elif player.centerX == 320 and player.centerY == 100:
            player.centerX = 240
            player.centerY = 60
            
        elif player.centerX == 280 and player.centerY == 100:
            player.centerX = 280
            player.centerY = 60
            
        elif player.centerX == 240 and player.centerY == 100:
            player.centerX = 320
            player.centerY = 60
            
        elif player.centerX == 200 and player.centerY == 100:
            player.centerX = 360
            player.centerY = 60
            
        else:
            player.centerX += 200
        die.value = ''
    
    # ROLL A 6  ROW 7
    if die.value == 6 and player.hitsShape(seventhRow) == True:
        if player.centerX == 360 and player.centerY == 100:
            player.centerX = 160
            player.centerY = 60
            
        elif player.centerX == 320 and player.centerY == 100:
            player.centerX = 200
            player.centerY = 60
            
        elif player.centerX == 280 and player.centerY == 100:
            player.centerX = 240
            player.centerY = 60
            
        elif player.centerX == 240 and player.centerY == 100:
            player.centerX = 280
            player.centerY = 60
            
        elif player.centerX == 200 and player.centerY == 100:
            player.centerX = 320
            player.centerY = 60
            
        elif player.centerX == 160 and player.centerY == 100:
            player.centerX = 360
            player.centerY = 60
            
        else:
            player.centerX += 240
        die.value = ''

# EIGTH ROW
    # ROLL A 1  ROW 8
    if die.value == 1 and player.hitsShape(eighthRow) == True:
        player.centerX -= 40
        die.value = ''
    
    # ROLL A 2  ROW 8
    if die.value == 2 and player.hitsShape(eighthRow) == True:
        player.centerX -= 80
        die.value = ''
        
    # ROLL A 3  ROW 8
    if die.value == 3 and player.hitsShape(eighthRow) == True:
        player.centerX -= 120
        die.value = ''    
        
    # ROLL A 4  ROW 8
    if die.value == 4 and player.hitsShape(eighthRow) == True:
        player.centerX -= 160
        die.value = ''
    
    # ROLL A 5  ROW 8
    if die.value == 5 and player.hitsShape(eighthRow) == True:
       player.centerX -= 200
       die.value = ''
    
    # ROLL A 6  ROW 8
    if die.value == 6 and player.hitsShape(eighthRow) == True:
        player.centerX -= 240
        die.value = ''
        
    # SNAKES AND LADDERS 
    # LADDERS
    if player.centerX == 240 and player.centerY == 340:
        player.centerX = 200
        player.centerY = 300
    elif player.centerX == 240 and player.centerY == 260:
        player.centerX = 200
        player.centerY = 220
    elif player.centerX == 80 and player.centerY == 220:
        player.centerX = 160
        player.centerY = 100
    elif player.centerX == 360 and player.centerY == 140:
        player.centerX = 280
        player.centerY = 60
    # SNAKES
    if player.centerX == 360 and player.centerY == 260:
        player.centerY += 40
    elif player.centerX == 160 and player.centerY == 220:
        player.centerX = 120
        player.centerY = 300
    elif player.centerX == 280 and player.centerY == 140:
        player.centerX = 280
        player.centerY = 260
    elif player.centerX == 200 and player.centerY == 100:
        player.centerX = 200
        player.centerY = 180
    elif player.centerX == 80 and player.centerY == 60:
        player.centerX = 80
        player.centerY = 140
        
    # REACHING THE END + END SCREEN + LOSE SCREEN
    if player.centerX <= 40 and player.centerY == 60:
        die.value = ''
        player.centerX = 40
        player.centerY = 60
        app.background = 'lightBlue'
        firstRow.visible = False
        secondRow.visible = False
        thirdRow.visible = False
        fourthRow.visible = False
        fifthRow.visible = False
        sixthRow.visible = False
        seventhRow.visible = False
        eighthRow.visible = False
        player.visible = False
        dieCube.visible = False
        die.visible = False
        boardOutline.visible = False
        snakes.visible = False
        ladders1.visible = False
        ladders2.visible = False
        ladders3.visible = False
        ladders4.visible = False
        gameOverLabel.visible = True
        timer.visible = False
        timerLabel.visible = False
        
    elif timer.value == 0:
        die.value = ''
        player.centerX = 40
        player.centerY = 60
        app.background = 'lightBlue'
        firstRow.visible = False
        secondRow.visible = False
        thirdRow.visible = False
        fourthRow.visible = False
        fifthRow.visible = False
        sixthRow.visible = False
        seventhRow.visible = False
        eighthRow.visible = False
        player.visible = False
        dieCube.visible = False
        die.visible = False
        boardOutline.visible = False
        snakes.visible = False
        ladders1.visible = False
        ladders2.visible = False
        ladders3.visible = False
        ladders4.visible = False
        gameOverLabel.visible = False
        timer.visible = False
        timerLabel.visible = False
        dieDots.visible = False

cmu_graphics.run()