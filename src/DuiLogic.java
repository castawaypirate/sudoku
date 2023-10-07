import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Κληρονομεί από την κλάση Logic και λειτουργεί
 * ως η λογική του Duidoku Sudoku
 * @author Απόλλων Συκιώτης
 * @author Αχιλλεύς Ριζάκος
 */

public class DuiLogic extends Logic {
    private boolean playersturn=true;
    private int endBoard[][];

    /**
     * Είναι ο κατασκευαστής της κλάσης
     */

    public DuiLogic(){
        super();
        board = new int[4][4];
        endBoard = new int[4][4];
        var = 2;
    }

    /**
     * Γεμίζει γραμμές και στήλες
     * @param row
     * @param column
     */
    public void fillEndBoard(int row,int column){
        endBoard[row][column]=1;
    }

    /**
     * Ελέγχει αν το παιχνίδι έχει τελειώσει
     * @return
     */
    public boolean endGame(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(endBoard[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Επιστρέφει μια λογική μεταβλητή που λέει αν είναι η σειρά του παίχτη ή όχι
     * @return playersturn
     */
    public boolean getPlayersturn() {
        return playersturn;
    }

    /**
     * Ορίζει μια λογική μεταβλητή που λέει αν είναι η σειρά του παίχτη ή όχι
     * @param playersturn
     */
    public void setPlayersturn(boolean playersturn){
        this.playersturn=playersturn;
    }

    /**
     * Ελέγχει το board για τα κενά κελιά και τις διαθέσιμες τιμές τους
     * και επιστρέφει ένα HashMap με κλειδία τιμή,γραμμή,στήλη και τιμές
     * τις αντίστοιχες τιμές τους προσωμοιάζοντας τον γύρο του bot. Η τιμή επιλέγεται
     * τυχαία.
     *
     * @return
     */
    public HashMap<String, Integer> botTurn() {
        Random rand = new Random();
        ArrayList<Integer[]> emptyCells = new ArrayList<>();
        ArrayList<Integer> availableValues = new ArrayList<>();
        HashMap<String, Integer> values = new HashMap<>();
        Integer[] cell;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int cellValue = board[i][j];
                if (cellValue == 0) {
                    emptyCells.add(new Integer[]{i, j});
                } 
            }
        }

        boolean cellFound = false;
        do {
            cell = emptyCells.get(rand.nextInt(emptyCells.size()));
            availableValues = getAvailableValues(cell[0], cell[1]);
            if (!availableValues.isEmpty()) {
                cellFound = true;
            }
        } while (!cellFound);
        setPlayersturn(true);
        int value = availableValues.get(rand.nextInt(availableValues.size()));
        values.put("value", value);
        values.put("row", cell[0]);
        values.put("column", cell[1]);
        return values;
    }
}
