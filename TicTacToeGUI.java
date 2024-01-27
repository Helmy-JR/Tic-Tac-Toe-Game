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
