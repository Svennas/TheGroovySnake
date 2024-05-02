package program

import java.awt.Color

def GRID_SIZE = 40
def GRID_WIDTH = 30
def GRID_HEIGHT = 20
def START_SCORE = 0

def appleColour = Color.RED
def apple = new Apple(appleColour, GRID_WIDTH, GRID_HEIGHT)

def startingSnakeBody = [[5, 5], [5, 4], [5, 3]]
def snakeDirectionStart = 'right'
def snakeColour = Color.GREEN
def snake = new Snake(startingSnakeBody, snakeDirectionStart, snakeColour, apple)

def gameBoard = new GameBoard(snake, apple, GRID_SIZE, GRID_WIDTH, GRID_HEIGHT, START_SCORE)

def snakeGame = new Game(snake, apple, gameBoard, GRID_SIZE, GRID_WIDTH, GRID_HEIGHT)

while (!gameBoard.getTurnOffGame()) {
    if (gameBoard.getIsGameOver()) {
        if (gameBoard.restartGame()) {
            snakeGame.resetGame([[5, 5], [5, 4], [5, 3]], snakeDirectionStart)
        }
        else {
            return 0
        }
    }
}