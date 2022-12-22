package kek.project.antia.first.model.individual;

import kek.project.antia.first.model.enumerator.Team;
import kek.project.antia.first.model.map.Pixel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Individual {
    private String id;
    private int x;
    private int y;

    private Team team;

    private boolean isDead;

    public Individual(){}

    public Individual (Individual individual){
        this.id = individual.getId();
        this.x = individual.getX();
        this.y = individual.getY();
        this.team = individual.getTeam();
    }


}
