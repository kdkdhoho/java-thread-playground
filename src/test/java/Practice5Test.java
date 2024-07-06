import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import q5.practice.Main;

class Practice5Test {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outputStream);

    @Test
    void Question5_Practice_Check() throws Exception {
        new Main(printStream).start();

        await().atMost(5, TimeUnit.SECONDS)
                .until(() -> outputStream.size() > 0);

        String result = outputStream.toString();
        assertThat(result).isEqualTo("""
                Hello world, nice to meet you.\s
                How's your day?\s
                """);
    }
}
