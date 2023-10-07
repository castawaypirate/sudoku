import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.*;

public class DuiLogicTest {

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
    public void fillEndBoard() {
        System.out.println("fillEndBoard");
        DuiLogic logic = new DuiLogic();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                logic.fillEndBoard(i,j);
            }
        }
        boolean expResult = true;
        assertEquals(expResult,logic.endGame());
    }

    @Test
    public void testEndGame() {
        System.out.println("endGame");
        DuiLogic logic = new DuiLogic();
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                logic.fillEndBoard(i,j);
            }
        }
        boolean expResult = false;
        assertEquals(expResult,logic.endGame());
    }

    @Test
    public void testBotTurn() {
        System.out.println("botTurn");
        DuiLogic logic = new DuiLogic();
        HashMap bot = logic.botTurn();
        boolean expResult = true;
        assertEquals(expResult,logic.getBoard()[(int) bot.get("row")][(int) bot.get("column")]==0);
    }
}