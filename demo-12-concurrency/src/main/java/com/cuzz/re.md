- CyclicBarrier
    
我们假设有这么一个场景，每辆车只能坐个人，当车满了，就发车。
```java
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
            System.out.println("车满了，开始出发...");
        });
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 开始上车...");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
```
输出结果
```
Thread-0 开始上车...
Thread-1 开始上车...
Thread-3 开始上车...
Thread-4 开始上车...
车满了，开始出发...
Thread-5 开始上车...
Thread-7 开始上车...
Thread-2 开始上车...
Thread-6 开始上车...
车满了，开始出发...
```
- Semaphore
假设我们有 3 个停车位，6 辆车去抢
```java
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire(); // 获取一个许可
                    System.out.println(Thread.currentThread().getName() + " 抢到车位...");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + " 离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // 释放一个许可
                }
            }).start();
        }
    }
}
```
输出
```
Thread-1 抢到车位...
Thread-2 抢到车位...
Thread-0 抢到车位...
Thread-2 离开车位
Thread-0 离开车位
Thread-3 抢到车位...
Thread-1 离开车位
Thread-4 抢到车位...
Thread-5 抢到车位...
Thread-3 离开车位
Thread-5 离开车位
Thread-4 离开车位
```
## 堵塞队列你知道吗？
