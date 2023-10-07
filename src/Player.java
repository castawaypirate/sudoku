import java.io.Serializable;
import java.util.ArrayList;

/**
 * Αυτή η κλάση κρατά τα στατιστικά για ένα παίχτη
 * @author Απόλλων Συκιώτης
 * @author Αχιλλεύς Ριζάκος
 */

public class Player implements Serializable {
    private  String nickname;
    private ArrayList<Integer> ClassicChoices = new ArrayList<>();
    private ArrayList<Integer> KillerChoices = new ArrayList<>();
    private int wins;
    private int loses;

    /**
     * Είναι ο κατασκευαστής της κλάσης
     * @param name
     */
    public Player(String name){
        nickname=name;
        for(int i=1;i<11;i++){
            ClassicChoices.add(i);
            KillerChoices.add(i);
        }
    }

    /**
     * Επιστρέφει το Όνομα Χρήστη του παίχτη
     * @return nickname
     */
    public String getNickname(){
        return nickname;
    }

    /**
     * Επιστρέφει το πίνακα με τα Κλασσικά Sudoku που δεν έχει παίξει ο παίκτης
     * @return ClassicChoices
     */
    public ArrayList<Integer> getClassicChoices(){
        return ClassicChoices;
    }

    /**
     * Επιστρέφει το πίνακα με τα Killer Sudoku που δεν έχει παίξει ο παίχτης
     * @return KillerChoices
     */
    public ArrayList<Integer> getKillerChoices(){
        return KillerChoices;
    }

    /**
     * Επιστρέφει τις νίκες του παίχτη στην Αναπαράσταση Duidoku
     * @return wins
     */
    public int getWins(){
        return wins;
    }

    /**
     * Επιστρέφει τις ήττες του παίχτη στην Αναπαράσταση Duidoku
     * @return loses
     */
    public int getLoses(){
        return loses;
    }

    /**
     * Προσθέτει μια νίκη στις συνολικές νίκες κάθε φόρα που κερδίζει ο παίχτης
     */
    public void victory(){
        wins++;
    }

    /**
     * Προσθέτει μια ήττα στις συνολικές ήττες κάθε φόρα που χάνει ο παίχτης
     */
    public void defeat(){
        loses++;
    }

    /**
     * Αφαιρεί τα Κλασσικά Sudoku που έχει παίξει ο παίχτης από τον πίνακα
     * με τα διαθέσιμα Κλασσικά Sudoku
     * @param arr
     */
    public void setClassicSudoku(ArrayList<Integer> arr){
        ClassicChoices.removeAll(ClassicChoices);
        for(int i : arr){
            ClassicChoices.add(i);
        }
    }

    /**
     * Αφαιρεί τα Killer Sudoku που έχει παίξει ο παίχτης από τον πίνακα
     * με τα διαθέσιμα Killer Sudoku
     * @param arr
     */
    public void setKilerSudoku(ArrayList<Integer> arr){
        KillerChoices.removeAll(KillerChoices);
        for(int i : arr){
            KillerChoices.add(i);
        }
    }

    /**
     * Εμφανίζει όλα τα στοιχεία του παίχτη
     */
    public void show(){
        System.out.println("Name : "+nickname);
        System.out.println("Classic");
        for(int i : ClassicChoices){
            System.out.print(i);
        }
        System.out.println();
        System.out.println("Killer");
        for(int i : KillerChoices){
            System.out.print(i);
        }
        System.out.println();
        System.out.println("wins : "+wins);
        System.out.println("loses : "+loses);
    }
}
