import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Αυτή είναι η γραφική διασύνδεση του παιχνιδιού
 * @author Απόλλων Συκιώτης
 * @author Αχιλλεύς Ριζάκος
 */
public class Gui {
    private JFrame frame;
    private JFrame nicknameFrame;
    private JPanel startPanel;
    private JPanel nicknamePanel;
    private JPanel classicPanel;
    private JPanel killerPanel;
    private JPanel duiPanel;
    private final static int clasnkillfontsize = 65;
    private final static int duifontsize = 100;
    private ClassicLogic classicLogic;
    private KillerLogic killerLogic;
    private DuiLogic duiLogic;
    private boolean isWordoku;
    private boolean isIncognito;
    int choice;
    private ArrayList<Integer> ClassicChoices = new ArrayList<>();
    private ArrayList<Integer> KillerChoices = new ArrayList<>();
    private PlayersData data = new PlayersData();
    private Player player;

    private Locale locale = Locale.getDefault();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("MessageBundle",locale);

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu(resourceBundle.getString("Game"));
    private JMenuItem item1 = new JMenuItem(resourceBundle.getString("New")+" "+resourceBundle.getString("Game"));
    private JMenuItem item2 = new JMenuItem(resourceBundle.getString("Close"));
    private JMenuItem item3 = new JMenuItem(resourceBundle.getString("Change")+" "+resourceBundle.getString("User"));
    private JMenu help = new JMenu(resourceBundle.getString("Help"));
    private JMenuItem item4 = new JCheckBoxMenuItem(resourceBundle.getString("Help"));
    private JMenu stats = new JMenu(resourceBundle.getString("Stats"));
    private JMenuItem duiStats = new JMenuItem("Duidoku");

    private DuiCell[][] board =  new DuiCell[4][4];

    /**
     * Αρχικοποιεί και εμφανίζει το πρώτο Frame
     */
    public Gui(){
        nicknameFrame = new JFrame(resourceBundle.getString("Start")+" "+resourceBundle.getString("Menu"));
        nicknameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nicknameFrame.setSize(800, 320);
        nicknameFrame.setResizable(false);
        nicknameFrame.setLocationRelativeTo(null);
        nicknameFrame.setVisible(true);
        initializeNicknamePanel();
    }

