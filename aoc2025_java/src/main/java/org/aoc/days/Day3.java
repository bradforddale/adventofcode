package org.aoc.days;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day3 implements SolvedAdventDay {
    @Override
    public String part1(final List<String> input) {
        long sum = 0;
        for (final String batteryBank : input) {
            String currentHighestJoltage = findHighestJoltageOfXDigits(batteryBank, 2);
            sum += Long.parseLong(currentHighestJoltage);

        }
        return sum + "";
    }

    @Override
    public String part2(final List<String> input) {
        long sum = 0;
        for (final String batteryBank : input) {
            String currentHighestJoltage = findHighestJoltageOfXDigits(batteryBank, 12);
            sum += Long.parseLong(currentHighestJoltage);

        }
        return sum + "";
    }

    private String findHighestJoltageOfXDigits(final String batteryBank, final int xDigits) {
        String changingBatteryBank = String.copyValueOf(batteryBank.toCharArray());
        String currentHighestJoltage = "";

        currentHighestJoltage = recursivelyFindXLargestDigits(xDigits, changingBatteryBank, currentHighestJoltage);
        return currentHighestJoltage;
    }

    private String recursivelyFindXLargestDigits(int digit, String changingBatteryBank, String currentHighestJoltage) {
        if (digit == 0) {
            return currentHighestJoltage;
        }

        for (int i = 9; i > 0; i--) {
            int index = changingBatteryBank.indexOf(Integer.toString(i));
            if (index >= 0 && index < (changingBatteryBank.length() - digit + 1)) { //exists and is not the last number
                currentHighestJoltage = currentHighestJoltage.concat(Integer.toString(i));
                changingBatteryBank = changingBatteryBank.substring(index + 1);

                digit--;
                return recursivelyFindXLargestDigits(digit, changingBatteryBank, currentHighestJoltage);
            }
        }

        return "";
    }


}
