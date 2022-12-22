package kek.project.antia.first.model.map;

import kek.project.antia.first.model.individual.HasCollision;
import kek.project.antia.first.model.individual.Individual;
import kek.project.antia.first.model.population.Population;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Maps {
    private int width;
    private int height;

    private int count;

    private List<Pixel> map;
    private List<Pixel> collisionMap;

    public Maps(int width, int height) {
        this.width = width;
        this.height = height;
        this.count = 0;

        map = new ArrayList<>();
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= width; j++) {
                map.add(new Pixel(i, j));
            }
        }
    }

    public void generateCollisionMap(Population population) {
        collisionMap = new ArrayList<>();
        collisionMap = population.getPopulationIndividuals().parallelStream()
                .filter(individual -> !individual.isDead())
                .filter(HasCollision.class::isInstance)
                .map(HasCollision.class::cast)
                .flatMap(hc -> hc.getArea().stream())
                .toList();
    }
}
