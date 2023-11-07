// Classe abstraite "Player" qui spécifie les stratégies des joueurs
abstract class Player {
    String name;

    public Player(String name) {
        this.name = name;
    }

    // Méthode pour choisir une action (Coopérer ou Trahir)
    public abstract boolean chooseAction();

    // Méthode pour informer le joueur de l'action de l'adversaire
    public abstract void informOpponentAction(boolean opponentAction);

    // Méthode pour réinitialiser l'état du joueur pour un nouveau tour
    public abstract void reset();
}