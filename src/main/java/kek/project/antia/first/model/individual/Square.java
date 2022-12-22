package kek.project.antia.first.model.individual;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kek.project.antia.first.model.enumerator.Behavior;
import kek.project.antia.first.model.map.Pixel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Square extends Member implements HasCollision {
    @JsonIgnore
    private int length;

    public Square() {
    }

    public Square(Individual individual) {
        super(individual);
        this.setHealthPoint(20);
        this.setDead(false);
        this.length = 20;
        this.setAccelerationX(1);
        this.setAccelerationY(1);
        this.setSpeedX(0);
        this.setSpeedY(0);
        this.setMaxSpeed(4);
        this.setTargetAccessible(true);
        this.setBehaviorState(Behavior.FIGHTING);

        this.setArea(computeArea(this.getX(), this.getY()));
        this.setCircumference(computeCircumference(this.getX(), this.getY()));
    }

    public List<Pixel> computeArea(int x, int y) {
        List<Pixel> funcArea = new ArrayList<>();
        for (int i = x - (this.length / 2); i < x + (this.length / 2); i++) {
            for (int j = y - (this.length / 2); j < y + (this.length / 2); j++) {
                funcArea.add(new Pixel(i, j));
            }
        }
//        System.out.println("Square " + super.getId() + " area: " + area);
        return funcArea;
    }

    public void computeAreaThenSet(int x, int y) {
        List<Pixel> funcArea = new ArrayList<>();
        for (int i = x - (this.length / 2); i < x + (this.length / 2); i++) {
            for (int j = y - (this.length / 2); j < y + (this.length / 2); j++) {
                funcArea.add(new Pixel(i, j));
            }
        }
//        System.out.println("Square " + super.getId() + " area: " + area);
        this.setArea(funcArea);

    }

    public List<Pixel> computeCircumference(int x, int y) {
        List<Pixel> funcCircumference = new ArrayList<>();
        for (int i = x - (this.length / 2); i <= x + (this.length / 2); i++) {
            funcCircumference.add(new Pixel(i, y - (this.length / 2)));
            funcCircumference.add(new Pixel(i, y + (this.length / 2)));
        }
        for (int j = y - (this.length / 2); j <= y + (this.length / 2); j++) {
            funcCircumference.add(new Pixel(x - (this.length / 2), j));
            funcCircumference.add(new Pixel(x + (this.length / 2), j));
        }

//        System.out.println("Square " + super.getId() + " circumference: " + circumference);
        return funcCircumference;
    }

    public void computeCircumferenceThenSet(int x, int y) {
        List<Pixel> funcCircumference = new ArrayList<>();
        for (int i = x - (this.length / 2); i <= x + (this.length / 2); i++) {
            funcCircumference.add(new Pixel(i, y - (this.length / 2)));
            funcCircumference.add(new Pixel(i, y + (this.length / 2)));
        }
        for (int j = y - (this.length / 2); j <= y + (this.length / 2); j++) {
            funcCircumference.add(new Pixel(x - (this.length / 2), j));
            funcCircumference.add(new Pixel(x + (this.length / 2), j));
        }

//        System.out.println("Square " + super.getId() + " circumference: " + circumference);
        this.setCircumference(funcCircumference);
    }
}