    /**
     * Προσθέτει τα στοιχεία του μενού στατιστικά στην μπάρα των μενού
     * @param boo
     */
    public void setStatsMenu(boolean boo){
        if(!boo){
            duiStats.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame,resourceBundle.getString("Wins")+"-->"+player.getWins()+" "+resourceBundle.getString("Loses")+"-->"+player.getLoses());
                }

            });
            stats.add(duiStats);
            menuBar.add(stats);
        }
    }

    /**
     * Επιστρέφει τον παίχτη
     * @return player
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Επιστρέφει το μενού βοήθεια
     * @return help
     */
    public JMenu getHelpMenu(){
        return help;
    }

    /**
     * Επιστρέφει το μενού
     * @return menuBar
     */
    public JMenuBar getMenuBar(){
        return menuBar;
    }

    /**
     * Επιστρέφει τα στοιχεία του παίχτη
     * @return data
     */
    public PlayersData getData(){
        return data;
    }

    /**
     * Αρχικοποιεί το Frame του αρχικού μενού
     */
    public void initializeFrame(){
        frame = new JFrame(resourceBundle.getString("Start")+" "+resourceBundle.getString("Menu"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 320);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Αρχικοποιεί το Panel του Nickname
     */
    public void initializeNicknamePanel(){
        GridLayout grid = new GridLayout(0,2);
        grid.setHgap(0);
        nicknamePanel = new JPanel();
        nicknamePanel.setLayout(grid);
        nicknamePanel.setBackground(Color.WHITE);
        JButton nick = new JButton("<html>"+resourceBundle.getString("Login")+"<center>"+resourceBundle.getString("Or")+"</center>"+resourceBundle.getString("Sign")+resourceBundle.getString("Up")+"</html>");
        JButton inco = new JButton(resourceBundle.getString("Guest"));
        Font font = new Font(nicknamePanel.getFont().getName(),Font.BOLD,50);
        nick.setFont(font);
        inco.setFont(font);
        nick.setBackground(Color.WHITE);
        inco.setBackground(Color.WHITE);
        nick.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(nicknameFrame, resourceBundle.getString("Give")+" "+resourceBundle.getString("UserName"));
                player = data.getPlayerFromFile(name);
                for(int in : player.getClassicChoices()){
                    ClassicChoices.add(in);
                }
                for(int in : player.getKillerChoices()){
                    KillerChoices.add(in);
                }
                isIncognito=false;
                nicknameFrame.dispose();
                initializeFrame();
                initializeStartPanel();
            }
        });
        inco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i=1;i<11;i++){
                    ClassicChoices.add(i);
                    KillerChoices.add(i);
                }
                isIncognito=true;
                nicknameFrame.dispose();
                initializeFrame();
                initializeStartPanel();
            }
        });
        nicknamePanel.add(nick);
        nicknamePanel.add(inco);
        nicknamePanel.setVisible(true);
        nicknameFrame.add(nicknamePanel);

    }

    /**
     * Επιστρέφει τα Κλασσικά Sudoku που έχει παίξει ο παίχτης
     * @return ClassicChoices
     */
    public ArrayList<Integer> getClassicChoices(){
        return ClassicChoices;
    }

    /**
     * Επιστρέφει τα Killer Sudoku που έχει παίξει ο παίχτης
     * @return KillerChoices
     */
    public ArrayList<Integer> getKillerChoices(){
        return KillerChoices;
    }

    /**
     * Επιστρέφει μια τυχαία επιλογή από τα puzzle τα οποία δεν έχουν ολοκληρωθεί
     * @return choice
     */
    public int getChoice() {
        return choice;
    }

    /**
     * Φορτώνει τις εικόνες του αρχικού μενού
     * @return img
     */
    public BufferedImage[] loadImages(){
        BufferedImage img[] = new BufferedImage[3];
        try {
            img[0] = ImageIO.read(new File("images/classic.png"));
            img[1] = ImageIO.read(new File("images/killer.png"));
            img[2] = ImageIO.read(new File("images/duido.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Αρχικοποιεί το Panel του αρχικού μενού
     */
    public void initializeStartPanel(){
        GridLayout grid = new GridLayout(0,3);
        grid.setHgap(30);
        startPanel = new JPanel();
        startPanel.setLayout(grid);
        startPanel.setBackground(Color.WHITE);
        JButton clas = new JButton();
        JButton kill = new JButton();
        JButton dui = new JButton();
        clas.setSize(240,260);
        kill.setSize(240,260);
        dui.setSize(240,260);
        BufferedImage[] img = loadImages();
        Image clasimg = img[0].getScaledInstance(clas.getWidth(), clas.getHeight(),
                Image.SCALE_SMOOTH);
        Image killimg = img[1].getScaledInstance(kill.getWidth(), kill.getHeight(),
                Image.SCALE_SMOOTH);
        Image duiimg = img[2].getScaledInstance(dui.getWidth(), dui.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon clasIcon = new ImageIcon(clasimg);
        ImageIcon killIcon = new ImageIcon(killimg);
        ImageIcon duiIcon = new ImageIcon(duiimg);
        clas.setIcon(clasIcon);
        kill.setIcon(killIcon);
        dui.setIcon(duiIcon);
        clas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        kill.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dui.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initializeClassicSudokuPanel();
            }
        });
        kill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeKillerSudokuPanel();
            }
        });
        dui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeDuidokuPanel();
            }
        });
        setMenu();
        startPanel.add(clas);
        startPanel.add(kill);
        startPanel.add(dui);
        frame.getContentPane().add(startPanel);
        frame.invalidate();
        frame.validate();
        frame.repaint();
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Ορίζει το μενού παιχνίδι και το προσθέτει στην μπάρα των μενού
     */
    public void setMenu(){
        frame.setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuBar.remove(help);
                frame.dispose();
                initializeFrame();
                initializeStartPanel();
            }

        });
        item2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }

        });
        item3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Gui();
            }
        });
        setStatsMenu(isIncognito);
    }

    /**
     * Ρωτάει τον χρήστη ποιά αναπαράσταση θέλει να επιλέξει
     */
    public void chooseRepresentation(){
        Object[] options = {resourceBundle.getString("Numbers"), resourceBundle.getString("Letters")};
        JOptionPane jop = new JOptionPane(
                resourceBundle.getString("Choose")+" "+resourceBundle.getString("Version"),JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[0]);
        JDialog dialog = jop.createDialog(null, resourceBundle.getString("Version"));
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);
        String a3 = (String) jop.getValue();
        if (a3.equals(resourceBundle.getString("Numbers"))) {
            isWordoku=false;
        }
        else{
            isWordoku=true;
        }
    }

    /**
     * Προσθέτει το μενού Βοήθεια στη μπάρα των μενού
     */
    public void addHelpToMenu(){
        item4.setSelected(false);
        help.add(item4);
        menuBar.add(help);
    }

    /**
     * Δέχεται δύο πίνακες με τα διαθέσιμα Sudoku της κάθε αναπαράστασης
     * και επιστρέφει έναν τυχαίο αριθμό αντίστοχου Sudoku
     * @param mode
     * @return ArrayList
     */
    public int getRandomChoice(String mode){
        Random rand = new Random();
        if(mode.equals("Classic")){
            return ClassicChoices.get(rand.nextInt(ClassicChoices.size()));
        }
        return KillerChoices.get(rand.nextInt(KillerChoices.size()));

    }

    /**
     * Αρχικοποιεί το Panel του Κλασσικού Sudoku
     */
    public void initializeClassicSudokuPanel(){
        if(ClassicChoices.isEmpty()){
            JOptionPane.showMessageDialog(frame,resourceBundle.getString("You")+resourceBundle.getString("Have")+" "+resourceBundle.getString("Solved")+" "+resourceBundle.getString("All")+" "+resourceBundle.getString("The")+" "+resourceBundle.getString("Claasic")+" "+"Sudoku");
            frame.dispose();
            initializeFrame();
            initializeStartPanel();
        }
        else{
            choice = getRandomChoice("Classic");
            classicPanel = new JPanel();
            frame.setTitle("Classic");
            frame.getContentPane().remove(startPanel);
            chooseRepresentation();
            classicPanel.setPreferredSize(new Dimension(800, 700));
            classicPanel.setLayout(new java.awt.GridLayout(3, 3, 5, 5));
            classicLogic = new ClassicLogic();
            classicLogic.choosePuzzle(choice);
            for(int x=0;x<9;x=x+3){
                for(int y=0;y<9;y=y+3){
                    JPanel jp = new JPanel();
                    GridLayout g = new GridLayout(3,3);
                    jp.setLayout(g);
                    jp.setBorder(BorderFactory.createLineBorder(Color.black,3));
                    for(int i=x;i<x+3;i++){
                        for(int j=y;j<y+3;j++){
                            if(classicLogic.getBoard()[i][j]==0){
                                jp.add(new ClassicCell(clasnkillfontsize,i,j,item4,this,false,"",classicLogic,isWordoku));
                            }
                            else{
                                jp.add(new ClassicCell(clasnkillfontsize,i,j,item4,this,true,String.valueOf(classicLogic.getBoard()[i][j]),classicLogic,isWordoku));
                            }
                        }

                    }
                    classicPanel.add(jp);
                }
            }
            frame.getContentPane().add(classicPanel);
            addHelpToMenu();
            frame.invalidate();
            frame.validate();
            frame.pack();
            frame.setLocationRelativeTo(null);
        }
    }

    /**
     * Αρχικοποιεί το Panel του Killer Sudoku
     */
    public void initializeKillerSudokuPanel() {
        if(KillerChoices.isEmpty()){
            JOptionPane.showMessageDialog(frame,resourceBundle.getString("You")+resourceBundle.getString("Have")+" "+resourceBundle.getString("Solved")+" "+resourceBundle.getString("All")+" "+resourceBundle.getString("The")+" "+"Killer Sudoku");
            frame.dispose();
            initializeFrame();
            initializeStartPanel();
        }
        else{
            choice = getRandomChoice("Killer");
            killerPanel = new JPanel();
            frame.setTitle("Killer");
            frame.getContentPane().remove(startPanel);
            killerPanel.setPreferredSize(new Dimension(800, 700));
            killerPanel.setLayout(new java.awt.GridLayout(3, 3));
            killerLogic = new KillerLogic();
            killerLogic.choosePuzzle(choice);
            for(int x=0;x<9;x=x+3){
                for(int y=0;y<9;y=y+3){
                    JPanel jp = new JPanel();
                    GridLayout g = new GridLayout(3,3);
                    jp.setLayout(g);
                    jp.setBorder(BorderFactory.createLineBorder(Color.black,1));
                    for(int i=x;i<x+3;i++){
                        for(int j=y;j<y+3;j++){
                            if(killerLogic.cellOfSum(i+1,j+1)){
                                jp.add(new KillerCell(clasnkillfontsize,i,j,item4,this,killerLogic.getSum(i+1,j+1),killerLogic.getColor(i+1,j+1),killerLogic));
                            }
                            else{
                                jp.add(new KillerCell(clasnkillfontsize,i,j,item4,this, 0,killerLogic.getColor(i+1,j+1),killerLogic));
                            }
                        }

                    }
                    killerPanel.add(jp);
                }
            }
            frame.getContentPane().add(killerPanel);
            addHelpToMenu();
            frame.invalidate();
            frame.validate();
            frame.pack();
            frame.setLocationRelativeTo(null);
        }
    }

    /**
     * Αρχικοποιεί το Panel του Duidoku Sudoku
     */
    public void initializeDuidokuPanel() {
        duiPanel = new JPanel();
        frame.setTitle("Duidoku");
        frame.getContentPane().remove(startPanel);
        chooseRepresentation();
        duiPanel.setPreferredSize(new Dimension(800, 700));
        duiPanel.setLayout(new java.awt.GridLayout(2, 2, 5, 5));
        duiLogic = new DuiLogic();
        for(int x=0;x<4;x=x+2){
            for(int y=0;y<4;y=y+2){
                JPanel jp = new JPanel();
                GridLayout g = new GridLayout(2,2);
                jp.setLayout(g);
                jp.setBorder(BorderFactory.createLineBorder(Color.black,3));
                for(int i=x;i<x+2;i++){
                    for(int j=y;j<y+2;j++){
                        DuiCell c = new DuiCell(duifontsize,i,j,item4,this,isWordoku,duiLogic);
                        board[i][j]=c;
                        jp.add(c);
                    }
                }
                duiPanel.add(jp);
            }
        }
        reInitialize(duiPanel);

    }

    /**
     * Ελέγχει στον πίνακα κελιά που δεν μπορεί να προσθεθεί αριθμός
     * και απενεργεποιεί τη δυνατότητα επιλογής τους από τον παίχτη
     */
    public boolean scanBoardForDisabledCells(){
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                if(duiLogic.getAvailableValues(i,j).isEmpty()){
                    board[i][j].disableCell();
                }
            }
        }
        if(duiLogic.endGame()){
            if(duiLogic.getPlayersturn()){
                JOptionPane.showMessageDialog(frame,resourceBundle.getString("You")+" "+resourceBundle.getString("Lose"));
                if(!(player==null)){
                    player.defeat();
                }
            }
            else {
                JOptionPane.showMessageDialog(frame,resourceBundle.getString("You")+" "+resourceBundle.getString("Win"));
                if(!(player==null)){
                    player.victory();
                }
            }
            if(!isIncognito){
                data.saveToBinaryFile(player);
            }
            endGame();
        }
        return duiLogic.endGame();
    }

    /**
     * Γεμίζει ένα κελί για την σειρά του Bot
     * @param row
     * @param column
     * @param value
     */
    public void fillBotCell(int row,int column,int value){
        board[row][column].setBotValue(value);
    }

    /**
     * Καλεί την HideButtons για να κρύψει τα κουμπία από
     * προηγούμενα κελιά
     * @param row
     * @param column
     */
    public void clearDuiCells(int row,int column){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(!(i==row && j==column)){
                    board[i][j].hideButtons();
                }
            }
        }
    }

    /**
     * Επιστρέφει το Panel του Duidoku Sudoku
     * @return duiPanel
     */
    public JPanel getDuiPanel(){
        return duiPanel;
    }

    /**
     * Επιστρέφει το Panel του Κλασσικού Sudoku
     * @return classicPanel
     */
    public JPanel getClassicPanel(){
        return classicPanel;
    }

    /**
     * Επιστρέφει το Panel του Killer Sudoku
     * @return killerPanel
     */
    public JPanel getKillerPanel(){
        return killerPanel;
    }

    /**
     * Αφαιρεί όλα τα στοιχεία από το Frame
     * @param panel
     */
    public void reInitialize(JPanel panel){
        frame.getContentPane().add(panel);
        frame.invalidate();
        frame.validate();
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Εμφανίζει ένα μήνυμα στην οθόνη όταν τελειώσει οποιοδήποτε παιχνίδι
     */
    public void endGame(){
        Object[] options = {resourceBundle.getString("New")+" "+resourceBundle.getString("Game"), resourceBundle.getString("Close")};
        JOptionPane jop = new JOptionPane(
                null,JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[0]);
        JDialog dialog = jop.createDialog(null, resourceBundle.getString("EndGame"));
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);
        String a3 = (String) jop.getValue();
        if (a3.equals(resourceBundle.getString("New")+" "+resourceBundle.getString("Game"))) {
            frame.dispose();
            initializeFrame();
            initializeStartPanel();
        }
        else{
            frame.dispose();
        }
    }

}
