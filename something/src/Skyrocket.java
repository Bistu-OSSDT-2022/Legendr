import java.awt.Color;
import java.awt.Graphics2D;

class Skyrocket extends Thing {
    Skyrocket(Canvas canvas, Color color, int originX) {
        super(canvas);
        // System.out.println( "sn,1021;color,"+color.getRGB()+";x,"+originX+";"
        // );
        this.color = color;
        width = canvas.skyrocketWidth;
        height = canvas.skyrocketHeight;
        velocity = canvas.skyrocketVelocity;
        stateX = originX;
        stateY = canvas.getHeight();
    }

    void draw(Graphics2D g, int time) {
        // canvas.things.add( new Trace(this) );
        velocity += canvas.gravity;
        velocity *= canvas.damp;
        stateY += velocity;
        g.fillOval((int) stateX, (int) stateY, width, height);
    }

    void explode() {
        for (int i = 0; i < canvas.pieceNumber; i++) {
            double angleXy = canvas.random.nextDouble() * 2 * Math.PI;
            double angleZ = canvas.random.nextDouble() * 2 * Math.PI;
            double pieceVelocity = canvas.pieceInitialVelocity
                    * Math.cos(angleZ);
            Piece piece = new Piece(canvas, color, stateX, stateY,
                    pieceVelocity, angleXy);
            canvas.things.add(piece);
        }
        stateDead = true;
    }

}
