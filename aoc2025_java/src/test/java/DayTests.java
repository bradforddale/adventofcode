import org.aoc.AdventOfCodeApplication;
import org.aoc.DayRunner;
import org.aoc.util.FileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    // TODO Turn these into parameterised tests last

    @Test
    public void day2() {
        final String part1 = dayRunner.runDay(2, 1, "_test");
        assertEquals("1227775554", part1);
        System.out.println("Part 1 passed: " + part1 + "\n");

        final String part2 = dayRunner.runDay(2, 2, "_test");
        assertEquals("4174379265", part2);
        System.out.println("Part 2 passed: " + part2 + "\n");
    }

    @Test
    public void day4() {
        final String part1 = dayRunner.runDay(4, 1, "_test");
        assertEquals("13", part1);
        System.out.println("Part 1 passed: " + part1 + "\n");

        final String part2 = dayRunner.runDay(4, 2, "_test");
        assertEquals("43", part2);
        System.out.println("Part 2 passed: " + part2 + "\n");
    }
}
