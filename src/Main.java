import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("./my_external_process.sh 5000");
        boolean done = p.waitFor(60, TimeUnit.SECONDS);
        if (done) {
            System.out.println("Process completed successfully");
        } else {
            System.out.println("Process did not complete in 60 seconds");
        }
    }
}