import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HandledBothStreams {

    static final ExecutorService streamHandlers = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("./my_external_process.sh -v 5000");
        InputStream stdOut = p.getInputStream();
        InputStream stdErr = p.getErrorStream();

        streamHandlers.execute(() -> handleStream(stdOut));
        streamHandlers.execute(() -> handleStream(stdErr));

        boolean done = p.waitFor(60, TimeUnit.SECONDS);
        if (done) {
            System.out.println("Process completed successfully");
        } else {
            System.out.println("Process did not complete in 60 seconds");
        }
    }

    private static void handleStream(InputStream inputStream) {
        try (BufferedReader stdOutReader = new BufferedReader(new InputStreamReader(inputStream))) {
            while (stdOutReader.readLine() != null) {
                // Just reading the line is enough. We don't need to do anything else with it.
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
