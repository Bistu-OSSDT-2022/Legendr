import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.Timer;

class Canvas extends java.awt.Canvas {
    double gravity = 1.0;
    double damp = 0.75;
    double skyrocketVelocity = -82;
    int skyrocketWidth = 4;
    int skyrocketHeight = 8;
    int pieceNumber = 200;
    double pieceInitialVelocity = 72;
    double pieceDarkerFactor = 0.90;
    int pieceWidth = 1;
    int pieceHeight = 2;
    double traceDarkerFactor = 0.70;
    int frameDelay = 40;
    int border = 10;

    Random random = new Random(System.currentTimeMillis());
    int currentTime;
    TreeSet things = new TreeSet();
    Skyrocket currentSkyrocket;
    Image offscreenImage;
    Graphics offscreenGraphics;

    void init() {
        setBackground(Color.BLACK);
        offscreenImage = createImage(getWidth(), getHeight());
        offscreenGraphics = offscreenImage.getGraphics();
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                onMousePress(e);
            }

            public void mouseReleased(MouseEvent e) {
                onMouseRelease(e);
            }
        });
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                onKeyPress(e);
            }
        });
        new Timer(frameDelay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        }).start();
        requestFocus();
    }

    void onMousePress(MouseEvent e) {

        if (currentSkyrocket != null)
            currentSkyrocket.explode();

        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        int max = r > g ? r : g;
        max = max > b ? max : b;
        double brighter = 255.0 / max;
        r = (int) (r * brighter);
        g = (int) (g * brighter);
        b = (int) (b * brighter);
        Color color = new Color(r, g, b);

        currentSkyrocket = new Skyrocket(this, color, e.getX());
        things.add(currentSkyrocket);

    }

    void onMouseRelease(MouseEvent e) {
        if (currentSkyrocket != null) {
            currentSkyrocket.explode();
            currentSkyrocket = null;
        }
    }

    void onKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            gravity += 0.01;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            gravity -= 0.01;
            if (gravity < 0)
                gravity = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            damp += 0.01;
            if (damp > 1)
                damp = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            damp -= 0.01;
            if (damp < 0)
                damp = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            skyrocketVelocity -= 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            skyrocketVelocity += 1;
            if (skyrocketVelocity > 0)
                skyrocketVelocity = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            skyrocketWidth += 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_F) {
            skyrocketWidth -= 1;
            if (skyrocketWidth < 1)
                skyrocketWidth = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_T) {
            skyrocketHeight += 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_G) {
            skyrocketHeight -= 1;
            if (skyrocketHeight < 1)
                skyrocketHeight = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_Y) {
            pieceNumber += 10;
        }
        if (e.getKeyCode() == KeyEvent.VK_H) {
            pieceNumber -= 10;
            if (pieceNumber < 0)
                pieceNumber = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_U) {
            pieceInitialVelocity += 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            pieceInitialVelocity -= 1;
            if (pieceInitialVelocity < 0)
                pieceInitialVelocity = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_I) {
            pieceDarkerFactor += 0.01;
            if (pieceDarkerFactor > 1)
                pieceDarkerFactor = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            pieceDarkerFactor -= 0.01;
            if (pieceDarkerFactor < 0)
                pieceDarkerFactor = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            pieceWidth += 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            pieceWidth -= 1;
            if (pieceWidth < 1)
                pieceWidth = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            pieceHeight += 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_SEMICOLON) {
            pieceHeight -= 1;
            if (pieceHeight < 1)
                pieceHeight = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_OPEN_BRACKET) {
            traceDarkerFactor += 0.01;
            if (traceDarkerFactor > 1)
                traceDarkerFactor = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_QUOTE) {
            traceDarkerFactor -= 0.01;
            if (traceDarkerFactor < 0)
                traceDarkerFactor = 0;
        }
        System.out.println("g" + (int) (gravity * 100) + " " + "d"
                + (int) (damp * 100) + " " + "sv"
                + (int) (-skyrocketVelocity * 1) + " " + "sw"
                + (int) (skyrocketWidth * 1) + " " + "sh"
                + (int) (skyrocketHeight * 1) + " " + "pn"
                + (int) (pieceNumber * 1) + " " + "pv"
                + (int) (pieceInitialVelocity * 1) + " " + "pf"
                + (int) (pieceDarkerFactor * 100) + " " + "pw"
                + (int) (pieceWidth * 1) + " " + "ph" + (int) (pieceHeight * 1)
                + " " + "tf" + (int) (traceDarkerFactor * 100));
    }

    public void update(Graphics g) {
        paint(g);
    }

    long totalPaintTime;

    public void paint(Graphics g) {
        if (offscreenImage == null)
            return;
        long time1 = System.currentTimeMillis();
        System.gc();
        long time2 = System.currentTimeMillis();
        offscreenGraphics.clearRect(0, 0, getWidth(), getHeight());
        TreeSet things2 = new TreeSet();
        while (!things.isEmpty()) {
            Thing thing = (Thing) things.first();
            things.remove(thing);
            thing.paint();
            if (thing.stateDead || thing.stateX < -border
                    || thing.stateY > getWidth() + border
                    || thing.stateY < -border
                    || thing.stateY > getHeight() + border) {
            } else {
                things2.add(thing);
            }
        }
        things = things2;
        g.drawImage(offscreenImage, 0, 0, this);
        long time3 = System.currentTimeMillis();
        totalPaintTime += time3 - time1;
        currentTime++;
        // System.out.println(
        // "("+(time2-time1)+") "+totalPaintTime+" / "+currentTime+" = "+totalPaintTime/currentTime
        // );
    }

}