package kek.project.antia.first.model.individual;

import kek.project.antia.first.model.map.Pixel;

import java.util.ArrayList;
import java.util.List;

public interface HasCollision {

    List<Pixel> computeArea(int x, int y);
    void computeAreaThenSet(int x, int y);
    List<Pixel> computeCircumference(int x, int y);
    void computeCircumferenceThenSet(int x, int y);
    int getX();
    int getY();
    List<Pixel> getCircumference();
    List<Pixel> getArea();
}
