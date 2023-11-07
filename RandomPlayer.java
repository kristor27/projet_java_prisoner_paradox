class RandomPlayer extends Player {
    public RandomPlayer(String name) {
        super(name);
    }

    @Override
    public boolean chooseAction() {
        return Math.random() < 0.5;
    }

    @Override
    public void informOpponentAction(boolean opponentAction) {
        // Ne fait rien
    }

    @Override
    public void reset() {
        // Ne fait rien
    }
}
