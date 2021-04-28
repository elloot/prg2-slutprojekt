import java.io.OutputStream;

public class ScreenStreamer implements Runnable {
    private OutputStream out;

    public ScreenStreamer(OutputStream o) {
        out = o;
    }
    @Override
    public void run() {

    }
}
