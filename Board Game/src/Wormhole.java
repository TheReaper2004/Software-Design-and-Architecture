public class Wormhole {

    private final int start;
    private final int end;

    public Wormhole(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int teleport(int position) {
        if (position == start) {
            return end;
        }

        if (position == end) {
            return start;
        }

        return -1;
    }
}