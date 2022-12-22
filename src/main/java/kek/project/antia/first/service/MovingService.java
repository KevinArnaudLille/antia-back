package kek.project.antia.first.service;

import kek.project.antia.first.model.enumerator.Direction;
import kek.project.antia.first.model.individual.HasCollision;
import kek.project.antia.first.model.individual.Member;
import kek.project.antia.first.model.individual.behavior.MovingWill;
import kek.project.antia.first.model.map.Maps;
import kek.project.antia.first.model.population.Population;
import kek.project.antia.first.model.utils.Directions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovingService {

    @Autowired
    CollisionService collisionService;

    @Autowired
    TargetingService targetingService;

    public void movePopulation(Population population, Maps maps) {
        population.getPopulationIndividuals().parallelStream()
                .filter(Member.class::isInstance)
                .map(Member.class::cast)
                .forEach(member ->  computeMovingMember(member,population,maps));
    }

    private void computeMovingMember(Member member, Population population, Maps maps) {
        // Define move will
        MovingWill movingWill = new MovingWill();
        Directions directions = getIndividualDirections(member);
        movingWill.setNextX(calculateNewX(member, directions.getxDirection()));
        movingWill.setNextY(calculateNewY(member, directions.getyDirection()));

        // Check if move allowed
        if (member instanceof HasCollision)
            movingWill.setBlocked(collisionService.isMoveTriggeringCollision((HasCollision) member, movingWill, maps));

        // If not block: move as expected
        if (!movingWill.isBlocked()) {
            moveMember(member, movingWill);
            member.setStop(false);
            return;
        }

        // If block:
            // If first time blocked, wait for one frame
        if (!member.isStop()){
            member.setStop(true);
            return;
        }

            // If was previously blocked, update target



    }

    private int calculateNewX(Member member, Direction xDirection) {
        if (xDirection == Direction.STOP) return member.getX();
        if (xDirection == Direction.FRONT) {
            int newSpeedX = member.getSpeedX() + member.getAccelerationX();
            if (newSpeedX <= member.getMaxSpeed())
                member.setSpeedX(newSpeedX);
        } else if (xDirection == Direction.BACK) {
            int newSpeedX = member.getSpeedX() - member.getAccelerationX();
            if (Math.abs(newSpeedX) <= member.getMaxSpeed())
                member.setSpeedX(newSpeedX);
        }
        return member.getX() + member.getSpeedX();
    }

    private int calculateNewY(Member member, Direction yDirection) {
        if (yDirection == Direction.STOP) return member.getY();
        if (yDirection == Direction.FRONT) {
            int newSpeedY = member.getSpeedY() + member.getAccelerationY();
            if (newSpeedY <= member.getMaxSpeed())
                member.setSpeedY(newSpeedY);
        } else if (yDirection == Direction.BACK) {
            int newSpeedY = member.getSpeedY() - member.getAccelerationY();
            if (Math.abs(newSpeedY) <= member.getMaxSpeed())
                member.setSpeedY(newSpeedY);
        }
        return member.getY() + member.getSpeedY();
    }

    private Directions getIndividualDirections(Member member) {
        Directions directions = new Directions();
        if ((member.getTarget().getX() - member.getX()) == 0) {
            directions.setXDirection(Direction.STOP);
        } else if ((member.getTarget().getX() - member.getX()) > 0) {
            directions.setXDirection(Direction.FRONT);
        } else {
            directions.setXDirection(Direction.BACK);
        }
        if ((member.getTarget().getY() - member.getY()) == 0) {
            directions.setYDirection(Direction.STOP);
        } else if ((member.getTarget().getY() - member.getY()) > 0) {
            directions.setYDirection(Direction.FRONT);
        } else {
            directions.setYDirection(Direction.BACK);
        }

        return directions;
    }

    private void moveMember(Member member, MovingWill movingWill) {
        member.setX(movingWill.getNextX());
        member.setY(movingWill.getNextY());
    }
}
