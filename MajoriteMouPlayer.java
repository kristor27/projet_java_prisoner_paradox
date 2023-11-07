class MajoriteMouPlayer extends Player {
    private int coopCount;
    private int treasonCount;

    public MajoriteMouPlayer(String name) {
        super(name);
        coopCount = 0;
        treasonCount = 0;
    }

    @Override
    public boolean chooseAction() {
        if (coopCount > treasonCount) {
            return true; // Coopérer si la majorité des tours précédents ont été coopératifs
        } else {
            return false; // Sinon, trahir
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
