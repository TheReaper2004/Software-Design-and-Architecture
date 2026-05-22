import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Wormhole> wormholes = new ArrayList<>();

    public Board() {
        wormholes.add(new Wormhole(4, 9));
        wormholes.add(new Wormhole(19, 23));
    }

    public int checkWormhole(int position) {
        for (Wormhole wormhole : wormholes) {
            int result = wormhole.teleport(position);

            if (result != -1) {
                return result;
            }
        }

        return -1;
    }
}