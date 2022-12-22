package kek.project.antia.first.model.enumerator;

public enum Direction {
    FRONT(1),
    BACK(-1),
    STOP(0);

    private int direction;

    Direction(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }
}
