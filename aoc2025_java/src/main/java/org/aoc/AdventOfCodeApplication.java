package org.aoc;

import org.aoc.util.FileReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AdventOfCodeApplication implements CommandLineRunner {

    final FileReader fileReader;
    final DayRunner dayRunner;

    public AdventOfCodeApplication(FileReader fileReader, DayRunner dayRunner) {
        this.fileReader = fileReader;
        this.dayRunner = dayRunner;
    }

    public static void main(String[] args) {
        SpringApplication.run(AdventOfCodeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final String result = dayRunner.runDay(5, 2);
        System.out.println("=====================================");
        System.out.println("FINAL RESULT: " + result);
        System.out.println("=====================================");
    }
}