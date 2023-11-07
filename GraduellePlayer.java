class GraduellePlayer extends Player {
    private int consecutiveTreasons;

    public GraduellePlayer(String name) {
        super(name);
        consecutiveTreasons = 0;
    }

    @Override
    public boolean chooseAction() {
        if (consecutiveTreasons < 3) {
            return true; // Coopérer les trois premiers tours
        } else {
            return false; // Ensuite, trahir
        }
    }

    @Override
    public void informOpponentAction(boolean opponentAction) {
        if (!opponentAction) {
            consecutiveTreasons++;
        } else {
            consecutiveTreasons = 0;
        }
    }

    @Override
    public void reset() {
        consecutiveTreasons = 0;
    }
}