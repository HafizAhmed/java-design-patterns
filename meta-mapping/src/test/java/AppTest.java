import org.junit.Test;

import static org.junit.Assert.fail;
/**
 * Application test.
 */
public class AppTest {

    /**
     * the application should not throw exception while executing
     */
    @Test
    public void shouldExecuteApplicationWithoutException() {
        try{
            App.main(new String[]{});
        } catch (Exception e) {
            fail();
        }
    }
}
