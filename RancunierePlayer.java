class RancunierePlayer extends Player {
    private boolean betrayed;

    public RancunierePlayer(String name) {
        super(name);
        betrayed = false;
    }

    @Override
    public boolean chooseAction() {
        return betrayed; // Trahir si trahi, sinon coopérer
    }

    @Override
    public void informOpponentAction(boolean opponentAction) {
        if (!opponentAction) {
            betrayed = true;
        }
    }

    @Override
    public void reset() {
        betrayed = false;
    }
}