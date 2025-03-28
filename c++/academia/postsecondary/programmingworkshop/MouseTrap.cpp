// Description: This program simulates a game of Mouse Trap.

#include <iostream>
#include <vector>
#include <ctime>
#include <unistd.h>

using namespace std;

//These are the numbered positions of each mouse, ranging from 0 (start) to 49 (cheese wheel)
int mouse_positions[] = {0,0,0,0};

//How much cheese each mouse possesses
int mouse_cheeses[] = {0,0,0,0};

//For each mouse, true if that mouse is still in the game
bool mouse_in_game[] = {false,false,false,false};

//Number of players still in the game
int player_count = 0;

int cheese_supply = 52; 
int trap_components_remaining = 23; 


/*
assembleTrap adds the next piece to the trap. It prints out a 
message that describes what piece was added, if any.

It returns true if the piece is successfully added, 
otherwise it returns false if the trap is already complete. 
*/
bool assembleTrap();

//wait causes the program to pause for seconds
void wait(double seconds);

/*
activateTrap puts the trap into motion. It has a chance of failing if
the trap malfunctions along the way.

The function returns true if the trap worked, and false if it doesn't.
*/
bool activateTrap();

/*
determineSpaceType will, given the position number of the space, return 
an integer representing the type of space it is For example, to determine the 
type of space of the first space after the starting position (0), 
determineSpaceType(1) will return 0 since that space is a Build 2 space.

Here is the legend for the different spaces:
    0 - Build
    1 - Move Ahead
    2 - Go Back
    3 - Take Cheese
    4 - Lose Cheese
    5 - Dog Bone
    6 - Cheese Wheel
    7 - Double Build 2
    8 - Safe Space
    9 - Turn Crank
*/
int determineSpaceType(int spaceNumber);

/*
This function will, given the position number of the space, return an 
integer representing the value of X for that space For example, 
since the first space after the start is a Build 2 space, 
determineX(1) will return 2. determineX(10) is 4 because the 
space moves the mouse 4 steps forward.
*/
int determineX(int spaceNumber);

/*
drawBoard constructs a string representing the board, starting with a blank
text drawing of the board, then calling placeMouse for each mouse still in
the game. It then prints out the string.
*/
void drawBoard ();

/*
takeCheese takes cheese for mouse_number either from the supply (if nonempty)
or from the other player with the most cheese pieces
*/
void takeCheese(int amount_of_cheese, int mouse_number);

/*
moveMouse takes a mouse and rolls a die, then moves the mouse that many
spaces along the board. It does NOT activate the space that it lands on.
*/
void moveMouse(int mouse_number);

