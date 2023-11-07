import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Classe "Arena" qui gère les confrontations, les générations, et compare les résultats
class Arena {

    // Variables et méthodes pour gérer les paramètres du jeu et du tournoi
    private static int numRounds = 100;
    private static int numGenerations = 100;
    private static int numPlayersPerGeneration = 20;
    private static List<Player> players = new ArrayList<>();

    // Method to initialize the list of players
    public static void initializePlayers() {
    players.add(new TitForTatPlayer("TitForTat"));
    players.add(new RandomPlayer("Random"));
    players.add(new SondeurPlayer("Sondeur"));
    players.add(new GraduellePlayer("Graduelle"));
    players.add(new RancunierePlayer("Rancuniere"));
    players.add(new MajoriteMouPlayer("MajoriteMou"));
    players.add(new MajoriteDurPlayer("MajoriteDur"));
    players.add(new PeriodiqueTTCPlayer("PeriodiqueTTC"));
    players.add(new PavlovPlayer("Pavlov"));
    }   


    // Méthode pour faire s'affronter deux joueurs et obtenir le résultat
    public static int battle(Player player1, Player player2) {
        player1.reset();
        player2.reset();
        int score = 0;
        for (int i = 0; i < numRounds; i++) {
            score += Game.playRound(player1, player2);
        }
        return score;
    }



    // Méthode pour exécuter une compétition évolutionniste déterministe
    public static void runEvolutionaryCompetition() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        for (int generation = 0; generation < numGenerations; generation++) {
            System.out.println("Generation " + generation);

            // Triez les joueurs en fonction de leurs performances dans la génération précédente
            Collections.sort(players, (p1, p2) -> {
                int score1 = 0;
                int score2 = 0;
                for (int i = 0; i < numPlayersPerGeneration && i < players.size(); i++) {
                    Player player = players.get(i);
                    score1 += battle(p1, player);
                    score2 += battle(p2, player);
                }
                return Integer.compare(score2, score1); // Triez du meilleur au moins performant
            });

            // Affichez les résultats de la génération
            for (int i = 0; i < numPlayersPerGeneration && i < players.size(); i++) {
                Player bestPlayer = players.get(i);
                System.out.println(bestPlayer.name + " - Score: " + battle(bestPlayer, players.get(i)));
            }

            // Générez de nouveaux joueurs basés sur les meilleurs
            for (int i = numPlayersPerGeneration; i < numPlayersPerGeneration * 2; i++) {
                if (i < players.size()) { // Check if there are enough players
                    Player newPlayer = createNewPlayerBasedOnBest(players, i);
                    players.add(newPlayer);
                }
            }
        }
    }

    // Méthode pour créer un nouveau joueur basé sur les meilleurs joueurs existants
    public static Player createNewPlayerBasedOnBest(List<Player> players, int newPlayerIndex) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // Génération de nouveaux joueurs ici.
        // Nous allons créer une copie du joueur le mieux classé.
        Player bestPlayer = players.get(0);
        return bestPlayer.getClass().getConstructor(String.class).newInstance(bestPlayer.name + "_New");
    }

    public static void runTournamentM0() {
        System.out.println("Tournament M0 - Number of Wins");
        for (Player player1 : players) {
            int totalWins = 0;
            for (Player player2 : players) {
                if (player1 != player2) {
                    int result = battle(player1, player2);
                    if (result > numRounds / 2) {
                        totalWins++;
                    }
                }
            }
            System.out.println(player1.name + " - Wins: " + totalWins);
        }
    }

    public static void runTournamentM1() {
        System.out.println("Tournament M1 - Number of Points");
        for (Player player1 : players) {
            int totalPoints = 0;
            for (Player player2 : players) {
                if (player1 != player2) {
                    int result = battle(player1, player2);
                    totalPoints += result;
                }
            }
            System.out.println(player1.name + " - Points: " + totalPoints);
        }
    }

    public static void runRandomEncounterEvolution() {
        for (int generation = 0; generation < numGenerations; generation++) {
            List<Player> nextGeneration = new ArrayList<>();
    
            // Sélectionnez des joueurs aléatoires pour s'affronter
            Collections.shuffle(players);
    
            for (int i = 0; i < numPlayersPerGeneration; i += 2) {
                if (i + 1 < players.size()) { // Ensure there are enough players to pair up
                    Player player1 = players.get(i);
                    Player player2 = players.get(i + 1);
                    int result1 = battle(player1, player2);
                    int result2 = battle(player2, player1);
    
                    // Sélectionnez le joueur le plus performant pour la prochaine génération
                    if (result1 >= result2) {
                        nextGeneration.add(player1);
                    } else {
                        nextGeneration.add(player2);
                    }
                }
            }
    
            players = nextGeneration;
        }
    }

    public static void runTournamentM3() {
        System.out.println("Tournament M3 - Random Encounter Evolution");
        runRandomEncounterEvolution();
    }

    public static void runFermiProcessRanking() {
        for (int generation = 0; generation < numGenerations; generation++) {
            List<Player> nextGeneration = new ArrayList<>();
    
            for (Player player1 : players) {
                int totalScore = 0;
                for (Player player2 : players) {
                    if (player1 != player2) {
                        int result = battle(player1, player2);
                        totalScore += result;
                    }
                }
    
                // Appliquez le processus de Fermi pour déterminer la probabilité de reproduction
                double fitness = 1.0 / (1 + Math.exp(-totalScore / numPlayersPerGeneration));
    
                // Choisissez aléatoirement si le joueur se reproduira
                if (Math.random() < fitness) {
                    nextGeneration.add(player1);
                }
            }
    
            players = nextGeneration;
        }
    }

    public static void runTournamentM4() {
        System.out.println("Tournament M4 - Fermi Process Ranking");
        runFermiProcessRanking();
    }


    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // Lancer le tournoi le plus simple
        Player player1 = new TitForTatPlayer("TitForTat");
        Player player2 = new RandomPlayer("Random");
        int result = battle(player1, player2);
        System.out.println("Result: " + result);

        initializePlayers();
        runTournamentM0();
        runTournamentM1();
        // Compétition évolutionniste "M2"
        runEvolutionaryCompetition();
        runTournamentM3();
        runTournamentM4();
  
    }

}