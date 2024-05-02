package program

import javax.swing.Timer

class Game {
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
        if (    newHead[0] < 0 ||
                newHead[0] >= getGridWidth() ||
                newHead[1] < 0 ||
                newHead[1] >= getGridHeight() ||
                getSnake().getBody().contains(newHead))
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
        } else {
            getSnake().removeTail()
        }
        getGameBoard().updateScore(getScore())
        getGameBoard().repaint()
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
