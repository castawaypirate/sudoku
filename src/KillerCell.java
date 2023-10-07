import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Κληρονομεί από την κλάση Cell για να προσομοιάση
 * ένα κελί Killer Sudoku
 * @author Απόλλων Συκιώτης
 * @author Αχιλλεύς Ριζάκος
 */

public class KillerCell extends Cell {
    private JLabel sumLabel;
    private KillerLogic killerLogic;
    private KillerCell x = this;

    private Locale locale = Locale.getDefault();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("MessageBundle",locale);

    /**
     * Αυτός είναι ο κατασκευαστής της κλάσης
     * Δέχεται τα παρακάτω ορίσματα για να αρχικοποιήσει το κελί
     * στο ταμπλό του Sudoku
     * @param fontsize
     * @param row
     * @param column
     * @param item
     * @param gui
     * @param sum
     * @param color
     * @param killerLogic
     */

    public KillerCell(int fontsize, int row, int column, JMenuItem item, Gui gui, int sum, int color,KillerLogic killerLogic) {
        super(fontsize, row, column, item, gui);
        this.killerLogic=killerLogic;
        if(sum==0){
            sumLabel =  new JLabel(" ");
        }
        else{
            sumLabel =  new JLabel(String.valueOf(sum));
        }
        sumLabel.setFont(new Font(sumLabel.getFont().getName(),Font.PLAIN,15));
        sumLabel.setOpaque(true);
        Color col = getColor(color);
        cellValue.setCaretColor(col);
        cellValue.setBackground(col);
        sumLabel.setBackground(col);
        cellValue.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                cellValue.setCaretColor(col);
                cellValue.setBackground(col);
                sumLabel.setBackground(col);
            }
        });
        cellValue.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(helpIsOn){
                    if(killerLogic.getAvailableValues(row + 1, column + 1).isEmpty()){
                        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(x),resourceBundle.getString("No")+" "+resourceBundle.getString("Valid")+" "+resourceBundle.getString("Number"),resourceBundle.getString("Help"),JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        String hel = "";
                        for(int i : killerLogic.getAvailableValues(row + 1, column + 1)){
                            hel = hel + String.valueOf(i)+ " ";
                        }
                        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(x),hel,resourceBundle.getString("Help"),JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                cellValue.setCaretColor(col.darker());
                cellValue.setBackground(col.darker());
                sumLabel.setBackground(col.darker());
            }
        });
        cellValue.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '1') && (c <= '9')) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
                    killerLogic.setValueToBoard(row,column,0);
                    e.consume();
                }
                else{
                    if(cellValue.getText().length()==1){
                        cellValue.setText("");
                    }
                }
            }
            public void keyReleased(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '1') && (c <= '9'))) {
                    e.consume();
                }
                else{
                    killerLogic.setValueToBoard(row,column,c-'0');
                    endKiller();
                }
            }
        });
        add(sumLabel, BorderLayout.NORTH);
    }

    /**
     * Ελέγχει αν το παιχνίδι έχει τερματιστεί και εμφανίζει το κατάλληλο μήνυμα
     * στον χρήστη.
     * Αλλιώς εμφανίζει μήνυνα "Η λύση δεν είναι σωστή" και επιτρέπει στον χρήστη
     * να διορθώσει το puzzle του.
     */

    public void endKiller(){
        if(killerLogic.isGameOver()){
            if(killerLogic.isGameCorrect()){
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(x),resourceBundle.getString("Completed"));
                gui.getKillerChoices().remove(Integer.valueOf(gui.getChoice()));
                if(!(gui.getPlayer()==null)){
                    gui.getPlayer().setKilerSudoku(gui.getKillerChoices());
                    gui.getData().saveToBinaryFile(gui.getPlayer());
                }
                gui.getMenuBar().remove(gui.getHelpMenu());
                gui.reInitialize(gui.getKillerPanel());
                gui.endGame();
            }
            else{
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(x),resourceBundle.getString("Solution")+" "+resourceBundle.getString("isn't")+" "+resourceBundle.getString("right"),resourceBundle.getString("Game"),JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Δέχεται ένα ακέραιο και επιστέφει το χρώμα
     * που του αντιστοιχεί
     * @param c
     * @return
     */
    public Color getColor(int c){
        if(c==1){
            return Color.YELLOW;
        }
        else if(c==2){
            return Color.GREEN;
        }
        else if(c==3){
            return Color.CYAN;
        }
        else if(c==4){
            return Color.PINK;
        }
        else{
            return Color.GRAY;
        }
    }
}
