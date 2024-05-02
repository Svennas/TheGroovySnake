package program

import javax.swing.JComponent
import java.awt.Color
import java.awt.Graphics

class Apple {
    int[] position = [0, 0]
    Color colour

    Apple(Color colour, int gridWidth, int gridHeight) {
        this.position = [0, 0]
        this.colour = colour

//        this.setVisible(true)

        generateApple(gridWidth, gridHeight)
    }

//    @Override
//    void paint(Graphics g) {
//        super.paint(g)
//        g.color = getColour()
//        g.fillRect(getPosition()[0] * 40, getPosition()[1] * 40, 40, 40)
//    }

    void generateApple(int gridWidth, int gridHeight) {
        this.position[0] = (int)(Math.random() * (gridWidth - 1)) + 1
        this.position[1] = (int)(Math.random() * (gridHeight - 1)) + 1
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
