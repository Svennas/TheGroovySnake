package program

import java.awt.Color

class Snake {
    ArrayList<List<Integer>> body
    def direction
    Color colour

    Snake(ArrayList<List<Integer>> body, String direction, Color colour) {
        this.body = body
        this.direction = direction
        this.colour = colour
    }

    void addToHead(int[] newHead) {
        getBody().add(0, newHead)
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
