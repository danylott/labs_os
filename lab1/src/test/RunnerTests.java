package test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.company.Runner;

public class RunnerTests {
    @Test
    void multiplier() {
        assertEquals(Runner.multiplier("5", "5"), 25);
        assertEquals(Runner.multiplier("3", "3"), 9);
    }
}
