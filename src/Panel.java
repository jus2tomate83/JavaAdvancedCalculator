import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel implements Runnable{
    // System
    Thread gameThread;
    //FPS
    int fps = 60;
    //SCREEN
    //SCREEN SETTINGS
    final int originalTileSize = 32; //32x32 tiles
    final int scale = 2;

    public final int tileSize = originalTileSize * scale; //48

    public final int screenWidth = 768; //768
    public final int screenHeight = 576; //576

    @Override
    public void run() {
        double drawInterval = 1000000000/fps; //allocated time for every loop
        double nextDrawTime = System.nanoTime() + drawInterval; //calculate the time before the next update
        while (gameThread != null) {
            // Update infos and pos
            Button.update();
            //DEBUG
//            long drawStart = 0;
//            drawStart = System.nanoTime();
//            long drawEnd =System.nanoTime();
//            long passed = drawEnd - drawStart;
//            System.out.println("Draw Time :" + passed);

            //draw the screen
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; //convert to milli
                if (remainingTime < 0 ) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime); //wait the remaining time
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        this.setFocusable(true);//gamepanel can receive input
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
}
