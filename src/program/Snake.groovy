package program

import javax.swing.JComponent
import java.awt.Color
import java.awt.Graphics

class Snake extends JComponent{
    ArrayList<List<Integer>> body
    def direction
    Color colour

    Apple apple

    Snake(ArrayList<List<Integer>> body, String direction, Color colour, Apple apple) {
        this.body = body
        this.direction = direction
        this.colour = colour

        this.apple = apple

        this.setVisible(true)
    }

    @Override
    void paint(Graphics g) {
        super.paint(g)

        g.color = getColour()
        for (int[] part : getBody()) {
            g.fillRect(part[0] * 40, part[1] * 40, 40, 40)
        }

        g.color = getApple().getColour()
        g.fillRect(getApple().getPosition()[0] * 40, getApple().getPosition()[1] * 40, 40, 40)
    }

    void addToHead(int[] newHead) {
        getBody().add(0, newHead)
    }

    boolean isHeadInBody(int[] head) {
        for (int i = 0; i < this.body.size(); i++) {
            if (head[0] == this.body[i][0] && head[1] == this.body[i][1]) {
                return true
            }
        }
        return false
    }

    void removeTail() {
        getBody().removeAt(body.size() - 1)
    }

    def getBody() {
        return this.body
    }

    def getDirection() {
        return this.direction
    }

    Color getColour() {
        return this.colour
    }

    void setBody(ArrayList<List<Integer>> body) {
        this.body = body
    }

    void setDirection(String direction) {
        this.direction = direction
    }
}
