import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallsPanel extends JPanel {


    static class Ball{

        int x;
        int y;
        double vx;
        double vy;
        Color color;

        Ball()
        {

            Random r = new Random();
            int max = 500;
            int min = 0;

            int maxV = 5;
            int minV = -5;

            this.x = r.nextInt((max - min) + 1) + min;
            this.y = r.nextInt((max - min) + 1) + min;
            this.vx = r.nextInt((maxV - minV) + 1) + minV;
            this.vy = r.nextInt((maxV - minV) + 1) + minV;

            color = Color.red;
        }

        @Override
        public String toString() {
            return "Ball{" +
                    "x=" + x +
                    ", y=" + y +
                    ", vx=" + vx +
                    ", vy=" + vy +
                    ", color=" + color +
                    '}';
        }
    }


    List<Ball> balls = new ArrayList<>();

    class AnimationThread extends Thread{

        boolean pause = true;

        public void run(){
            for(;;){

                    for (Ball ball:balls
                         ) {
                        ball.x += ball.vx;
                        ball.y += ball.vy;
                    }
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //przesuń kulki
                //wykonaj odbicia od ściany
                //wywołaj repaint
                //uśpij
            }
        }

        public synchronized void WakeUp() {
            pause = false;
            notify();
        }
    }

    private AnimationThread workingThread = new AnimationThread();

    BouncingBallsPanel(){
        workingThread.start();
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));

    }

    void onStart(){
        System.out.println("Start or resume animation thread");
        workingThread.WakeUp();
    }

    void onStop(){

        System.out.println("Suspend animation thread");
    }

    void onPlus(){

        Ball newBall = new Ball();
        balls.add(newBall);
        System.out.println(newBall.toString());
    }

    void onMinus(){
        System.out.println("Remove a ball");
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Ball ball:balls) {
            g2d.setColor(ball.color);
            g2d.fillOval(ball.x,ball.y,20,20);
        }

        //dalsza część metody, rysowanie itp.
    }
}