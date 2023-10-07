import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Κληρονομεί από την Cell για να προσομοιάσει ένα
 * κελί Κλασσικού Sudoku
 * @author Απόλλων Συκιώτης
 * @author Αχιλλεύς Ριζάκος
 */
public class ClassicCell extends Cell {
    private ClassicCell x = this;
    private ClassicLogic classicLogic;

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
     * @param fix
     * @param fixvalue
     * @param classicLogic
     * @param isWordoku
     */

    public ClassicCell(int fontsize, int row, int column, JMenuItem item, Gui gui, boolean fix, String fixvalue, ClassicLogic classicLogic, boolean isWordoku) {
        super(fontsize, row, column, item,gui);
        this.classicLogic=classicLogic;
        Color col = this.getBackground();
        cellValue.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                cellValue.setCaretColor(col);
                cellValue.setBackground(col);
            }
        });
        cellValue.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(!fix){
                    if(helpIsOn){
                        if(classicLogic.getAvailableValues(row,column).isEmpty()){
                            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(x),resourceBundle.getString("No")+" "+resourceBundle.getString("Valid")+" "+resourceBundle.getString("Number"),resourceBundle.getString("Help"),JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            String hel = "";
                            for(int i : classicLogic.getAvailableValues(row,column)){
                                if(isWordoku){
                                    hel = hel + String.valueOf((char)(i+64))+ " ";
                                }
                                else{
                                    hel = hel + String.valueOf(i)+ " ";
                                }
                            }
                            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(x),hel,resourceBundle.getString("Help"),JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    cellValue.setCaretColor(col.darker());
                    cellValue.setBackground(col.darker());
                }
            }
        });
        cellValue.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (( availableChars(c,isWordoku) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    classicLogic.setValueToBoard(row,column,0);
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
                if (( availableChars(c,isWordoku))) {
                    e.consume();
                }
                else{
                    if(isWordoku){
                        classicLogic.setValueToBoard(row,column,Character.toUpperCase(c)-64);
                        endClassic();
                    }
                    else{
                        classicLogic.setValueToBoard(row,column,c-'0');
                        endClassic();

                    }
                    int pos = cellValue.getCaretPosition();
                    cellValue.setText(cellValue.getText().toUpperCase());
                    cellValue.setCaretPosition(pos);
                }
            }
        });
        if(fix){
            if(isWordoku){
                char cha = (char)(Integer.parseInt(fixvalue)+64);
                cellValue.setText(String.valueOf(cha));
            }
            else{
                cellValue.setText(fixvalue);
            }
            cellValue.setDisabledTextColor(Color.BLACK);
            cellValue.setEditable(false);
            cellValue.setEnabled(false);
        }
    }

    /**
     * Ελέγχει αν το παιχνίδι έχει τερματιστεί και εμφανίζει το
     * κατάλληλο μήνυμα στον χρήστη.
     * Αλλιώς εμφανίζει μήνυμα "η λύση δεν είναι σωστή" και
     * επιτρέπει στον χρήστη να διορθώσει το puzzle του.
     */
    public void endClassic(){
        if(classicLogic.isGameOver()){
            if(classicLogic.isGameCorrect()){
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(x),resourceBundle.getString("Completed"));
                gui.getClassicChoices().remove(Integer.valueOf(gui.getChoice()));
                if(!(gui.getPlayer()==null)){
                    gui.getPlayer().setClassicSudoku(gui.getClassicChoices());
                    gui.getData().saveToBinaryFile(gui.getPlayer());
                }
                gui.getMenuBar().remove(gui.getHelpMenu());
                gui.endGame();
            }
            else{
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(x),resourceBundle.getString("Solution")+" "+resourceBundle.getString("isn't")+" "+resourceBundle.getString("right"),resourceBundle.getString("Game"),JOptionPane.INFORMATION_MESSAGE);
            }
            classicLogic.showBoard();
        }
    }


    /**
     * Δέχεται το χαρακτήρα που εισάχθηκε από το πληκτρολόγιο και επιστέφει
     * μία λογική τιμή ανάλογα αν είνα εντός ή εκτός ορίων. Λειτουργεί για
     * αριθμούς και χαρακτήρες
     * @param c
     * @param isWordoku
     * @return true/false
     */
    public boolean availableChars(char c, boolean isWordoku){
        if(isWordoku){
            if(!((c >= 'A') && (c <= 'I')) && !((c >= 'a') && (c <= 'i'))){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(!((c >= '1') && (c <= '9'))){
                return true;
            }
            else {
                return false;
            }
        }
    }
}
