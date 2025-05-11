public class Timer {
    private long start;

    public void start() {
        start = System.nanoTime();
    }

    public double stop() {
        long end = System.nanoTime();
        return (end - start) / 1e9;
    }
}
