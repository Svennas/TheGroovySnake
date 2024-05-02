package program

import java.awt.Color

class Apple {
    int[] position = [0, 0]
    Color colour

    Apple(Color colour, int gridWidth, int gridHeight) {
        this.position = [0, 0]
        this.colour = colour

        generateApple(gridWidth, gridHeight)
    }

    void generateApple(int gridWidth, int gridHeight) {
        this.position[0] = (int)(Math.random() * gridWidth)
        this.position[1] = (int)(Math.random() * gridHeight)
    }

    boolean isHeadAtApple(int[] snakeHead) {
        return (snakeHead[0] == this.position[0] && snakeHead[1] == this.position[1])
    }

    int[] getPosition() {
        return this.position
    }

    Color getColour() {
        return this.colour
    }
}
