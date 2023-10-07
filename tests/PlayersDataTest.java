import org.junit.*;

import static org.junit.Assert.*;

public class PlayersDataTest {

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
    public void testExists() {
        System.out.println("exists");
        PlayersData data = new PlayersData();
        boolean expResults = true;
        assertEquals(expResults,data.exists());
    }

}