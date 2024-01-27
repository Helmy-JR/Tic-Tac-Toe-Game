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


## The Code :

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI {
    private JFrame frame; // Main frame for the GUI
    private JButton[][] buttons; // Array to hold the buttons for the Tic Tac Toe grid
    private JLabel statusLabel; // Label to display current player or game status
    private JLabel playerXWinsLabel; // Label to display number of wins for Player X
    private JLabel playerOWinsLabel; // Label to display number of wins for Player O
    private String currentPlayer; // Tracks the current player (either "X" or "O")
    private boolean gameOver; // Tracks whether the game is over or not
    private JButton restartButton; // Button to restart the game
    private int playerXWins; // Tracks the number of wins for Player X
    private int playerOWins; // Tracks the number of wins for Player O

    // Constructor to set up the GUI and initialize variables
    public TicTacToeGUI() {
        frame = new JFrame("Tic Tac Toe"); // Create the main frame
        buttons = new JButton[3][3]; // Create a 2D array for the buttons in the grid
        currentPlayer = "X"; // Set the initial player to X
        gameOver = false; // Game is not over initially
        playerXWins = 0; // Initialize Player X wins to 0
        playerOWins = 0; // Initialize Player O wins to 0

        // Create the grid panel for the Tic Tac Toe board
        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton(); // Create a new button
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 100)); // Set font size for button text
                final int r = row;
                final int c = col;
                buttons[row][col].addActionListener(e -> buttonClicked(r, c)); // Add action listener to the button
                gridPanel.add(buttons[row][col]); // Add button to the grid panel
            }
        }

        restartButton = new JButton("Restart"); // Create the restart button
        restartButton.addActionListener(e -> restartGame()); // Add action listener to restart button

        // Create status label to display current player or game status
        statusLabel = new JLabel("Current player: " + currentPlayer);
        // Create labels to display number of wins for Player X and Player O
        playerXWinsLabel = new JLabel("Player X wins: " + playerXWins);
        playerOWinsLabel = new JLabel("Player O wins: " + playerOWins);

        // Create bottom panel to hold status label, restart button, and win count labels
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(statusLabel);
        bottomPanel.add(restartButton);
        bottomPanel.add(playerXWinsLabel);
        bottomPanel.add(playerOWinsLabel);

        // Add components to the main frame
        frame.add(gridPanel, BorderLayout.CENTER); // Add grid panel to the center
        frame.add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel to the south

        frame.setSize(400, 300); // Set frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        frame.setVisible(true); // Make frame visible
    }

    // Method to handle button click events
    private void buttonClicked(int row, int col) {
        // Check if the game is not over and the clicked button is empty
        if (!gameOver && buttons[row][col].getText().equals("")) {
            buttons[row][col].setText(currentPlayer); // Set button text to current player
            // Check if the current player wins
            if (checkWin(row, col)) {
                statusLabel.setText("Player " + currentPlayer + " wins!"); // Update status label
                // Increment win count for the current player
                if (currentPlayer.equals("X")) {
                    playerXWins++;
                    playerXWinsLabel.setText("Player X wins: " + playerXWins); // Update win count label for Player X
                } else {
                    playerOWins++;
                    playerOWinsLabel.setText("Player O wins: " + playerOWins); // Update win count label for Player O
                }
                gameOver = true; // Set game over flag to true
            } else if (checkDraw()) {
                statusLabel.setText("It's a draw!"); // Update status label for draw
                gameOver = true; // Set game over flag to true
            } else {
                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X"; // Switch to the next player
                statusLabel.setText("Current player: " + currentPlayer); // Update status label
            }
        }
    }

    // Method to check if the current player wins
    private boolean checkWin(int row, int col) {
        String symbol = buttons[row][col].getText();
        // Check rows, columns, and diagonals for a winning pattern
        return (buttons[row][(col + 1) % 3].getText().equals(symbol) && buttons[row][(col + 2) % 3].getText().equals(symbol)) ||
                (buttons[(row + 1) % 3][col].getText().equals(symbol) && buttons[(row + 2) % 3][col].getText().equals(symbol)) ||
                (row == col && buttons[(row + 1) % 3][(col + 1) % 3].getText().equals(symbol) && buttons[(row + 2) % 3][(col + 2) % 3].getText().equals(symbol)) ||
                (row + col == 2 && buttons[(row + 1) % 3][(col + 2) % 3].getText().equals(symbol) && buttons[(row + 2) % 3][(col + 1) % 3].getText().equals(symbol));
    }

    // Method to check if the game is a draw
    private boolean checkDraw() {
        // Check if all buttons are filled
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to restart the game
    private void restartGame() {
        // Clear all buttons, reset current player, update status label, and reset game over flag
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(""); // Clear button text
            }
        }
        currentPlayer = "X"; // Reset current player to X
        statusLabel.setText("Current player: " + currentPlayer); // Update status label
        gameOver = false; // Reset game over flag
    }

    // Main method to create an instance of TicTacToeGUI class
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeGUI::new); // Create and run the GUI on the event dispatch thread
    }
}

```