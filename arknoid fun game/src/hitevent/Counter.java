package hitevent;

/**
 * hitevent.Counter class.
 */
public class Counter {
    //member
    private int counter;

    /**
     * @param number the number
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * add number to current count.
     *
     * @param number the number to add
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }


    /**
     * subtract number from current count.
     *
     * @param number the number to subtract
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * get current count.
     *
     * @return the current count.
     */
    public int getValue() {
        return this.counter;
    }

    /**
     * change the number of the counter.
     *
     * @param num the number to change to
     */
    public void setCounter(int num) {
        this.counter = num;
    }

    /**
     * @return the new type of printing
     */
    public String toString() {
        String stringRank;
        stringRank = ("num of bumps " + this.getValue());
        return stringRank;
    }
}
