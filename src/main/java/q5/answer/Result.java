package q5.answer;

public class Result {

    private final int targetEndCount;
    private final StringBuilder builder = new StringBuilder();

    private int currentEndCount = 0;

    Result(int targetEndCount) {
        this.targetEndCount = targetEndCount;
    }

    public void add(String value) {
        if (value.isBlank()) {
            builder.append(System.lineSeparator());
        } else {
            builder.append(value).append(" ");
        }
    }

    public void signalEnd() {
        currentEndCount++;
    }

    public boolean canPrint() {
        return currentEndCount == targetEndCount;
    }

    public void print() {
        System.out.println(builder);
    }
}
