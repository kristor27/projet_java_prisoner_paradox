// Classe "Game" qui définit les règles du jeu
class Game {
    // Méthode pour jouer un tour et déterminer les résultats
    public static int playRound(Player player1, Player player2) {
        boolean action1 = player1.chooseAction();
        boolean action2 = player2.chooseAction();

        if (action1 && action2) {
            // Les deux joueurs coopèrent
            return 3;
        } else if (!action1 && !action2) {
            // Les deux joueurs trahissent
            return 1;
        } else {
            // Un joueur coopère, l'autre trahit
            if (action1) {
                player1.informOpponentAction(false);
                player2.informOpponentAction(true);
            } else {
                player1.informOpponentAction(true);
                player2.informOpponentAction(false);
            }
            return 0;
        }
    }
}