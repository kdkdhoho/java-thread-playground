package q5.answer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Printer implements Runnable {

    private final Thread thread;
    private final Result result;
    private final Lock lock;
    private final Condition readerCondition;
    private final Condition printerCondition;

    public Printer(String name, Result result, Lock lock, Condition readerCondition, Condition printerCondition) {
        this.thread = new Thread(this, name);
        this.result = result;
        this.lock = lock;
        this.readerCondition = readerCondition;
        this.printerCondition = printerCondition;
    }

    @Override
    public void run() {
        while (true) {
            try {
                try {
                    lock.lock();

                    if (!result.canPrint()) { // 출력할 수 없다면
                        readerCondition.signal(); // reader 쓰레드 깨우기
                        printerCondition.await(); // PrinterCondition에서 대기
                    } else {
                        result.print(); // 출력
                        return; // 쓰레드 작업 종료
                    }
                } finally {
                    lock.unlock(); // Lock 반환
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void start() {
        thread.start();
    }
}
