package org.aoc.days;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class Day4 implements SolvedAdventDay{

    @Override
    public String part1(final List<String> input) {
        // maps the line number to the positions of the rolls of paper (@)
        final Map<Integer, List<Integer>> originalPaperPositonsPerLine = loadOriginalFromInput(input);
        System.out.println("Map of positions: \n" + originalPaperPositonsPerLine);

        Map<Integer, List<Integer>> currentPaperPositonsPerLine = Map.copyOf(originalPaperPositonsPerLine);

        final AtomicInteger totalAccessiblePaper = new AtomicInteger();
        int currentAccessiblePaper = countAccessiblePaper(currentPaperPositonsPerLine, new HashMap<>());;

        totalAccessiblePaper.addAndGet(currentAccessiblePaper);
        return totalAccessiblePaper.toString();
    }

    @Override
    public String part2(final List<String> input) {
        // maps the line number to the positions of the rolls of paper (@)
        final Map<Integer, List<Integer>> originalPaperPositonsPerLine = loadOriginalFromInput(input);
        System.out.println("Map of positions: \n" + originalPaperPositonsPerLine);

        Map<Integer, List<Integer>> currentPaperPositonsPerLine = Map.copyOf(originalPaperPositonsPerLine);
        final AtomicInteger totalAccessiblePaper = new AtomicInteger();
        int currentAccessiblePaper;
        do {
            // SETUP
            Map<Integer, List<Integer>> nextPaperPositonsPerLine = new HashMap<>();

            // ITERATE
            currentAccessiblePaper = countAccessiblePaper(currentPaperPositonsPerLine, nextPaperPositonsPerLine);

            // GATHER RESULTS
            totalAccessiblePaper.addAndGet(currentAccessiblePaper);

            // PREPARE FOR NEXT ITERATION
            currentPaperPositonsPerLine = Map.copyOf(nextPaperPositonsPerLine);
            System.out.println(currentPaperPositonsPerLine);
        } while (currentAccessiblePaper != 0);

        return totalAccessiblePaper + "";
    }

    private int countAccessiblePaper(final Map<Integer, List<Integer>> currentPaperPositonsPerLine,
                                     final Map<Integer, List<Integer>> leftOverPaperPositionMap) {
        final AtomicInteger currentAccessiblePaper = new AtomicInteger();
        for (int x = 0; x < currentPaperPositonsPerLine.size(); x++) {
            final List<Integer> leftoverPapersForNextLine = new ArrayList<>();

            final List<Integer> currentLine = currentPaperPositonsPerLine.getOrDefault(x, new ArrayList<>());
            final List<Integer> previousLine = currentPaperPositonsPerLine.getOrDefault(x-1, new ArrayList<>());
            final List<Integer> nextLine = currentPaperPositonsPerLine.getOrDefault(x+1, new ArrayList<>());

            currentLine.stream()
                    .forEach(positionY -> {
                        if (isPaperAccessible(positionY, currentLine, previousLine, nextLine)) {
                            currentAccessiblePaper.getAndIncrement();
                        } else {
                            leftoverPapersForNextLine.add(positionY);
                        }
                    });

            // PREPARE FOR NEXT ITERATION
            leftOverPaperPositionMap.put(x, leftoverPapersForNextLine);

        }
        return currentAccessiblePaper.get();
    }

    private boolean isPaperAccessible(final int currentYPosition,
                                      final List<Integer> currentLine,
                                      final List<Integer> previousLine,
                                      final List<Integer> nextLine) {
        int inRange = 0;

        inRange += (int) currentLine.stream()
                .filter(paperPosition -> paperPosition >= currentYPosition - 1 && paperPosition <= currentYPosition + 1
                        && paperPosition != currentYPosition)
                .count();

        inRange += (int) previousLine.stream()
                .filter(paperPosition -> paperPosition >= currentYPosition - 1 && paperPosition <= currentYPosition + 1)
                .count();

        inRange += (int) nextLine.stream()
                .filter(paperPosition -> paperPosition >= currentYPosition - 1 && paperPosition <= currentYPosition + 1)
                .count();

        return inRange < 4;
    }

    private Map<Integer, List<Integer>> loadOriginalFromInput(final List<String> input) {
        final Map<Integer, List<Integer>> originalPaperPositonsPerLine = new HashMap<>();

        for (int lineNumber = 0; lineNumber < input.size(); lineNumber++) {
            final List<Integer> paperPerLine = new ArrayList<>();
            final String line = input.get(lineNumber);
            for (int position = 0; position < line.length(); position++) {
                if (line.charAt(position) == '@') {
                    paperPerLine.add(position);
                }
            }

            originalPaperPositonsPerLine.put(lineNumber, paperPerLine);
        }

        return originalPaperPositonsPerLine;
    }
}
