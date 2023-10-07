import java.util.ArrayList;

/**
 * Κληρονομεί από τη κλάση Logic και λειτουργεί ως
 * η λογική του Κλασσικού Sudoku
 * @author Απόλλων Συκιώτης
 * @author Αχιλλεύς Ριζάκος
 */
public class ClassicLogic extends Logic {

    /**
     * Αυτός είναι ο κατασκευαστής της κλάσης
     */
    public ClassicLogic(){
        super();
        solution = new int[9][9];
        board = new int[9][9];
        var=3;
    }

    /**
     * Αναζητά από το αρχείο που κρατά ποιά puzzle έχει λύσει ο κάθε παίχτης
     * και μετά κάνει μια τυχαία επιλογή από αυτά που υπολείπονται
     * @param choice
     */

    public void choosePuzzle(int choice){
        ArrayList<String[]> clas = load.getSolution(choice,false);
        for(int i=0;i<clas.size();i++){
            for(int j=0;j<clas.get(i).length;j++){
                solution[i][j]=Character.getNumericValue(clas.get(i)[j].charAt(0));
                if(clas.get(i)[j].contains(".")){
                    board[i][j]=Character.getNumericValue(clas.get(i)[j].charAt(0));
                }
                else{
                    board[i][j]=0;
                    zeros++;
                }
            }
        }
    }
}
