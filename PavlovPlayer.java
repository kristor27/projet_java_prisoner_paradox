class PavlovPlayer extends Player {
    private boolean lastAction;

    public PavlovPlayer(String name) {
        super(name);
        lastAction = true;
    }

    @Override
    public boolean chooseAction() {
        return lastAction; // Répète l'action précédente de l'adversaire
    }

    @Override
    public void informOpponentAction(boolean opponentAction) {
        lastAction = opponentAction;
    }

    @Override
    public void reset() {
        lastAction = true;
    }
}