/*
activateSpace uses determineSpaceType and determineX to figure out
what should happen for the mouse_position of mouse_number, then takes 
the appropriate action.
*/
void activateSpace(int mouse_number, int &mouse_position, int &mouse_cheese){
    cout << "Landed on: ";
    switch(determineSpaceType(mouse_position)){
        case 0: {
            int buildNumber = determineX(mouse_position);
            cout << "Build " << buildNumber << ".\n";
            if(player_count <= buildNumber){
                if(assembleTrap()){
                    cout << "Adding a component to the trap and taking a cheese from the supply.\n";
                    takeCheese(1, mouse_number);
                }
            }
            break;
        }
        case 1: {
            cout << "Move Forward " << determineX(mouse_position) << ".\n";
            int forward = determineX(mouse_position);
            cout << "Moving " << to_string(forward) << " steps forward..." << endl;
            mouse_position += forward;
            break;
        }
        case 2: {
            cout << "Move Back " << determineX(mouse_position) << ".\n";
            int back = determineX(mouse_position);
            cout << "Moving " << to_string(back) << " steps back..." << endl;
            mouse_position -= back;
            break;
        }
        case 3: {
            cout << "Take " << determineX(mouse_position) << " Cheese.\n";
            takeCheese(determineX(mouse_position), mouse_number);
            break;
        }
        case 4: {
            int lostCheese = determineX(mouse_position);
            cout << "Lose " << lostCheese << " Cheese.\n";
            mouse_cheese = max(0, mouse_cheese - lostCheese);
            cheese_supply += min(lostCheese, mouse_cheese);
            break;
        }
        case 5: {
            cout << "Dog bone! Nothing happens." << endl;
            break;
        }
        case 6: {
            cout << "Take 2 Cheese.\n";
            takeCheese(2, mouse_number);
            break;
        }
        case 7: {
            cout << "Double Build 4.\n";
            for(int i = 0; i < 2; i++){
                if(player_count <= 4){
                    if(assembleTrap()){
                        cout << "Adding a component to the trap and taking a cheese from the supply.\n";
                        takeCheese(1, mouse_number);
                    }
                }
            }
            break;
        }
        case 8: {
            cout << "Safe!" << endl;
            break;
        }
        case 9: {
            cout << "Turn Crank.\n";
            if (trap_components_remaining > 0){
                cout << "Cannot turn crank; Trap is incomplete." << endl;
            } else{
                bool more_rolls = true;
                while (more_rolls && mouse_cheese > 0){
                    cout << "Time to turn the crank! Would you like to move another mouse (Y/N)?"
                        << endl;
                    
                    drawBoard();
                    
                    cout << "You have " << mouse_cheese << " pieces of cheese." << endl;
                    
                    char input;
                    cin >> input;
                    if(input == 'Y' || input == 'y'){
                        cout << "Which mouse would you like to move?" << endl;
                        int other_mouse;
                        cin >> other_mouse;
                        if(mouse_in_game[other_mouse] && mouse_positions[other_mouse] != 44){
                            cheese_supply++;
                            mouse_cheese--;
                            moveMouse(other_mouse);
                            drawBoard();
                        }
                    } else {
                        more_rolls = false;
                    }
                    
                }
                if (activateTrap()){
                    for(int i = 0; i < 4; i++){
                        if(mouse_positions[i] == 49){
                            cout << "Mouse " << i << " was captured!\n";
                            mouse_in_game[i] = false;
                            mouse_cheese += mouse_cheeses[i];
                            mouse_cheeses[i] = 0;
                        }
                    }
                }
                
            }
            break;
        }
        default:
            cout << "Error: Invalid Space type!" << endl;
    }
}


/*
playGame uses the above functions to run the main game loop.

It should do the following:
1) Set up the number of players/mice in the game
2) For each mouse still in the game:
  a) Indicate the mouse whose turn it is
  b) Display a count of the cheese supply and the current mouse's cheese
  c) Move the mouse
  d) Activate the mouse's new space
  e) Display the board
  f) (optional) Use wait or cin to pause the game after each turn 
3) End the game once there is only one mouse remaining
*/
void playGame(){
    cout << "How many players are playing? 2-4" << endl;
    cin >> player_count;
    while (player_count != 1) {
    for (int i = 0; i < player_count; i++) {
        cout << "It's Player " << i << "'s turn!" << endl;
        cout << "Current amount in cheese supply: " << cheese_supply;
        cout << "You have " << mouse_cheeses << "pieces of cheese!" << endl;
        moveMouse(i);
        activateSpace(i, mouse_positions[i], mouse_cheeses[i]);
        drawBoard();
        if (player_count == 1) {
            cout << "Game over! Player " << i << " wins!";
            break;
        }
        }
}
}

int main(){
    srand(time(NULL));
    playGame();
    return 0;
}

//****************************************************************
//DO NOT MODIFY THE CODE BELOW
//****************************************************************

int rollDie(){
    return rand()%6+1;
}

bool assembleTrap(){
    if (trap_components_remaining == 0){
        cout << "Trap is already complete!" << endl;
        return false;
    }
    cout << "Adding component #" << to_string(24-trap_components_remaining) << ": ";
    
    string components[] = {"Trap empty", "Base A inserted.",
                           "Gear Support attached to Base A.",
                           "Gear 3 attached to Gear Support.",
                          "Gear Crank pushed onto Gear 3.", 
                           "Gear 5 meshed with Gear 3.",
                           "Stop Sign placed.",
                          "Lamppost inserted into Base A.",
                           "Boot added to Lamppost.","Stairway assembled.",
                          "Bucket and Marble snapped onto Stairway.",
                           "Base B inserted.","Rain Gutter placed onto Base B.",
                          "Plumbing inserted into Base B.",
                           "Helping Hand loaded into Plumbing.",
                          "Thing-a-ma-jig added to top of Plumbing.",
                           "Bathtub placed on Plumbing.",
                          "Second Marble placed on Thing-a-ma-jig.",
                           "Diving Board placed on Base B.",
                          "Diver standing on Diving Board.", 
                           "Cage Base C plugged in", 
                           "Washtub placed on Cage Base C.",
                          "Cage Post added to Cage Base C.", 
                           "Cage balanced on Cage Post.\nTrap assembled!"};
    
    cout << components[24-trap_components_remaining] << endl;
    
    trap_components_remaining--;
    return true;
}

