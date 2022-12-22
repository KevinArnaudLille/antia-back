package kek.project.antia.first.model.population;

import kek.project.antia.first.model.individual.Individual;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Population{
    private List<Individual> populationIndividuals;

    public Population() {
        this.populationIndividuals = new ArrayList<>();
    }

    public Population(List<Individual> populationIndividuals) {
        this.populationIndividuals = populationIndividuals;
    }

    public void addIndividual(Individual individual){
        populationIndividuals.add(individual);
    }

    public void removeIndividual(Individual individual){
        populationIndividuals.remove(individual);
    }
}
