class SondeurPlayer extends Player {
    private int round;

    public SondeurPlayer(String name) {
        super(name);
        round = 0;
    }

    @Override
    public boolean chooseAction() {
        if (round % 2 == 0) {
            return true; // Coopérer les tours pairs
        } else {
            return false; // Trahir les tours impairs
        }
    }

    @Override
    public void informOpponentAction(boolean opponentAction) {
        round++;
    }

    @Override
    public void reset() {
        round = 0;
    }
}