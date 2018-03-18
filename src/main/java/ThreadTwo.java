import java.awt.*;

public class ThreadTwo implements Runnable {

    private Graphics2D graphics2D;
    private String text;

    public ThreadTwo(Graphics2D graphics2D, String text) {
        this.graphics2D = graphics2D;
        this.text = text;
    }

    public void run() {
        int x = 280;
        for (int i = 0; i < text.length(); i++) {
            x -= 10;
            graphics2D.setColor(Color.GREEN);
            graphics2D.drawString(text.charAt(i)+"",x,50);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