void wait(double seconds){
    sleep(seconds);
}

bool activateTrap(){
    
    cout << "Turning the crank..." << endl;
    cout << "Gears are rotating..." << endl;
    wait(2);
    if (rand()%100 < 1){
        cout << "The lever failed to push the stop sign! The trap has failed." << endl;
        return false;
    }
    cout << "The lever pushed the stop sign!" << endl;
    cout << "The stop sign pushed the boot!" << endl;
    if (rand()%100 < 2){
        cout << "The boot missed the bucket! The trap has failed." << endl;
        return false;
    }
    cout << "The boot tipped over the bucket!" << endl;
    wait(0.5);
    cout << "The marble has been released!" << endl;
    cout << "The marble is descending the stairs..." << endl;
    wait(2);
    if (rand()%100 < 3){
        cout << "The marble slipped off the stairs! The trap has failed." << endl;
        return false;
    }
    cout << "The marble is rolling down the rain gutter..." << endl;
    wait(2);
    cout << "The marble struck the rod with the helping hands!" << endl;
    if (rand()%100 < 4){
        cout << "...but the second marble didn't move! The trap has failed." << endl;
        return false;
    }
    cout << "The second marble is released!" << endl;
    wait(1);
    cout << "The marble has fallen into the bathtub!" << endl;
    wait(1);
    cout << "The marble has hit the diving board!" << endl;
    cout << "The diver has been launched into the air!" << endl;
    if (rand()%100 < 5){
        cout << "The diver didn't land in the washtub! The trap has failed." << endl;
        return false;
    }
    cout << "The diver landed in the washtub!" << endl;
    cout << "The cage is falling!" << endl; 
    wait(2);
    if (rand()%100 < 6){
        cout << "The cage got stuck! The trap has failed." << endl;
        return false;
    }
    cout << "The trap has been successfully sprung!" << endl;
    return true;
}

int determineSpaceType(int spaceNumber){
   
    vector<int> build_spaces = {1,3,4,6,8,9,11,12,14,16,17,18,19,20,22,
                                23,25,26,28,29,31,32,34,35,36,38,40,41,43};
    vector<int> move_spaces = {10,30};
    vector<int> back_spaces = {5,13,15,27,33,42};
    vector<int> take_spaces = {2,7,37,48};
    vector<int> lose_spaces = {24,39};
    vector<int> dog_spaces = {21};
    vector<int> wheel_spaces = {49};
    vector<int> double_spaces = {45,47};
    vector<int> safe_spaces = {44};
    vector<int> crank_spaces = {46};
    
    vector<vector<int>> space_array = {build_spaces,move_spaces,back_spaces,
                                       take_spaces,lose_spaces,dog_spaces,
                                       wheel_spaces,double_spaces,
                                       safe_spaces,crank_spaces};
    
    for (int i = 0; i <= 9; i++){
        for (int j : space_array[i]){
            if (j == spaceNumber){
                return i;
            }
        }
    }
            
    return -1; //error!
}

int determineX(int spaceNumber){
    int xs[] = {0,2,2,4,2,2,4,1,4,3,4,4,2,13,4,6,2,4,3,2,4,0,4,2,2,2,4,6,
                4,3,19,2,4,4,2,4,3,3,4,3,4,3,6,2,0,4,0,4,3,2};
    return xs[spaceNumber];
}

void placeMouseInSquare(string* board_strings, int mouse_number, int row, int col){
    if (board_strings[row][col] == ' '){
        board_strings[row][col] = char('0'+mouse_number);
    } else if (board_strings[row][col+1] == ' '){
        board_strings[row][col+1] = char('0'+mouse_number);
    } else if (board_strings[row+1][col] == '_'){
        board_strings[row+1][col] = char('0'+mouse_number);
    } else{
        board_strings[row+1][col+1] = char('0'+mouse_number);
    }
}

