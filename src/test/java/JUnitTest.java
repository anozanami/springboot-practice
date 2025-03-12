import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @DisplayName("1 + 2는 3이다.")
    @Test
    public void JUnitTest() {
        int a = 1;
        int b = 2;
        int sum = 3;
        Assertions.assertEquals(a+b, sum);
    }

    @DisplayName("나는 누구인가")
    @Test
    public void JUnitTest2() {
        String name = "윤여찬";
        String firstName = "윤";
        String lastName = "여찬";
        Assertions.assertEquals(name, firstName + lastName);
    }
}
