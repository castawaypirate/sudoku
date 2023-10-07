import org.junit.*;

import static org.junit.Assert.*;

public class ClassicLogicTest {

    @BeforeClass
    public static void setUpClass(){
    }

    @AfterClass
    public static void tearDownClass(){
    }

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }

    @Test
    public void testChoosePuzzle() {
        System.out.println("choosePuzzle");
//        ClassicLogic instance = new ClassicLogic();
////        instance.choosePuzzle(5);
////        ClassicLogic expResult = new ClassicLogic();
////        expResult.choosePuzzle(5);
////        assertEquals(expResult,instance);
    }

//    @Test
//    public void getSolution() {
//    }

//    @Test
//    public void getBoard() {
//    }

    @Test
    public void setValueToBoard() {
        System.out.println("setValueToBoard");
//        int table[][]= new int[][]{{2,7,8,5,6,1,3,4,9,
//                6, 1, 5, 4, 9, 3, 8, 2, 7,
//                3, 4,9, 7, 8, 2, 6, 5, 1,
//                9, 3, 1, 2, 5, 8, 7, 6, 4,
//                5, 8, 4, 9, 7, 6, 2, 1, 3,
//                7, 2, 6,3, 1, 4,5, 9, 8,
//                8, 5, 2, 1, 4, 7, 9, 3,6,
//                4, 9, 7, 6, 3, 5, 1, 8, 2,
//                1, 6, 3, 8, 2, 9, 4, 7, 5}};
//        System.out.println("setValueToBoard");
//        ClassicLogic classicLogic = new ClassicLogic();
//        classicLogic.board = table;
//        classicLogic.choosePuzzle(5);
//        classicLogic.setValueToBoard(1,1,0);
//        int expResult = 0;
//        assertEquals(expResult,classicLogic.board[1][1]);
    }

//    @Test
//    public void showBoard() {
//    }

//    @Test
//    public void isCellCorrect() {
//    }

    @Test
    public void isGameOver() {
        System.out.println("isGameOver");
        Logic logic = new Logic();
        boolean expResult = true;
        assertEquals(expResult,logic.isGameOver());
    }

    @Test
    public void isGameCorrect() {
        System.out.println("isGameCorrect");
    }

    @Test
    public void getIncorrectCells() {
    }

    @Test
    public void getAvailableValues() {
    }
}