package kek.project.antia.first.mapper;

import kek.project.antia.first.model.individual.Circle;
import kek.project.antia.first.model.individual.Individual;
import kek.project.antia.first.model.individual.Square;
import kek.project.antia.first.model.population.Population;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class PopulationMapper {

    public Population determineIndividualsTypeInPop(Population rawPopulation) {
        Population mappedPopulation = new Population();

        mappedPopulation.setPopulationIndividuals(rawPopulation.getPopulationIndividuals().parallelStream()
                .map(individual -> {
                    if (individual.getId().contains("circle")) {
                        return new Circle(individual);
                    } else if (individual.getId().contains("square")) {
                        return new Square(individual);
                    }
                    return new Individual();
                })
                .toList());

        return mappedPopulation;
    }
}