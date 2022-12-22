package kek.project.antia.first.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kek.project.antia.first.mapper.PopulationMapper;
import kek.project.antia.first.model.animation.FullAnimation;
import kek.project.antia.first.model.individual.Individual;
import kek.project.antia.first.model.map.Maps;
import kek.project.antia.first.model.population.Population;
import kek.project.antia.first.service.AnimCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class AnimationController {

    @Autowired
    PopulationMapper populationMapper;

    @Autowired
    AnimCalculationService animCalculation;

    @MessageMapping("/animation")
    @SendTo("/topic")
    public FullAnimation animationRequested(Population rawPopulation) throws JsonProcessingException {
        System.out.println("Received population is:" + rawPopulation.getPopulationIndividuals().stream().map(Individual::getId).toList().toString());
        Population mappedPopulation = populationMapper.determineIndividualsTypeInPop(rawPopulation);
        System.out.println("Mapped population is:" + mappedPopulation.getPopulationIndividuals().stream().map(Individual::getId).toList().toString());

        Maps maps = new Maps(600, 300);

        return animCalculation.generateFullAnimation(mappedPopulation, maps);
    }
}
