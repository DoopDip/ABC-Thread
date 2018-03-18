import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main extends JFrame {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("ABC Thread");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(new Page());
        jFrame.pack();
        jFrame.setVisible(true);
    }

    static class Page extends JPanel implements Runnable {

        public static int WIDTH = 300;
        public static int HEIGHT = 100;

        private BufferedImage bufferedImage;
        private Graphics2D graphics2D;
        private Thread thread;

        private String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        public Page() {
            super();
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setFocusable(true);
            requestFocus();
        }

        public void addNotify() {
            super.addNotify();
            if (thread == null) {
                thread = new Thread(this);
                thread.start();
            }
        }

        public void run() {
            bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
            graphics2D = (Graphics2D) bufferedImage.getGraphics();
            long startTime;
            long URDTimeMillis;
            long waitTime;
            long targetTime = 1000/30;

            new Thread(new ThreadOne(graphics2D, text)).start();
            new Thread(new ThreadTwo(graphics2D, text)).start();
            new Thread(new ThreadThree(graphics2D, text)).start();

            while (true) {
                startTime = System.nanoTime();

                graphics2D.setFont(new Font("Courier New", Font.PLAIN, 15));
                Graphics graphics = this.getGraphics();
                graphics.drawImage(bufferedImage,0, 0,null);
                graphics.dispose();

                URDTimeMillis = (System.nanoTime() - startTime)/ 1000000;
                waitTime = targetTime - URDTimeMillis;

                try {
                    Thread.sleep(waitTime);
                } catch (Exception e) {}
            }
        }
    }

}
