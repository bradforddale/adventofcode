package org.aoc;

import org.aoc.days.Day4;
import org.aoc.days.SolvedAdventDay;
import org.aoc.util.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DayRunner {

//    final Day4 day4;
    final FileReader fileReader;
    final Map<Integer, SolvedAdventDay> dayMap = new HashMap<>();

    @Autowired
    public DayRunner(FileReader fileReader,
                      final Day4 day4) {
        this.fileReader = fileReader;
        dayMap.put(4, day4);
    }

    public String runDay(final int day,
                         final int part) {
        final String filename = String.format("src/main/resources/day%s", day);
        System.out.println(String.format("Run Day %s Part %s with file %s", day, part, filename));

        final List<String> input = fileReader.readFile(filename);
        final SolvedAdventDay solvedDay = dayMap.get(day);

        return part == 1
                ? solvedDay.part1(input)
                : solvedDay.part2(input);
    }
}
