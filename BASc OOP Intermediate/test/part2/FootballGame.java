package test.part2;

public class FootballGame {
    private int roundNumber;
    private int homeGoals;
    private int homeBehinds;
    private int awayGoals;
    private int awayBehinds;
    
    
    public FootballGame (int roundNumber, int homeGoals, int homeBehinds, int awayGoals, int awayBehinds) {
        this.roundNumber = roundNumber;
        this.homeGoals = homeGoals;
        this.homeBehinds = homeBehinds;
        this.awayGoals = awayGoals;
        this.awayBehinds = awayBehinds;
    }
    
    public String summarise() {
        int totalHomeScore = (homeGoals * 6) + homeBehinds;
        int totalAwayScore = (awayGoals * 6) + awayBehinds;
        String results = "";
        if (totalHomeScore > totalAwayScore) {
            results = ("Round " + roundNumber + ": Home team won " + totalHomeScore + " points to " + totalAwayScore + ".");
        }
        if (totalHomeScore == totalAwayScore) {
            results = ("Round " + roundNumber + ": match was tied " + totalHomeScore + " points each.");
        }
        else {
            results = ("Round " + roundNumber + ": Home team lost " + totalHomeScore + " points to " + totalAwayScore + ".");
        }
        return results;
    }
    
    @Override
    public String toString() {
        return roundNumber + "," + homeGoals + "," + homeBehinds + "," + awayGoals + "," + awayBehinds;
    }
}
