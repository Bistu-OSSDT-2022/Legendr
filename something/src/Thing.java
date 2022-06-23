import java.awt.Color;
import java.awt.Graphics2D;

abstract class Thing implements Comparable {
    Canvas canvas;
    int birthTime;
    Color color;
    int width, height;
    double stateX, stateY, velocity;
    boolean stateDead;

    Thing(Canvas canvas) {
        this.canvas = canvas;
        birthTime = canvas.currentTime;
    }

    void paint() {
        Graphics2D g = (Graphics2D) canvas.offscreenGraphics;
        int time = canvas.currentTime - birthTime;
        g.setColor(color);
        draw(g, time);
    }

    abstract void draw(Graphics2D g, int time);

    public int compareTo(Object o) {
        Thing thing = (Thing) o;
        if (birthTime > thing.birthTime)
            return -1;
        if (birthTime < thing.birthTime)
            return 1;
        if (equals(thing))
            return 0;
        return -1;
    }

}