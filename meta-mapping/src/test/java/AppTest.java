import org.junit.Test;

import static org.junit.Assert.assertTrue;
/**
 * Application test.
 */
public class AppTest {

    /**
     * Issue: Add at least one assertion to this test case.
     * Solution: Inserted assertion to check whether the execution of the main method in {@link App}
     * throws an exception.
     */

    @Test
    public void shouldExecuteApplicationWithoutException() {
        try{
            App.main(new String[]{});
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
