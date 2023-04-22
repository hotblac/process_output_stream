import java.io.*;
import java.util.concurrent.TimeUnit;

public class HandledStdOutStream {

    public static void main(String[] args) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("./my_external_process.sh 5000");
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
