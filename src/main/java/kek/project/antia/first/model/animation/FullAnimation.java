package kek.project.antia.first.model.animation;

import kek.project.antia.first.model.population.Population;
import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class FullAnimation {
    private LinkedHashMap<Integer, Population> frames;

    public FullAnimation() {
        frames = new LinkedHashMap<>();
    }

    public void addFrame(int frameNb, Population population) {
        this.frames.put(frameNb, population);
    }
}
