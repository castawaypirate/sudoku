import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LoadFilesTest {

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
    public void testGetSolution() {
        System.out.println("getSolution");
        LoadFiles loadFiles = new LoadFiles();
        ArrayList<String[]> expSol = new ArrayList<>(){
            {
                add(new String[] {
                        "2","7","8.","5","6","1","3","4","9."});
                add(new String[] {
                        "6","1","5","4","9.","3.","8","2","7"});
                add(new String[] {
                        "3","4","9","7.","8.","2.","6.","5.","1."});
                add(new String[] {
                        "9","3.","1.","2","5.","8","7","6.","4."});
                add(new String[] {
                        "5","8","4.","9","7","6","2.","1","3"});
                add(new String[] {
                        "7.","2.","6","3","1.","4","5.","9.","8."});
                add(new String[] {
                        "8.","5.","2.","1.","4.","7.","9","3","6"});
                add(new String[] {
                        "4","9","7","6.","3.","5","1","8","2"});
                add(new String[] {
                        "1.","6","3","8","2","9","4.","7","5"});
            }
        };
        int sum=0;
        for(int i=0;i<expSol.size();i++){
            for(int j=0;j<expSol.get(i).length;j++){
                if(expSol.get(i)[j].equals(loadFiles.getSolution(1,false).get(i)[j])){
                    sum++;
                }
            }
        }
        int expResult = 81;
        assertEquals(expResult,sum);

    }

    @Test
    public void testGetSumGroups() {
        System.out.println("getSumGroups");
        LoadFiles loadFiles = new LoadFiles();
        loadFiles.getSolution(1,true);
        int expResult = 31;
        assertEquals(expResult,loadFiles.getSumGroups().size());

    }

    @Test
    public void testGetUniqueGroups() {
        System.out.println("getUniqueGroups");
        LoadFiles loadFiles = new LoadFiles();
        loadFiles.getSolution(1,true);
        int expResult = 81;
        assertEquals(expResult,loadFiles.getUniqueGroups().size());
    }

    @Test
    public void testGetColorGroups() {
        System.out.println("getColorGroups");
        LoadFiles loadFiles = new LoadFiles();
        loadFiles.getSolution(1,true);
        int expResult = 1;
        int result = loadFiles.getColorGroups().get(1);
        assertEquals(expResult,result);
    }

}