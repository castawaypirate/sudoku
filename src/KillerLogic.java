import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * * Κληρονομεί από τη κλάση Logic και λειτουργεί ως
 * η λογική του Κλασσικού Sudoku
 * @author Απόλλων Συκιώτης
 * @author Αχιλλεύς Ριζάκος
 */

public class KillerLogic extends Logic {
    private HashMap<Integer, Integer> sumGroups;
    private HashMap<Integer, Integer> uniqueTeamGroups;
    private HashMap<Integer, Integer> colorGroups;
    private HashMap<Integer, ArrayList<Integer>> teamCells;
    private HashSet<Integer> cellsOfSum;

    /**
     * Αυτός είναι ο κατασκευαστής της κλάσης
     */

    public KillerLogic() {
        super();
        solution = new int[9][9];
        board = new int[9][9];
        sumGroups = new HashMap<>();
        uniqueTeamGroups = new HashMap<>();
        colorGroups = new HashMap<>();
        teamCells = new HashMap<>();
        cellsOfSum = new HashSet<>();
        var=3;
    }

    /**
     * Δέχεται μια τυχαία τιμή από 1 εώς το 10 και αρχικοποιεί
     * τα sumGroups, uniqueTeamGroups και colorGroups με τις
     * τιμές του αντίστοιχου Sudoku
     * @param choice
     */
    public void choosePuzzle(int choice) {
        ArrayList<String[]> clas = load.getSolution(choice, true);
        for (int i = 0; i < clas.size(); i++) {
            for (int j = 0; j < clas.get(i).length; j++) {
                solution[i][j] = Character.getNumericValue(clas.get(i)[j].charAt(0));
                zeros++;
            }
        }
        colorGroups = load.getColorGroups();
        sumGroups = load.getSumGroups();
        uniqueTeamGroups = load.getUniqueGroups();
        for (Integer cell : uniqueTeamGroups.keySet()) {
            Integer team = uniqueTeamGroups.get(cell);
            if (teamCells.containsKey(team)) {
                teamCells.get(team).add(cell);
            } else {
                ArrayList<Integer> cells = new ArrayList<>();
                cells.add(cell);
                teamCells.put(team, cells);
            }
        }
    }

    /**
     * Δέχεται σαν παραμέτους την γραμμή x και στήλη y του Sudoku
     * και επιστρέφει τον αριθμό της μοναδικής ομάδας
     * @param x
     * @param y
     * @return
     */
    public int getUniqueTeam(int x,int y){
        Integer cell = x*10 + y;
        if (uniqueTeamGroups.containsKey(cell)) {
            return uniqueTeamGroups.get(cell);
        }
        return 0;
    }

    /**
     * Δέχεται σαν παραμέτους την γραμμή x και στήλη y του Sudoku
     * και επιστρέφει μία λογική τιμή που καθορίζει αν το κελί
     * περιέχει δείκτη αθροίσματος ή οχι
     * @param x
     * @param y
     * @return true/false
     */
    public boolean cellOfSum(int x,int y){
        Integer cell = x*10 + y;
        Integer team = uniqueTeamGroups.get(cell);
        if (!cellsOfSum.contains(team)) {
            cellsOfSum.add(team);
            return true;
        }
        return false;
    }

    /**
     * Δέχεται σαν παραμέτους την γραμμή x και στήλη y του Sudoku
     * και επιστρέφει το άθροισμα της ομάδας που ανήκει το κελί
     * @param x
     * @param y
     * @return sum
     */
    public int getSum(int x,int y){
        Integer cell = x*10 + y;
        Integer team = uniqueTeamGroups.get(cell);
        return sumGroups.get(team);
    }

    /**
     * Δέχεται σαν παραμέτους την γραμμή x και στήλη y του Sudoku
     * και επιστρέφει το χρώμα της ομάδας που ανήκει το κελί
     * @param x
     * @param y
     * @return color
     */
    public int getColor(int x,int y){
        Integer cell = x*10 + y;
        Integer team = uniqueTeamGroups.get(cell);
        if (colorGroups.containsKey(team)) {
            return colorGroups.get(team);
        }
        return 0;
    }

    @Override
    public ArrayList<Integer> getAvailableValues(int row, int column){
        ArrayList<Integer> availableValues = super.getAvailableValues(row-1, column-1);
        Integer cell = row*10 + column;
        Integer team = uniqueTeamGroups.get(cell);
        ArrayList<Integer> cells = teamCells.get(team);
        int totalSum = 0;
        int sum = 0;
        int cellsToBeFilled = 0;
        for (int i = 0; i < cells.size(); i++) {
            int x = (int) cells.get(i)/10;
            int y = cells.get(i)%10;
            int cellNumber = board[x - 1][y - 1];
            if (cellNumber == 0 && cell != x*10 + y) {
                cellsToBeFilled ++;
            }
            sum += cellNumber;
        }
        totalSum = sumGroups.get(team) - sum;
        if (cellsToBeFilled < 1) {
            for (int i = 0; i < availableValues.size(); i++) {
                if (availableValues.get(i) != totalSum) {
                    availableValues.remove(i);
                    i--;
                }
            }
        } else {
            for (int i = 0; i < availableValues.size(); i++) {
                if (availableValues.get(i) > totalSum) {
                    availableValues.remove(i);
                    i--;
                }
            }
        }
        return availableValues;
    }
}
