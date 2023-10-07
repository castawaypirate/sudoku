import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Αποθηκεύει του κάθε παίχτη σε αρχεία
 * @author Απόλλων Συκιώτης
 * @author Αχιλλεύς Ριζάκος
 */

public class PlayersData {
    private ArrayList<Player> players;

    /**
     * Ο κατασκευαστής της κλάσης
     */
    public PlayersData(){
        players=new ArrayList<>();
    }

    /**
     * Ελέγχει αν το αρχείο με τα δεδομένα των παιχτών υπάρχει
     * @return boolean
     */
    public boolean exists(){
        File f = new File("PlayersData.dat");
        return (f.exists() && !f.isDirectory());
    }

    /**
     * Επιλέγει τον παίχτη με το όνομα name από το ArrayList που φορτώνει
     * από το αρχείο
     * @param name
     * @return Player
     */
    public Player getPlayerFromFile(String name){
        if(exists()){
            loadFromBinaryFile();
            for(Player p : players){
                if(p.getNickname().equals(name)){
                    return p;
                }
            }
        }
        return new Player(name);
    }

    /**
     * Αποθηκεύει τα στοιχεία ενός παίχτη στο
     * αντίστοιχο αρχείο
     * @param player
     */
    public void saveToBinaryFile(Player player) {
        for(Player p : players){
            if(p.getNickname().equals(player.getNickname())){
                players.remove(p);
                break;
            }
        }
        players.add(player);
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("PlayersData.dat"))) {
            out.writeObject(players);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Φορτώνει τα αρχεία με τα στατιστικά των παιχτών
     */
    public void loadFromBinaryFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("PlayersData.dat"))) {
            ArrayList<Player> object;
            object = (ArrayList<Player>) in.readObject();
            players=object;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
