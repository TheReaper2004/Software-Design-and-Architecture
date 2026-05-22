public class Player {

    private final String name;
    private int position;
    private final int endPosition;
    private int totalMovement;
    private int turns;

    public Player(String name, int startPosition, int endPosition) {
        this.name = name;
        this.position = startPosition;
        this.endPosition = endPosition;
        this.totalMovement = 0;
        this.turns = 0;
    }

    public void move(int diceRoll) {
        turns++;
        totalMovement += diceRoll;

        if (position < endPosition) {
            position += diceRoll;

            if (position > endPosition) {
                position = endPosition;
            }
        } else {
            position -= diceRoll;

            if (position < endPosition) {
                position = endPosition;
            }
        }
    }

    public void loseTurn() {
        turns++;
    }

    public int calculateNewPosition(int diceRoll) {
        if (position < endPosition) {
            int newPosition = position + diceRoll;

            if (newPosition > endPosition) {
                return endPosition;
            }

            return newPosition;
        } else {
            int newPosition = position - diceRoll;

            if (newPosition < endPosition) {
                return endPosition;
            }

            return newPosition;
        }
    }

    public boolean hasWon() {
        return position == endPosition;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getTotalMovement() {
        return totalMovement;
    }

    public int getTurns() {
        return turns;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}