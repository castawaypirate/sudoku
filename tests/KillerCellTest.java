import org.junit.*;

import javax.swing.*;

import java.awt.*;

public class KillerCellTest {

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
    public void testGetColor() {
        System.out.println("getColor");
        KillerCell cell1 = new KillerCell(10,1,1,new JMenuItem(),new Gui(),10,1,new KillerLogic());
        KillerCell cell2 = new KillerCell(10,1,1,new JMenuItem(),new Gui(),10,2,new KillerLogic());
        assertEquals(cell1.getColor(2).equals(Color.GREEN));
        assertEquals(cell2.getColor(3).equals(Color.CYAN));
    }

    private void assertEquals(boolean equals) {
    }
}