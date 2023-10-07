import org.junit.*;

import javax.swing.*;

import static org.junit.Assert.*;

public class ClassicCellTest {

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
    public void testEndClassic() {
        System.out.println("endClassic");
        ClassicLogic logic = new ClassicLogic();
        ClassicCell cell = new ClassicCell(10,1,1,new JMenuItem(),new Gui(),false,"",new ClassicLogic(),false);
        boolean result = logic.isGameOver();
        boolean expResult = true;
        assertEquals(expResult,result);
    }

    @Test
    public void testAvailableChars() {
        System.out.println("availableChars");
        ClassicCell cell1 = new ClassicCell(10,1,1,new JMenuItem(),new Gui(),false,"",new ClassicLogic(),false);
        ClassicCell cell2 = new ClassicCell(10,1,1,new JMenuItem(),new Gui(),false,"",new ClassicLogic(),true);
        boolean expResult = false;
        assertEquals(expResult,cell1.availableChars('5',false));
        assertEquals(expResult,cell2.availableChars('D',true));
    }

}