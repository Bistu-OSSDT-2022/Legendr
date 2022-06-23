import java.awt.Color;
import java.awt.Graphics2D;

class Trace extends Thing {
    double velocityX, velocityY;

    Trace(Thing thing) {
        super(thing.canvas);
        this.color = thing.color;
        this.width = thing.width;
        this.height = thing.height;
        stateX = thing.stateX;
        stateY = thing.stateY;
    }

    void draw(Graphics2D g, int time) {

        g.fillRect((int) stateX, (int) stateY, width, height);

        color = new Color((int) (color.getRed() * canvas.traceDarkerFactor),
                (int) (color.getGreen() * canvas.traceDarkerFactor),
                (int) (color.getBlue() * canvas.traceDarkerFactor));
        if (color.getRed() + color.getGreen() + color.getBlue() < 10)
            stateDead = true;

    }

}