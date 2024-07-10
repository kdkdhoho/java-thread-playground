package q5.answer;

import java.util.concurrent.atomic.AtomicInteger;

public class Result {

    private final int targetEndCount;
    private final StringBuffer buffer = new StringBuffer();

    private final AtomicInteger currentEndCount = new AtomicInteger(0);

    Result(int targetEndCount) {
        this.targetEndCount = targetEndCount;
    }

    public void add(String value) {
        if (value.isBlank()) {
            buffer.append(System.lineSeparator());
        } else {
            buffer.append(value).append(" ");
        }
    }

    public void signalEnd() {
        currentEndCount.getAndIncrement();
    }

    public boolean canPrint() {
        return currentEndCount.get() == targetEndCount;
    }

    public void print() {
        System.out.println(buffer);
    }
}
