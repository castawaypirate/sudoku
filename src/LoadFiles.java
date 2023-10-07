import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Φορτώνει τα Sudoku από τα txt αρχεία
 * @author Απόλλων Συκιώτης
 * @author Αχιλλεύς Ριζάκος
 */

public class LoadFiles {
    private ArrayList<String[]> sums;
    private ArrayList<String[]> sols;
    private int choice;

    public LoadFiles(){
    }

    /**
     * Δέχεται μια λογική μεταβλητή isKiller η οποία καθορίζει ποια παραλλαγή
     * του παιχνιδιού θα φορτώσει από τα αρχεία
     * @param isKiller
     */
    public void Load(boolean isKiller) {
        File file;
        File file2 = null;
        if(isKiller){
            file = new File("files/Killer Sudoku Sums");
            file2 = new File("files/Killer Sudoku Solutions");
        }
        else{
            file = new File("files/Sudoku");
        }
        Scanner in;
        try {
            if(isKiller){
                in = new Scanner(file);
                sums = new ArrayList<>();
                while (in.hasNextLine()) {
                    sums.add(in.nextLine().split(","));
                }
                in = new Scanner(file2);
                sols = new ArrayList<>();
                while (in.hasNextLine()) {
                    sols.add(in.nextLine().split(" "));
                }
            }
            else{
                in = new Scanner(file);
                sols = new ArrayList<>();
                while (in.hasNextLine()) {
                    sols.add(in.nextLine().split(" "));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Μετατρέπει τις μη επεξεργασμένες συμβολοσειρές που έχει διαβάσει απο το
     * αρχείο σε ένα πίνακα πινάκων που περιέχει τη λύση από το Sudoku που έχει
     * επιλεχθεί βάση της μεταβλητής choice και τον επιστρέφει
     * @param choice
     * @param isKiller
     * @return clas
     */
    public ArrayList<String[]> getSolution(int choice,boolean isKiller) {
        this.choice=choice;
        Load(isKiller);
        ArrayList<String[]> clas = new ArrayList<>();
        for (int i=0;i<sols.size();i++) {
            if(sols.get(i).length==1){
                int ch = Integer.parseInt(sols.get(i)[0]);
                if(choice==ch){
                    for (int j = i + 1; j < i+10; j++) {
                        clas.add(sols.get(j));
                    }
                }
            }
        }
        return clas;
    }

    /**
     * Για την παραλλαγή Killer δημιουργεί ένα Hashmap που έχει σαν κλειδί μία ομάδα
     * και σαν τιμή το άθροισμα των κελιών της 
     * @return groups
     */
    public HashMap<Integer, Integer> getSumGroups() {
        HashMap<Integer, Integer> groups = new HashMap<>();
        for (int i=0;i<sums.size();i++) {
            if(sums.get(i).length==1){
                int ch = Integer.parseInt(sums.get(i)[0]);
                if(choice==ch){
                    int j = i + 1;
                    while(sums.get(j).length>1) {
                        Integer sum = Integer.parseInt(sums.get(j)[sums.get(j).length-2]);
                        groups.put(j, sum);
                        j++;
                    }
                }
            }
        }
        return groups;
    }

    /**
     * Για την παραλλαγή Killer δημιουργεί ένα Hashmap που έχει σαν κλειδί ένα
     * κελί και σαν τιμή την ομάδα που ανήκει
     * @return groups
     */
    public HashMap<Integer, Integer> getUniqueGroups() {
        HashMap<Integer, Integer> groups = new HashMap<>();
        for (int i=0;i<sums.size();i++) {
            if(sums.get(i).length==1){
                int ch = Integer.parseInt(sums.get(i)[0]);
                if(choice==ch){
                    int j = i + 1;
                    while(sums.get(j).length>1){
                        for(int k=0;k<sums.get(j).length-2;k++){
                            groups.put(Integer.parseInt(sums.get(j)[k]),j);
                        }
                        j++;
                    }
                }
            }
        }
        return groups;
    }

    /**
     * Για την παραλλαγή Killer δημιουργεί ένα Hashmap που έχει σαν κλειδί τα
     * κελιά που ανήκουν σε μία ομάδα και σαν τιμή το χρώμα της
     * @return
     */
    public HashMap<Integer,Integer> getColorGroups() {
        HashMap<Integer, Integer> groups = new HashMap<>();
        for (int i=0;i<sums.size();i++) {
            if(sums.get(i).length==1){
                int ch = Integer.parseInt(sums.get(i)[0]);
                if(choice==ch){
                    int j = i + 1;
                    while(sums.get(j).length>1){
                        Integer color = Integer.parseInt(sums.get(j)[sums.get(j).length-1]);
                        groups.put(j, color);
                        j++;
                    }
                }
            }
        }
        return groups;
    }

    public void Show(ArrayList<String[]> list){
        for (int i = 0; i < list.size(); i++) {
            for (String j : list.get(i)) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

}

