package kek.project.antia.first.model.utils;

import kek.project.antia.first.model.enumerator.Direction;

public class Directions {
    private Direction xDirection;
    private Direction yDirection;

    public Directions(){}

    public Directions(Direction xDirection, Direction yDirection) {
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }

    public Direction getxDirection() {
        return xDirection;
    }

    public void setXDirection(Direction xDirection) {
        this.xDirection = xDirection;
    }

    public Direction getyDirection() {
        return yDirection;
    }

    public void setYDirection(Direction yDirection) {
        this.yDirection = yDirection;
    }
}
