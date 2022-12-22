package kek.project.antia.first.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kek.project.antia.first.model.animation.FullAnimation;
import kek.project.antia.first.model.map.Maps;
import kek.project.antia.first.model.population.Population;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimCalculationService {

    @Autowired
    TargetingService targetingService;

    @Autowired
    MovingService movingService;

    @Autowired
    CollisionService collisionService;

    @Autowired
    FightingService fightingService;

    ObjectMapper objectMapper = new ObjectMapper();

    public FullAnimation generateFullAnimation(Population population, Maps maps) throws JsonProcessingException {

        FullAnimation anim = new FullAnimation();
        boolean animationShouldContinue = true;
        int currentFrame = 0;

        // Add first frame
        copyThenAddFrameToAnim(population, anim, currentFrame);

        while (animationShouldContinue) {
            currentFrame++;

            // Compute target
            targetingService.definePopTarget(population);

            // Compute space presence
            collisionService.calculateCircumAndArea(population);
            maps.generateCollisionMap(population);

            // Compute move
            movingService.movePopulation(population, maps);

            // Compute damage
            fightingService.computeAllDamage(population);

            // Set dead
            fightingService.setDead(population);

            copyThenAddFrameToAnim(population, anim, currentFrame);
            System.out.println(String.format(
                    "+++++ FRAME %s calculation done",
                    currentFrame
            ));

            if (currentFrame >= 500)
                animationShouldContinue = false;
        }
        return anim;
    }

    private void copyThenAddFrameToAnim(Population population, FullAnimation anim, int currentFrame) throws JsonProcessingException {
        // Copy frame
        Population frameToAdd = objectMapper.readValue(
                objectMapper.writeValueAsString(population), Population.class
        );
        // Add frame to anim
        anim.addFrame(currentFrame, frameToAdd);
    }
}