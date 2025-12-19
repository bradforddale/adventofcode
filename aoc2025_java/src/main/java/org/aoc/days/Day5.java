package org.aoc.days;

import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Day5 implements SolvedAdventDay{

    @Override
    public String part1(final List<String> input) {
        final List<String> freshRanges = new ArrayList<>();
        final List<Long> ingredientIds = new ArrayList<>();

        extractFreshInfoFromInput(input, freshRanges, ingredientIds);

        System.out.println("Fresh ranges: " + freshRanges);
        System.out.println("Ingredient ids: " + ingredientIds);

        int countFreshIngredients = 0;

        for (final Long ingredientId : ingredientIds) {
            for (final String freshRange : freshRanges) {
                final long rangeStart = Long.parseLong(freshRange.split("-")[0]);
                final long rangeEnd = Long.parseLong(freshRange.split("-")[1]);

                if (ingredientId >= rangeStart && ingredientId <= rangeEnd) {
                    countFreshIngredients++;
                    break;
                }
            }
        }

        return countFreshIngredients + "";
    }

    @Override
    public String part2(final List<String> input) {
        final List<String> freshRanges = new ArrayList<>();
        final List<Long> ingredientIds = new ArrayList<>();

        extractFreshInfoFromInput(input, freshRanges, ingredientIds);

        System.out.println("Fresh ranges: " + freshRanges);
        System.out.println("Ingredient ids: " + ingredientIds);

        List<Range<Long>> sortedRanges = freshRanges.stream()
                .map((rangeLine) -> {
                    Long rangeStart = Long.parseLong(rangeLine.split("-")[0]);
                    Long rangeEnd = Long.parseLong(rangeLine.split("-")[1]);
                    return Range.of(rangeStart, rangeEnd);
                })
                .sorted(Comparator.comparing((Range<Long> r) -> r.getMinimum()).thenComparing((Range<Long> r) -> r.getMaximum()))
                .toList();

        System.out.println("Sorted fresh ranges: " + sortedRanges);

        long totalFreshIngredients = 0;

        // keep a range and grow it until it there's no overlap then count it and null it to start over
        Range<Long> growingRange = null;

        for (int i = 0; i < sortedRanges.size(); i++) {
            final Range<Long> currentRange = sortedRanges.get(i);

            if (growingRange == null) {
//                totalFreshIngredients += currentRange.getMaximum() - currentRange.getMinimum() + 1;
                growingRange = currentRange;
            } else {
                if (growingRange.isOverlappedBy(currentRange)) {
                    // Range must keep growing
                    growingRange = Range.of(Math.min(currentRange.getMinimum(), growingRange.getMinimum()), Math.max(currentRange.getMaximum(), growingRange.getMaximum()));
                } else {
                    totalFreshIngredients += growingRange.getMaximum() - growingRange.getMinimum() + 1;
                    growingRange = currentRange;
                }
            }
        }
        // what's left in the last growing range
        totalFreshIngredients += growingRange.getMaximum() - growingRange.getMinimum() + 1;

        return totalFreshIngredients + "";

    }

    private static void extractFreshInfoFromInput(List<String> input, List<String> freshRanges, List<Long> ingredientIds) {
        boolean reachedEndOfRanges = false;
        for (final String line : input) {
            if (line.isEmpty()) {
                reachedEndOfRanges = true;
                continue;
            }

            if (!reachedEndOfRanges) {
                freshRanges.add(line);
            } else {
                ingredientIds.add(Long.parseLong(line));
            }
        }
    }

}
