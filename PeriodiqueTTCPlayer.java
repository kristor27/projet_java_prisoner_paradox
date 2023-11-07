class PeriodiqueTTCPlayer extends Player {
    private int round;

    public PeriodiqueTTCPlayer(String name) {
        super(name);
        round = 0;
    }

    @Override
    public boolean chooseAction() {
        round++;
        if (round % 3 == 0) {
            return false; // Trahir tous les trois tours
        } else {
            return true; // Coopérer sinon
        }
    }

    @Override
    public void informOpponentAction(boolean opponentAction) {
        // Ne fait rien
    }

    @Override
    public void reset() {
        round = 0;
    }
}