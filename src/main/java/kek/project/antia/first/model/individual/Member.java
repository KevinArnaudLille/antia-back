package kek.project.antia.first.model.individual;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kek.project.antia.first.model.enumerator.Behavior;
import kek.project.antia.first.model.map.Pixel;
import lombok.Data;

import java.util.List;

@Data
public class Member extends Individual {
    @JsonIgnore
    private int healthPoint;

    @JsonIgnore
    private int damage;
    @JsonIgnore
    private int reach;

    @JsonIgnore
    private Behavior behaviorState;

    @JsonIgnore
    private Member target;
    @JsonIgnore
    private boolean isTargetAccessible;

    @JsonIgnore
    private List<Pixel> area;
    @JsonIgnore
    private List<Pixel> circumference;

    @JsonIgnore
    private int accelerationX;
    @JsonIgnore
    private int speedX;
    @JsonIgnore
    private int accelerationY;
    @JsonIgnore
    private int speedY;
    @JsonIgnore
    private int maxSpeed;
    @JsonIgnore
    private boolean isStop;

    public Member() {
    }

    ;

    public Member(int targetX, int targetY, List<Pixel> area, List<Pixel> circumference) {
        this.area = area;
        this.circumference = circumference;
    }

    public Member(Individual individual) {
        super(individual);
        this.area = area;
        this.circumference = circumference;
        this.isStop = false;
    }
}
