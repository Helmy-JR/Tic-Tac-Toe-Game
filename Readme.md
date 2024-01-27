# Tic Tac Toe Game Documentation

## Overview

Tic Tac Toe is a classic two-player game where players take turns marking spaces in a 3x3 grid. The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row wins the game. This project implements a Tic Tac Toe game in Java with a graphical user interface (GUI) using Swing.

## Purpose

The purpose of this project is to provide a playable Tic Tac Toe game with the following features:

1. Graphical User Interface (GUI): The game features a graphical interface where players can interact with the grid using clickable buttons.
2. Player Turns: Players take turns marking spaces in the grid.
3. Win Detection: The game detects when a player wins by forming a row, column, or diagonal of their marks.
4. Draw Detection: The game detects when the grid is fully filled without a winner, resulting in a draw.
5. Restart Functionality: Players can restart the game to play again.

## Functionality

### GUI Components
- **Grid Panel**: Displays the 3x3 grid of buttons representing the Tic Tac Toe board.
- **Status Label**: Displays the current player's turn or game status (win, draw).
- **Restart Button**: Allows players to restart the game.
- **Player Win Count Labels**: Display the number of wins for each player (Player X and Player O).

### Game Logic
- **Player Turns**: Players alternate turns marking spaces on the grid with their respective symbols (X or O).
- **Win Detection**: The game checks for win conditions after each move to determine if a player has won.
- **Draw Detection**: The game checks if the grid is fully filled without a winner, resulting in a draw.
- **Restart**: Players can restart the game at any time to reset the grid and start a new game.
  
## Structure

### Classes
- **TicTacToeGUI**: Main class that implements the GUI and game logic.
  
### Methods
- **buttonClicked(int row, int col)**: Handles button click events. Updates the grid, checks for win/draw conditions, and switches player turns.
- **checkWin(int row, int col)**: Checks if the current move results in a win for the player.
- **checkDraw()**: Checks if the game is a draw (grid is fully filled without a winner).
- **restartGame()**: Resets the game by clearing the grid, updating the status, and resetting the player turns.

## Usage

1. Run the program to launch the Tic Tac Toe game.
2. Click on any empty space in the grid to mark it with your symbol.
3. Take turns with the other player until one player wins or the game ends in a draw.
4. Use the restart button to start a new game.

## Conclusion

The Tic Tac Toe game project provides a simple yet functional implementation of the classic game with a graphical interface. It demonstrates the use of Java Swing for building GUI applications and implements core game logic for player interaction, win detection, and restart functionality.
