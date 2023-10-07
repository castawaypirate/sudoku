
/**
 * Αυτή είναι η Main του project
 * @author Αχιλλεύς Ριζάκος
 * @author Απόλλων Συκιώτης
 */

public class Main {

    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gui gui=new Gui();
            }
        });
    }
}

