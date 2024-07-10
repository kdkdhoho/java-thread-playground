package q5.answer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static final Lock lock = new ReentrantLock();
    private static final Condition readerCondition = lock.newCondition();
    private static final Condition printerCondition = lock.newCondition();

    public static void main(String[] args) throws URISyntaxException, IOException {
        URL resource1 = Main.class.getClassLoader().getResource("file1.txt");
        URL resource2 = Main.class.getClassLoader().getResource("file2.txt");
        List<String> file1 = Files.readAllLines(Paths.get(resource1.toURI()));
        List<String> file2 = Files.readAllLines(Paths.get(resource2.toURI()));

        Result result = new Result(2);
        Reader reader1 = new Reader(0, "reader0", file1, result, lock, readerCondition, printerCondition);
        Reader reader2 = new Reader(1, "reader1", file2, result, lock, readerCondition, printerCondition);
        Printer printer = new Printer("printer", result, lock, readerCondition, printerCondition);
        reader1.start();
        reader2.start();
        printer.start();
    }
}
