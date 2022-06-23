import java.applet.Applet;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App extends Applet {
    public void init() {
        Canvas canvas = new Canvas();
        canvas.setSize(getWidth(), getHeight());
        add(canvas);
        canvas.gravity = Double.parseDouble(getParameter("gravity")) / 100;
        canvas.damp = Double.parseDouble(getParameter("damp")) / 100;
        canvas.skyrocketVelocity = Double
                .parseDouble(getParameter("skyrocketVelocity")) / -1;
        canvas.skyrocketWidth = Integer
                .parseInt(getParameter("skyrocketWidth"));
        canvas.skyrocketHeight = Integer
                .parseInt(getParameter("skyrocketHeight"));
        canvas.pieceNumber = Integer.parseInt(getParameter("pieceNumber"));
        canvas.pieceInitialVelocity = Double
                .parseDouble(getParameter("pieceInitialVelocity"));
        canvas.pieceDarkerFactor = Double
                .parseDouble(getParameter("pieceDarkerFactor")) / 100;
        canvas.pieceWidth = Integer.parseInt(getParameter("pieceWidth"));
        canvas.pieceHeight = Integer.parseInt(getParameter("pieceHeight"));
        canvas.traceDarkerFactor = Double
                .parseDouble(getParameter("traceDarkerFactor")) / 100;
        canvas.init();
    }

    public static void main(String[] args) throws Exception {

        Frame frame = new Frame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Canvas canvas = new Canvas();
        canvas.setSize(400, 400);
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.show();

        canvas.gravity = 1.0;
        canvas.damp = 0.75;
        canvas.skyrocketVelocity = -82;
        canvas.skyrocketWidth = 4;
        canvas.skyrocketHeight = 8;
        canvas.pieceNumber = 200;
        canvas.pieceInitialVelocity = 72;
        canvas.pieceDarkerFactor = 0.90;
        canvas.pieceWidth = 1;
        canvas.pieceHeight = 2;
        canvas.traceDarkerFactor = 0.70;
        canvas.frameDelay = 50;

        canvas.init();

    }

}