class TitForTatPlayer extends Player {
    private boolean lastOpponentAction;

    public TitForTatPlayer(String name) {
        super(name);
        lastOpponentAction = true;
    }

    @Override
    public boolean chooseAction() {
        return lastOpponentAction;
    }

    @Override
    public void informOpponentAction(boolean opponentAction) {
        lastOpponentAction = opponentAction;
    }

    @Override
    public void reset() {
        lastOpponentAction = true;
    }
}