class MajoriteDurPlayer extends Player {
    private int coopCount;
    private int treasonCount;

    public MajoriteDurPlayer(String name) {
        super(name);
        coopCount = 0;
        treasonCount = 0;
    }

    @Override
    public boolean chooseAction() {
        if (treasonCount > coopCount) {
            return false; // Trahir si la majorité des tours précédents ont été trahisons
        } else {
            return true; // Sinon, coopérer
        }
    }

    @Override
    public void informOpponentAction(boolean opponentAction) {
        if (opponentAction) {
            coopCount++;
        } else {
            treasonCount++;
        }
    }

    @Override
    public void reset() {
        coopCount = 0;
        treasonCount = 0;
    }
}