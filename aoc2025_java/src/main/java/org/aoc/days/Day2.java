package org.aoc.days;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class Day2 implements SolvedAdventDay {
    @Override
    public String part1(final List<String> input) {
        final String[] ranges = input.get(0).split(",");

        long sum = 0;
        for (final String range : ranges) {
            final long rangeStart = Long.parseLong(range.split("-")[0]);
            final long rangeEnd = Long.parseLong(range.split("-")[1]);

            for (long i = rangeStart; i <= rangeEnd; i++) {
                sum = onlyMadeUpOfTwoSequences(sum, i);
            }
        }

        return sum + "";
    }

    private long onlyMadeUpOfTwoSequences(long sum,
                                          final long i) {
        final String currentNumber = Long.toString(i);
        // Since an invalid is "made only of some sequence of digits repeated twice" then we can just check if the first half matches the second half
        if ((currentNumber.length() % 2) == 0) {
            int midIndex = currentNumber.length() / 2;

            if (currentNumber.substring(0, midIndex).equalsIgnoreCase(currentNumber.substring(midIndex))) {
                sum += i;
            }
        }
        return sum;
    }

    @Override
    public String part2(final List<String> input) {
        final String[] ranges = input.get(0).split(",");

        long sum = 0;
        for (final String range : ranges) {
            final long rangeStart = Long.parseLong(range.split("-")[0]);
            final long rangeEnd = Long.parseLong(range.split("-")[1]);

            for (long i = rangeStart; i <= rangeEnd; i++) {
                sum = canBeMadeOfMultipleSequences(sum, i);
            }
        }

        return sum + "";
    }

    private long canBeMadeOfMultipleSequences(long sum,
                                              final long i) {
        final String currentNumber = Long.toString(i);
        /*
        Going from the smallest number of digits up, we do the following:
            1. Get up a sequence from the beginning starting with size 1
            2.
        */

        for (int digits = 1; digits < (currentNumber.length()/2)+1; digits++) {
            final String sequenceConsidering = currentNumber.substring(0, digits);

            final int count = StringUtils.countOccurrencesOf(currentNumber, sequenceConsidering);
            if ((count * sequenceConsidering.length() == currentNumber.length())) {
                sum += i;
                System.out.println("Found invalid number" + currentNumber + " with  sequence: " + sequenceConsidering + " sum = " + sum);
                break;
            }

            // TODO add a case to check if there is no possibility of sequence repeating correctly


        }
        return sum;
    }
}
