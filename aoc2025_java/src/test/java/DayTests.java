import org.aoc.AdventOfCodeApplication;
import org.aoc.DayRunner;
import org.aoc.util.FileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

@SpringBootTest(classes = AdventOfCodeApplication.class)
@ExtendWith(SpringExtension.class)

public class DayTests {
    final FileReader fileReader;
    final DayRunner dayRunner;

    @Autowired
    public DayTests(FileReader fileReader, DayRunner dayRunner) {
        this.fileReader = fileReader;
        this.dayRunner = dayRunner;
    }

    @Test
    public void day4() {
//        final List<String> fileContent = fileReader.readFile("src/main/resources/day4_test");
//        System.out.println(fileContent);
//        final String actual = dayRunner.day4(fileContent);
////        final String expected = "13";
//        final String expected = "43";
//        assertEquals(expected, actual);

        final String part1 = dayRunner.runDay(4, 1, "src/main/resources/day4_test");
        assertEquals("13", part1);
        System.out.println("Part 1 passed: " + part1 + "\n");

        final String part2 = dayRunner.runDay(4, 2, "src/main/resources/day4_test");
        assertEquals("43", part2);
        System.out.println("Part 2 passed: " + part2 + "\n");

    }
}
