package kek.project.antia.first.service;

import kek.project.antia.first.model.individual.Circle;
import kek.project.antia.first.model.individual.Member;
import kek.project.antia.first.model.population.Population;
import org.springframework.stereotype.Service;

@Service
public class FightingService {

    public void computeAllDamage(Population population){
        population.getPopulationIndividuals().parallelStream()
                .filter(Member.class::isInstance)
                .map(Member.class::cast)
                .filter(member -> !member.isDead())
                .forEach(this::dealDamage);
    }

    public void dealDamage(Member member){
        double targetDistance = Math.sqrt(Math.pow(member.getTarget().getX() - member.getX(), 2) +
                Math.pow(member.getTarget().getY() - member.getY(), 2));

        if (targetDistance <= member.getReach() + ((Circle)member).getRadius()*2)
            member.getTarget().setHealthPoint(member.getTarget().getHealthPoint() - member.getDamage());
    }

    public void setDead(Population population){
        population.getPopulationIndividuals().parallelStream()
                .filter(Member.class::isInstance)
                .map(Member.class::cast)
                .filter(member -> !member.isDead() && member.getHealthPoint() <= 0)
                .forEach(member -> member.setDead(true));
    }
}
