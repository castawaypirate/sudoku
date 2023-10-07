import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

/**
 * Κληρονομεί από την κλάση Cell για να προσομοιάση
 * ένα κελί Duidoku Sudoku
 * @author Απόλλων Συκιώτης
 * @author Αχιλλεύς Ριζάκος
 */
public class DuiCell extends Cell {
    private boolean isWordoku;
    private DuiLogic logic;
    private JPanel buttonPanel = new JPanel();
    private DuiCell x = this;
    private JButton[] buttons = new JButton[4];

    /**
     *  Αυτός είναι ο κατασκευαστής της κλάσης
     *  Δέχεται τα παρακάτω ορίσματα για να αρχικοποιήσει το κελί
     *  στο ταμπλό του Sudoku
     * @param fontsize
     * @param row
     * @param column
     * @param item
     * @param gui
     * @param isWordoku
     * @param duiLogic
     */

    public DuiCell(int fontsize, int row, int column, JMenuItem item, Gui gui, boolean isWordoku, DuiLogic duiLogic) {
        super(fontsize, row, column, item, gui);
        logic=duiLogic;
        this.isWordoku=isWordoku;
        remove(cellValue);
        setFocusable(true);
        for(int i = 0;i<4;i++){
            JButton button = new JButton();
            if(isWordoku){
                button.setText(String.valueOf((char)(i+1+64)));
            }
            else{
                button.setText(String.valueOf(i+1));
            }
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    button.requestFocusInWindow();
                    x.setOpaque(true);
                    x.setText(button.getText());
                    Font font = new Font(cellValue.getFont().getName(),Font.BOLD,fontsize);
                    x.setFont(font);
                    x.setBackground(Color.GREEN);
                    x.setHorizontalAlignment(SwingConstants.CENTER);
                    x.removeAll();
                    if(isWordoku){
                        logic.getBoard()[row][column]=(int)(button.getText().charAt(0)-64);
                    }
                    else{
                        logic.getBoard()[row][column]=Integer.parseInt(button.getText());
                    }
                    logic.fillEndBoard(row,column);
                    logic.setPlayersturn(false);
                    if(!gui.scanBoardForDisabledCells()){
                        HashMap<String,Integer> botTurn = logic.botTurn();
                        logic.getBoard()[botTurn.get("row")][botTurn.get("column")]=botTurn.get("value");
                        gui.fillBotCell(botTurn.get("row"),botTurn.get("column"),botTurn.get("value"));
                        logic.fillEndBoard(botTurn.get("row"),botTurn.get("column"));
                        gui.scanBoardForDisabledCells();
                    }
                }
            } );
            buttons[i]=button;
            buttonPanel.add(button);
        }
        buttonPanel.setVisible(false);
        x.add(buttonPanel);
        gui.reInitialize(gui.getDuiPanel());
        addMouseListener();
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                gui.clearDuiCells(row,column);
            }
        });
    }

    /**
     * Θέτει την τιμή που εγχωρεί το Bot στην παραλλαγή Duidoku.
     * @param value
     */
    public void setBotValue(int value){
        setOpaque(true);
        if(isWordoku){
            setText(String.valueOf((char)(value+64)));
        }
        else{
            setText(String.valueOf(value));
        }
        Font font = new Font(cellValue.getFont().getName(),Font.BOLD,fontsize);
        setFont(font);
        setBackground(Color.PINK);
        setHorizontalAlignment(SwingConstants.CENTER);
        removeAll();
    }

    /**
     * Προσθέτει ένα MouseListener στο DuiCell
     */
    public void addMouseListener(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x.requestFocusInWindow();
                x.removeMouseListener(this);
                for(int i=0;i<4;i++){
                    for(int j : logic.getAvailableValues(row,column)){
                        if(isWordoku){
                            if((buttons[i].getText().charAt(0))==(char)(j+64)){
                                buttons[i].setVisible(true);
                            }
                        }
                        else{
                            if(Integer.parseInt(buttons[i].getText())==j){
                                buttons[i].setVisible(true);
                            }
                        }
                    }
                }
                buttonPanel.setVisible(true);
            }
        });
    }

    /**
     * Μαυρίζει τα κελιά που δεν μπορούν να δεχθούν καμία
     * τιμή καθώς έχουν μπλοκαριστεί από τις τιμές των υπόλοιπων κελιών.
     */
    public void disableCell(){
        if(getText().equals("")){
            setOpaque(true);
            setBackground(Color.BLACK);
            removeAll();
            logic.fillEndBoard(row,column);
        }

    }

    /**
     * Θέτει αόρατα τα κουμπιά του προηγούμενου κελιού όταν πατηθεί ένα επόμενο
     */
    public void hideButtons(){
        for(int i=0;i<4;i++){
            buttons[i].setVisible(false);
        }
        buttonPanel.setVisible(false);
        addMouseListener();
    }

}
