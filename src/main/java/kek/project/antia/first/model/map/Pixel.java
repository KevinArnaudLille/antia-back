package kek.project.antia.first.model.map;

import lombok.Data;

import java.util.Objects;

@Data
public class Pixel {
    private int x;
    private int y;

    public Pixel() {
    }

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;

        Pixel pixel = (Pixel) o;
        // field comparison
        return this.x == pixel.getX()
                && this.y == pixel.getY();
    }
}
