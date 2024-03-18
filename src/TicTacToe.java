import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardwidth = 600;
    int boardheight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton playAgain = new JButton("Play Again");

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c< 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);


                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            checkWinner();
                            checkDraw();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX; //set currentplayer == to if playerX change it to playerO else change to playerX
                                textLabel.setText(currentPlayer + "'s turn");
                            }
                        }
                    }
                });
            }
        }
        playAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();    

            }
        });
    }

    void checkWinner() {
        //horizontal
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText() == "") continue;

            if (board[i][0].getText() == board[i][1].getText() && board[i][1].getText() == board[i][2].getText()){
                for (int j = 0; j < 3; j++) {
                    setWinner(board[i][j]);
                }
                gameOver = true;
                return;
            }
        }

        // vertical
        for (int i = 0; i < 3; i++) {
            if (board[0][i].getText() == "") continue;

            if (board[0][i].getText() == board[1][i].getText() && board[1][i].getText() == board[2][i].getText()) {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[j][i]);
                }
                gameOver = true;
                return;
            }
        }

        //diagonal left
        if (!board[0][0].getText().equals("") && board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText()) {
            for (int j = 0; j < 3; j++) {
                setWinner(board[j][j]);
            }
            gameOver = true;
            return;
        }

        //diagonal right
        if (!board[0][2].getText().equals("") && board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText()) {
            for (int j = 0; j < 3; j++) {
                setWinner(board[j][2 - j]);
            }
            gameOver = true;
            return;
        }

    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
        frame.add(playAgain, BorderLayout.SOUTH);

    }

    void checkDraw() {
        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getText().equals("")) {
                    draw = false;
                    break;
                }
            }
        }
        if (draw && !gameOver) {
            textLabel.setText("Draw!");
            gameOver = true;
            frame.add(playAgain, BorderLayout.SOUTH);
        }
    }
    void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
                board[i][j].setBackground(Color.darkGray);
                board[i][j].setForeground(Color.white);
            }
        }
        currentPlayer = playerX;
        gameOver = false;
        textLabel.setText("Tic-Tac-Toe");
        frame.remove(playAgain);
        frame.revalidate();
        frame.repaint();
    }
}
