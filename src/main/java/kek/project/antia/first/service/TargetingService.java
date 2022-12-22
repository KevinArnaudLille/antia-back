package kek.project.antia.first.service;

import kek.project.antia.first.model.individual.Individual;
import kek.project.antia.first.model.individual.Member;
import kek.project.antia.first.model.population.Population;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TargetingService {

    public void definePopTarget(Population population) {
        Map<Boolean, List<Member>> memberGroupedByTargetDeath = population.getPopulationIndividuals()
                .parallelStream()
                .filter(Member.class::isInstance)
                .map(Member.class::cast)
                .collect(Collectors.partitioningBy(member -> {
                    if (member.getTarget() == null) return true;
                    return member.getTarget().isDead();
                }));

                memberGroupedByTargetDeath.get(true).parallelStream()
                .forEach(member -> defineMemberTarget(member, population));
    }

    public void UpdateMemberTarget(Member member, Population population) {

    }

    private void defineMemberTarget(Member member, Population population) {
        switch (member.getBehaviorState()) {
            case FIGHTING -> defineClosestEnemyAsTarget(member, population);
        }
    }

    private void defineClosestEnemyAsTarget(Member member, Population population) {
        Member enemy = population.getPopulationIndividuals().parallelStream()
                .filter(Member.class::isInstance)
                .map(Member.class::cast)
                .filter(otherMember -> otherMember.getTeam() != member.getTeam())
                .min(Comparator.comparing(otherMember ->
                        Math.sqrt(Math.pow(otherMember.getX() - member.getX(), 2) +
                                Math.pow(otherMember.getY() - member.getY(), 2))))
                .orElse(null);

        member.setTarget(enemy);
    }
}
