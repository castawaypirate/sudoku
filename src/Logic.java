import java.util.ArrayList;

public class Logic {
    protected LoadFiles load;
    protected int solution[][];
    protected int board[][];
    protected ArrayList<Integer[]> incorrectCells;
    protected int zeros;
    protected int var;

    public Logic(){
        load = new LoadFiles();
        incorrectCells = new ArrayList<>();
        zeros = 0;
    }


    /**
     * Επιστρέφει έναν πίνακα με τη λύση
     * @return solution
     */
    public int[][] getSolution() {
        return solution;
    }

    /**
     * Επιστρέφει το παρόν ταμπλό
     * @return board
     */
    public int[][] getBoard(){
        return board;
    }

    /**
     * Δέχεται την γραμμή και την στήλη του board
     * και θέτει την μεταβλητή value σε εκείνη την
     * θέση
     * @param x
     * @param y
     * @param value
     */
    public void setValueToBoard(int x,int y,int value){
        if (value == 0) {
            zeros++;
        } else if (board[x][y] == 0) {
            zeros--;
        }
        board[x][y]=value;
    }

    /**
     * Εκτυπώνει τη λύση
     */
    public void showSolution(){
        for(int i=0;i<solution.length;i++){
            for(int j=0;j<solution[i].length;j++){
                System.out.print(solution[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Εκτυπώνει το ταμπλό
     */
    public void showBoard(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public boolean isCellCorrect(int row, int column) {
        return board[row][column] == 0 || board[row][column] == solution[row][column];
    }

    public boolean isGameOver() {
        return zeros==0;
    }

    /**
     * Ελέγχει αν το παιχνίδι λειτουργεί κανονικά ή όχι.
     * @return
     */
    public boolean isGameCorrect() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!(board[i][j] == solution[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Ελέγχει ποιές τιμές μπορούν να μπούν σε ένα κελί
     * @param row
     * @param column
     * @return availableValues
     */
    public ArrayList<Integer> getAvailableValues(int row, int column) {
        ArrayList<Integer> availableValues = new ArrayList<>();
        int boxStartX = ((int) row/var)*var;
        int boxStartY = ((int) column/var)*var;
        int boxLength = (int) Math.sqrt(board.length);

        for (int i = 0; i <= board.length; i++) {
            availableValues.add(i);
        }

        for (int i = 0; i < board.length; i++) {
            int value = board[row][i];
            availableValues.set(value, 0);
        }

        for (int i = 0; i < board.length; i++) {
            int value = board[i][column];
            availableValues.set(value, 0);
        }

        for (int i = boxStartX; i < boxLength + boxStartX; i++) {
            for (int j = boxStartY; j < boxLength + boxStartY; j++) {
                int value = board[i][j];
                availableValues.set(value, 0);
            }
        }

        for (int i = 0; i < availableValues.size(); i++) {
            if (availableValues.get(i) == 0) {
                availableValues.remove(i);
                i--;
            }
        }
        return availableValues;
    }
}
