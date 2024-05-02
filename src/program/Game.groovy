package program

import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.Timer
import java.awt.Color
import java.awt.Graphics

class Game extends JComponent {
    def gridSize
    def gridWidth
    def gridHeight

    GameBoard gameBoard
    Snake snake
    Apple apple

    Timer timer
    def score = 0

    Game(Snake snake, Apple apple, GameBoard gameBoard, int gridSize, int gridWidth, int gridHeight) {
        this.gameBoard = gameBoard
        this.snake = snake
        this.apple = apple

        this.gridSize = gridSize
        this.gridWidth = gridWidth
        this.gridHeight = gridHeight

        this.timer = new Timer(100, { move() })
        this.timer.start()

        this.setSize(gridWidth * gridSize + gridSize, gridHeight * gridSize + gridSize)
        this.setVisible(true)
        this.setLocation(0, 0)
        this.repaint()

        this.gameBoard.add(this)
    }

    @Override
    void paint(Graphics g) {
        g.color = Color.BLACK
        for (int x = 0; x < getGridWidth(); x++) {
            g.fillRect(x * getGridSize(), 0 * getGridSize(), getGridSize(), getGridSize())
            g.fillRect(x * getGridSize(), getGridHeight() * getGridSize(), getGridSize(), getGridSize())
        }
        for (int y = 0; y <= getGridHeight(); y++) {
            g.fillRect(0 * getGridSize(), y * getGridSize(), getGridSize(), getGridSize())
            g.fillRect(getGridWidth() * getGridSize(), y * getGridSize(), getGridSize(), getGridSize())
        }
    }

    void move() {
        def head = getSnake().getBody()[0]
        int[] newHead = [head[0], head[1]]
        switch (getSnake().getDirection()) {
            case 'up':
                newHead[1]--
                break
            case 'down':
                newHead[1]++
                break
            case 'left':
                newHead[0]--
                break
            case 'right':
                newHead[0]++
                break
        }
        if (    newHead[0] < 1 ||
                newHead[0] > getGridWidth() - 1 ||
                newHead[1] < 1 ||
                newHead[1] > getGridHeight() - 1 ||
                getSnake().getBody().contains(newHead) ||
                getSnake().isHeadInBody(newHead))
        {
            getTimer().stop()
            getGameBoard().updateScore(getScore())
            getGameBoard().gameOverMessage()
            return
        }

        getSnake().addToHead(newHead)

        if (getApple().isHeadAtApple(newHead)) {
            setScore(getScore() + 1)
            getApple().generateApple(getGridWidth(), getGridHeight())
//            getApple().repaint()
        } else {
            getSnake().removeTail()
        }
        getGameBoard().updateScore(getScore())
        getGameBoard().repaint()
        getSnake().repaint()
//        getApple().repaint()
    }

    void resetGame(ArrayList<List<Integer>> body, String snakeDirection) {
        println("Body change to in reset = " + body)
        getSnake().setBody(body)
        getSnake().setDirection(snakeDirection)

        getApple().generateApple(getGridWidth(), getGridHeight())

        setScore(0)
        getGameBoard().updateScore(getScore())
        getGameBoard().setIsGameOver(false)
        getGameBoard().repaint()
        getSnake().repaint()
//        getApple().repaint()

        getTimer().start()
    }

    int getGridSize() {
        return this.gridSize
    }

    int getGridWidth() {
        return this.gridWidth
    }

    int getGridHeight() {
        return this.gridHeight
    }

    GameBoard getGameBoard() {
        return this.gameBoard
    }

    Snake getSnake() {
        return this.snake
    }

    Apple getApple() {
        return this.apple
    }

    Timer getTimer() {
        return this.timer
    }

    int getScore() {
        return this.score
    }

    void setScore(score) {
        this.score = score
    }
}
