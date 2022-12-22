package kek.project.antia.first.model.individual;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kek.project.antia.first.model.enumerator.Behavior;
import kek.project.antia.first.model.map.Pixel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Circle extends Member implements HasCollision {
    @JsonIgnore
    private int radius;

    public Circle() {
    }

    public Circle(Individual individual) {
        super(individual);
        this.setHealthPoint(20);
        this.setDead(false);
        this.setDamage(1);
        this.setReach(2);
        this.radius = 10;
        this.setAccelerationX(1);
        this.setAccelerationY(1);
        this.setSpeedX(0);
        this.setSpeedY(0);
        this.setMaxSpeed(3);
        this.setTargetAccessible(true);
        this.setBehaviorState(Behavior.FIGHTING);

        this.setArea(computeArea(this.getX(), this.getY()));
        this.setCircumference(computeCircumference(this.getX(), this.getY()));
    }

    public List<Pixel> computeArea(int x, int y) {
        List<Pixel> funcArea = new ArrayList<>();
        for (int i = y - radius; i < y + radius; i++) {
            for (int j = x; ((j - x) * (j - x) + (i - y) * (i - y)) <= (radius * radius); j--) {
                funcArea.add(new Pixel(j, i));
            }
            for (int j = x + 1; (j - x) * (j - x) + (i - y) * (i - y) <= (radius * radius); j++) {
                funcArea.add(new Pixel(j, i));
            }
        }
//        System.out.println("Circle " + super.getId() + " area: " + area);
        return funcArea;
    }

    public void computeAreaThenSet(int x, int y) {
        List<Pixel> funcArea = new ArrayList<>();
        for (int i = y - radius; i < y + radius; i++) {
            for (int j = x; ((j - x) * (j - x) + (i - y) * (i - y)) <= (radius * radius); j--) {
                funcArea.add(new Pixel(j, i));
            }
            for (int j = x + 1; (j - x) * (j - x) + (i - y) * (i - y) <= (radius * radius); j++) {
                funcArea.add(new Pixel(j, i));
            }
        }
//        System.out.println("Circle " + super.getId() + " area: " + area);
        this.setArea(funcArea);
    }

    public List<Pixel> computeCircumference(int x, int y) {
        List<Pixel> funcCircumference = new ArrayList<>();
        for (int i = y - radius; i <= y + radius; i++) {
            for (int j = x - radius; j <= x + radius; j++) {
                double d = Math.sqrt((j - x) * (j - x) + (y - i) * (y - i));
                if (d == this.radius) {
                    funcCircumference.add(new Pixel(j, i));
                }
            }
        }
        return funcCircumference;
    }

        public void computeCircumferenceThenSet ( int x, int y){
            List<Pixel> funcCircumference = new ArrayList<>();
            for (int i = y - radius; i <= y + radius; i++) {
                for (int j = x - radius; j <= x + radius; j++) {
                    double d = Math.sqrt((j - x) * (j - x) + (y - i) * (y - i));
                    if (d == this.radius) {
                        funcCircumference.add(new Pixel(j, i));
                    }
                }
            }

//        System.out.println("Circle " + super.getId() + " circumference: " + circumference);
            this.setCircumference(funcCircumference);
        }
    }
