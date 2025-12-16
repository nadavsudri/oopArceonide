public class Counter {
    private int count;
    public void increase(int n) {
        count += n;
    }
    public void decrease(int n) {

        count -= n;
    }
    public int getCount() {
        return count;
    }
}
