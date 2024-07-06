package q5.answer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Printer implements Runnable {

    private final Thread thread;
    private final Result result;
    private final Lock lock;
    private final Condition readerCondition;

    public Printer(String name, Result result, Lock lock, Condition readerCondition) {
        this.thread = new Thread(this, name);
        this.result = result;
        this.lock = lock;
        this.readerCondition = readerCondition;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();

                if (!result.canPrint()) { // 출력할 수 없다면
                    readerCondition.signal(); // reader 쓰레드 깨우기
                } else {
                    result.print(); // 출력
                    return; // 쓰레드 종료
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void start() {
        thread.start();
    }
}
