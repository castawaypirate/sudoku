import org.junit.*;

import static org.junit.Assert.*;

public class PlayerTest {

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
    public void testVictory() {
        System.out.println("vicoty");
        Player player = new Player("Name");
        player.victory();
        player.victory();
        int expResult = 2;
        int result = player.getWins();
        assertEquals(expResult,result);
    }

    @Test
    public void testDefeat() {
        System.out.println("defeat");
        Player player = new Player("Name");
        player.victory();
        player.defeat();
        int expResult = 1;
        int result = player.getLoses();
        assertEquals(expResult,result);
    }

}