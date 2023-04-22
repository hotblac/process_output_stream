import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class HandledCombinedStream {

    public static void main(String[] args) throws IOException, InterruptedException {


        Process p = new ProcessBuilder("./my_external_process.sh", "-v", "5000")
                .redirectErrorStream(true)
                .start();
        InputStream stdOut = p.getInputStream();

        try (BufferedReader stdOutReader = new BufferedReader(new InputStreamReader(stdOut))) {
            while (stdOutReader.readLine() != null) {
                // Just reading the line is enough. We don't need to do anything else with it.
            }
        }
        boolean done = p.waitFor(60, TimeUnit.SECONDS);
        if (done) {
            System.out.println("Process completed successfully");
        } else {
            System.out.println("Process did not complete in 60 seconds");
        }
    }

}
