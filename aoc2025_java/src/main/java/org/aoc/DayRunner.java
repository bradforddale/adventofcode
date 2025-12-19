package org.aoc;

import org.aoc.days.*;
import org.aoc.util.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DayRunner {

    final FileReader fileReader;
    final Map<Integer, SolvedAdventDay> dayMap = new HashMap<>();

    @Autowired
    public DayRunner(final FileReader fileReader,
                     final Day1 day1,
                     final Day2 day2,
                     final Day3 day3,
                     final Day4 day4,
                     final Day5 day5,
//                     final Day6 day6,
//                     final Day7 day7,
//                     final Day8 day8,
//                     final Day9 day9,
//                     final Day10 day10,
//                     final Day11 day11,
                     final Day12 day12) {
        this.fileReader = fileReader;
        dayMap.put(1, day1);
        dayMap.put(2, day2);
        dayMap.put(3, day3);
        dayMap.put(4, day4);
        dayMap.put(5, day5);
//        dayMap.put(6, day6);
//        dayMap.put(7, day7);
//        dayMap.put(8, day8);
//        dayMap.put(9, day9);
//        dayMap.put(10, day10);
//        dayMap.put(11, day11);
        dayMap.put(12, day12);
    }

    public String runDay(final int day,
                         final int part) {
        return runDay(day, part, "");
    }

    public String runDay(final int day,
                         final int part,
                         final String testFileAddendum) {
        final String filename = String.format("src/main/resources/day%s%s", day, testFileAddendum);
        System.out.println(String.format("Run Day %s Part %s with file %s", day, part, filename));

        final List<String> input = fileReader.readFile(filename);
        final SolvedAdventDay solvedDay = dayMap.get(day);

        return part == 1
                ? solvedDay.part1(input)
                : solvedDay.part2(input);
    }
}
