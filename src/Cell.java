import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Προσομοιώνει ένα κελί Sudoku
 * @author Απόλλων Συκιώτης
 * @author Αχιλλεύς Ριζάκος
 */
public class Cell extends JLabel {
    protected JFormattedTextField cellValue;
    protected int fontsize;
    protected int row;
    protected int column;
    protected boolean helpIsOn;
    protected Gui gui;
    protected JMenuItem item;

    /**
     * Αυτός είναι ο κατασκευαστής της κλάσης
     * Δέχεται τα παρακάτω ορίσματα για να αρχικοποιήσει το κελί
     * στο ταμπλό του Sudoku
     * @param fontsize
     * @param row
     * @param column
     * @param item
     * @param gui
     */

    public Cell(int fontsize, int row, int column, JMenuItem item, Gui gui){
        this.fontsize=fontsize;
        this.row=row;
        this.column=column;
        this.item=item;
        this.gui=gui;
        item.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    helpIsOn = true;
                }
                if(e.getStateChange() == ItemEvent.DESELECTED){
                    helpIsOn = false;
                }
            }
        });
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black,1));
        cellValue = new JFormattedTextField();
        Font font = new Font(cellValue.getFont().getName(),Font.BOLD,fontsize);
        cellValue.setFont(font);
        cellValue.setHorizontalAlignment(JFormattedTextField.CENTER);
        cellValue.setBackground(this.getBackground());
        cellValue.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(cellValue,BorderLayout.CENTER);
    }

}
