import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JUnitCycleTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("After Each");
    }
}
