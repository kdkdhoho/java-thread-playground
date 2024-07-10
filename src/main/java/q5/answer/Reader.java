package q5.answer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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

    private final AtomicInteger index = new AtomicInteger(0);
    private static final AtomicInteger turn = new AtomicInteger(0);

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
                    lock.lock(); // Lock 획득 (시도)

                    if (index.get() >= data.size()) { // 모두 읽었으면
                        result.signalEnd(); // 종료 신호보내고
                        printerCondition.signal(); // printer 한번 깨워보고
                        return; // 쓰레드 작업 종료
                    }

                    while (turn.get() != id) { // 내 turn이 아니라면 무한 turn이 될 때까지 무한 반복
                        readerCondition.signal(); // 다른 Reader Thread를 깨우고
                        readerCondition.await(); // ReaderCondition에서 대기한다.
                    }

                    // 내 turn이 됐다면
                    result.add(data.get(index.getAndIncrement())); // 데이터 추가
                    turn.updateAndGet(x -> x ^ 1); // turn이 0이면 1로, 1이면 0으로 update
                    readerCondition.signal(); // 다른 reader 깨운다.
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } finally {
                lock.unlock(); // Lock 반환
            }
        }
    }

    public void start() {
        thread.start();
    }
}