void placeMouse(string* board_strings, int mouse_number){
    
    int mouse_position = mouse_positions[mouse_number];
    if (mouse_position == 0){
        placeMouseInSquare(board_strings, mouse_number, 1, 1);
    } else if (mouse_position <= 20){
        placeMouseInSquare(board_strings, mouse_number, 3, 1+(mouse_position-1)*4);
    } else if (mouse_position == 21){
        placeMouseInSquare(board_strings, mouse_number, 5, 1+(mouse_position-2)*4);
    } else if (mouse_position <= 41){
        placeMouseInSquare(board_strings, mouse_number, 7, 1+(41-mouse_position)*4);
    } else {
            switch(mouse_position){
                case 42:
                    placeMouseInSquare(board_strings, mouse_number, 9, 1);
                    break;
                case 43:
                    placeMouseInSquare(board_strings, mouse_number, 11, 4);
                    break;
                case 44:
                    placeMouseInSquare(board_strings, mouse_number, 13, 8);
                    break;
                case 45:
                    placeMouseInSquare(board_strings, mouse_number, 15, 5);
                    break;
                case 46:
                    placeMouseInSquare(board_strings, mouse_number, 17, 9);
                    break;
                case 47:
                    placeMouseInSquare(board_strings, mouse_number, 17, 14);
                    break;
                case 48:
                    placeMouseInSquare(board_strings, mouse_number, 15, 18);
                    break;
                case 49:
                    placeMouseInSquare(board_strings, mouse_number, 13, 14);
                    break;
                default:
                    break;
            }
    }
}

void drawBoard (){
    string board_strings[] ={
    " __\n",
    "|  | <- Start\n",
    "|__| __  __  __  __  __  __  __  __  __  __  __  __  __  __  __  __  __  __  __\n",
    "|  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n",
    "|__||__||__||__||__||__||__||__||__||__||__||__||__||__||__||__||__||__||__||__|\n",
    "                                                                            |  |\n",
    " __  __  __  __  __  __  __  __  __  __  __  __  __  __  __  __  __  __  __ |__|\n",
    "|  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n",
    "|__||__||__||__||__||__||__||__||__||__||__||__||__||__||__||__||__||__||__||__|\n",
    "|  |\n",
    "|__|__\n",
    "   |  |\n",
    "   |__| __    __\n",
    "Safe ->|  |<<|  | <- Cheese Wheel\n",
    "     __|__|<<|__|__\n",
    "  V |  |        |  | ^\n",
    "  V |__| __   __|__| ^\n",
    "        |  |>|  |\n",
    "        |__|>|__|\n",
    };
    
    for (int i = 0; i < player_count; i++){
        if (mouse_in_game[i]){
            placeMouse(board_strings, i);
        }
    }
    
    for (int i = 0; i < 19; i++){
        cout << board_strings[i];
    }
    cout << endl << endl;
    
}

void takeCheese(int amount_of_cheese, int mouse_number) {
    int amount_to_steal = amount_of_cheese - cheese_supply;

    if (amount_to_steal <= 0) {
        cheese_supply -= amount_of_cheese;
        cout << "Took " << amount_of_cheese << " cheese pieces from the supply." << endl;
        mouse_cheeses[mouse_number] += amount_of_cheese;
        return;
    }

    if (amount_to_steal != amount_of_cheese) {
        cout << "Took " << cheese_supply << " cheese pieces from the supply." << endl;
        mouse_cheeses[mouse_number] += cheese_supply;
        cheese_supply = 0;
    }

    int max_mouse = 0;
    int max_cheese = 0;

    for (int i = 0; i < player_count; i++) {
        if (i != mouse_number && mouse_cheeses[i] > max_cheese) {
            max_cheese = mouse_cheeses[i];
            max_mouse = i;
        }
    }

    int stolen_cheese = min(amount_to_steal, max_cheese);
    cout << "Stole " << stolen_cheese << " pieces of Cheese from Mouse " << max_mouse << "!" << endl;
    mouse_cheeses[mouse_number] += stolen_cheese;
    mouse_cheeses[max_mouse] -= stolen_cheese;
}

void moveMouse(int mouse_number){
    cout << "Moving Mouse " + to_string(mouse_number) + "!" << endl;
    int dieroll = rollDie();
    cout << "Rolled: " + to_string(dieroll) << endl;
    mouse_positions[mouse_number] += dieroll;
    if (mouse_positions[mouse_number] > 49){
        mouse_positions[mouse_number] -= 6;
    }    
}

