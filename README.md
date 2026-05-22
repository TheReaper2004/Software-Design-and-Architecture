# Software-Design-and-Architecture

## What is this project?
This project is a Java board game simulation.
The game is played on a 6x6 board with 4 players.
Each player rolls two dice and moves around the board.
The first player to reach the end position wins the game.
The game currently uses automated players to simulate gameplay.
This allows easier testing of game rules and board interactions.

# Features
1. Four Players
The game has:
- Red player
- Blue player
- Yellow player
- Green player
Each player starts in a different position.

2. Dice Rolling
The game uses two dice.
The dice values are random.
This makes every game different.

3. Movement System
Players move after rolling the dice.
The total movement is tracked during the game.
The movement is shown in the final summary table.

4. Hit Rule
If a player lands on another player:
- the move is cancelled
- the player loses the turn
- the player stays in the same place
This creates more challenge in the game.

5. Wormholes
The board contains wormholes.
If a player lands on a wormhole:
- the player teleports to another position
Example:
- position 4 goes to 9
- position 19 goes to 23
This makes the game more interesting.

## Classes Used
Main Class
The Main class starts the game.
It creates the Game object and runs the play method.

# Game Class
The Game class controls the game.
Responsibilities:
- controls turns
- rolls dice
- checks winners
- handles hit rule
- handles wormholes
- prints summary

# Player Class
The Player class stores player information.
It stores:
- player name
- current position
- total movement
- end position
The Player class also controls movement.

# Dice Class
The Dice class rolls two random dice.
This class uses Java Random.

# Board Class
The Board class stores wormholes.
It checks if a player enters a wormhole.

# Wormhole Class
The Wormhole class stores:
- start position
- end position
It teleports players between positions.

# Object Oriented Programming
This project uses Object Oriented Programming (OOP).
The program is split into multiple classes.
Each class has a different responsibility.
This makes the code:
- cleaner
- easier to understand
- easier to maintain

# Java Concepts Used
This project uses:
- classes
- objects
- loops
- methods
- lists
- random numbers
- encapsulation
- condition statements

# Game Rules
1. A player rolls two dice.
2. The player moves around the board.
3. If the player lands on another player, the move is cancelled.
4. Wormholes teleport players to other positions.
5. The first player to reach the end wins.

## Example Output

# Example Game Summary
| Player | Position | Movement |
|--------|----------|----------|
| Red    |    35    |    34    |
| Blue   |     6    |    22    |
| Yellow |    12    |    24    |
| Green  |    23    |    18    |
Winner: Blue
Total turns: 14

# Conclusion
This project successfully creates a playable board game simulation using Java.
The project demonstrates:
- object oriented programming
- multiple classes
- game logic
- random gameplay
- board rules
- movement tracking
- console output
The game can be expanded in the future by adding:
- save system
- graphical interface
- more board rules
- different game modes