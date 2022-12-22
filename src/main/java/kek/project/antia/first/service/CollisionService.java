package kek.project.antia.first.service;

import kek.project.antia.first.model.individual.HasCollision;
import kek.project.antia.first.model.individual.Individual;
import kek.project.antia.first.model.individual.Member;
import kek.project.antia.first.model.individual.behavior.MovingWill;
import kek.project.antia.first.model.map.Maps;
import kek.project.antia.first.model.map.Pixel;
import kek.project.antia.first.model.population.Population;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CollisionService {

    public void calculateCircumAndArea(Population population){
        population.getPopulationIndividuals().parallelStream()
                .filter(HasCollision.class::isInstance)
                .map(HasCollision.class::cast)
                .forEach(hc -> {
                    hc.computeAreaThenSet(hc.getX(), hc.getY());
                    hc.computeCircumferenceThenSet(hc.getX(), hc.getY());
                });
    }

    public boolean isMoveTriggeringCollision(HasCollision hc, MovingWill movingWill, Maps maps){
        List<Pixel> reduceCollisionMap = new ArrayList<>(maps.getCollisionMap());
        reduceCollisionMap.removeAll(hc.getArea());

        return !Collections.disjoint(reduceCollisionMap,hc.computeCircumference(movingWill.getNextX(), movingWill.getNextY()));
    }
}
