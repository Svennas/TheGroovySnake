package program

import javax.swing.*
import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class GameBoard extends JFrame implements KeyListener {
    Snake snake
    Apple apple

    def gridSize
    def score
    def isGameOver
    def turnOffGame

    def areBordersPainted

    GameBoard(Snake snake, Apple apple, int gridSize, int gridWidth, int gridHeight, int score) {
        this.snake = snake
        this.apple = apple
        this.gridSize = gridSize
        this.score = score
        this.isGameOver = false
        this.turnOffGame = false
        this.areBordersPainted = false

        this.setTitle("Snake Game")
        this.setSize(gridWidth * (gridSize + 2), gridHeight * (gridSize + 4))
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.setLocationRelativeTo(null)

//        this.add(this.apple)
        this.add(this.snake)


        this.addKeyListener(this)
        this.setVisible(true)
    }

    void updateScore(int newScore) {
        this.score = newScore
    }

    @Override
    void paint(Graphics g) {
        super.paint(g)

//        if (!this.areBordersPainted) {
//            g.color = Color.BLACK
//            for (int x = 0; x < 30; x++) {
//                g.fillRect(x * getGridSize(), 0 * getGridSize(), getGridSize(), getGridSize())
//                g.fillRect(x * getGridSize(), 20 * getGridSize(), getGridSize(), getGridSize())
//            }
//            for (int y = 0; y <= 20; y++) {
//                g.fillRect(0 * getGridSize(), y * getGridSize(), getGridSize(), getGridSize())
//                g.fillRect(30 * getGridSize(), y * getGridSize(), getGridSize(), getGridSize())
//            }
//            setAreBordersPainted(true)
//        }

//        g.color = getSnake().getColour()
//        for (int[] part : getSnake().getBody()) {
//            g.fillRect(part[0] * getGridSize(), part[1] * getGridSize(), getGridSize(), getGridSize())
//        }
//        g.color = getApple().getColour()
//        g.fillRect(getApple().getPosition()[0] * getGridSize(),
//                getApple().getPosition()[1] * getGridSize(), getGridSize(), getGridSize())

        g.color = Color.BLACK
        g.drawString("Score: $score", 10, 20)
    }

    @Override
    void keyTyped(KeyEvent e) {}

    @Override
    void keyPressed(KeyEvent e) {
        switch (e.keyCode) {
            case KeyEvent.VK_UP:
                if (getSnake().getDirection() != 'down') getSnake().setDirection('up')
                break
            case KeyEvent.VK_DOWN:
                if (getSnake().getDirection() != 'up') getSnake().setDirection('down')
                break
            case KeyEvent.VK_LEFT:
                if (getSnake().getDirection() != 'right') getSnake().setDirection('left')
                break
            case KeyEvent.VK_RIGHT:
                if (getSnake().getDirection() != 'left') getSnake().setDirection('right')
                break
        }
    }

    @Override
    void keyReleased(KeyEvent e) {}

    void gameOverMessage() {
        JOptionPane.showMessageDialog(this, "Game Over! Your score is: $score")
        setIsGameOver(true)
    }

    boolean restartGame() {
        def option = JOptionPane.showConfirmDialog(this, "Do you want to restart the game?")
        if (option == JOptionPane.YES_OPTION) {
            return true
        }
        else if (option == JOptionPane.NO_OPTION) {
            setTurnOffGame(true)
            this.dispose()
            return false
        }
        return false
    }

    boolean getIsGameOver() {
        return this.isGameOver
    }

    boolean getTurnOffGame() {
        return this.turnOffGame
    }

    void setIsGameOver(isGameOver) {
        this.isGameOver = isGameOver
    }

    Snake getSnake() {
        return this.snake
    }

    Apple getApple() {
        return this.apple
    }

    int getGridSize() {
        return this.gridSize
    }

    def getScore() {
        return this.score
    }
}
