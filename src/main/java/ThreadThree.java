import java.awt.*;

public class ThreadThree implements Runnable {

    private Graphics2D graphics2D;
    private String text;

    public ThreadThree(Graphics2D graphics2D, String text) {
        this.graphics2D = graphics2D;
        this.text = text;
    }

    public void run() {
        int x = 150;
        for (int i = 0; i < text.length(); i++) {
            if (i%2 == 0)
                x+=i*10;
            else
                x-=i*10;
            graphics2D.setColor(Color.GREEN);
            graphics2D.drawString(text.charAt(i)+"",x,80);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
