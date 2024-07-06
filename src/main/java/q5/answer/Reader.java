package q5.answer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Reader implements Runnable {

    private final int id;
    private final Thread thread;
    private final Result result;
    private final Lock lock;
    private final Condition readerCondition;
    private final Condition printerCondition;
    private final List<String> data;

    private volatile int index = 0;
    private static volatile int turn = 0;

    public Reader(int id, String name, List<String> data, Result result, Lock lock, Condition readerCondition, Condition printerCondition) {
        this.id = id;
        this.data = new ArrayList<>(data);
        this.lock = lock;
        this.readerCondition = readerCondition;
        this.printerCondition = printerCondition;
        this.thread = new Thread(this, name);
        this.result = result;
    }

    @Override
    public void run() {
        while (true) {
            try {
                try {
                    lock.lock();

                    if (index >= data.size()) { // 모두 읽었으면
                        result.signalEnd(); // 종료 신호보내고
                        printerCondition.signal(); // printer 한번 깨워보고
                        return; // 쓰레드 종료
                    }

                    while (turn != id) {
                        readerCondition.signal();
                        readerCondition.await();
                    }

                    result.add(data.get(index++)); // 데이터 추가
                    turn ^= 1; // 순서 변경
                    readerCondition.signal(); // 다른 reader 깨우기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
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
