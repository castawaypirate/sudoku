import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class KillerLogicTest {

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
//        KillerLogic instance = new KillerLogic();
//        instance.choosePuzzle(5);
//        KillerLogic expResult = new KillerLogic();
//        expResult.choosePuzzle(5);
//        assertEquals(expResult,instance);
    }

    @Test
    public void testGetUniqueTeam() {
        System.out.println("getUniqueTeam");
        KillerLogic instance = new KillerLogic();
        int result = instance.getUniqueTeam(10,10);
        int expResult = 0;
        assertEquals(expResult,result);
    }

    @Test
    public void testCellOfSum() {
        System.out.println("cellOfSum");
        KillerLogic instance = new KillerLogic();
        boolean result = instance.cellOfSum(10,10);
        boolean expResult = true;
        assertEquals(expResult,result);
    }

    @Test
    public void testGetSum() {
        System.out.println("getSum");
        KillerLogic instance = new KillerLogic();
        instance.choosePuzzle(1);
        int expResult = 11;
        assertEquals(expResult,instance.getSum(1,1));
    }

    @Test
    public void testGetColor() {
        System.out.println("getColor");
        KillerLogic instance = new KillerLogic();
        instance.choosePuzzle(1);
        int expResult = 1;
        assertEquals(expResult,instance.getColor(1,1));
    }

    @Test
    public void testGetAvailableValues() {
        System.out.println("getAvailableValues");
        KillerLogic instance = new KillerLogic();
        instance.choosePuzzle(1);
        ArrayList expResult = new ArrayList<Integer>();
        for(int i=1;i<10;i++){
            expResult.add(i);
        }
        assertEquals(expResult,instance.getAvailableValues(1,1));
    }
}