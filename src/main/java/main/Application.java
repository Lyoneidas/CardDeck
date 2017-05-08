package main;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.commons.cli.*;
import utils.Utilities;
import utils.exception.ShuffleException;
import utils.impl.AShuffle;
import utils.interf.Shuffle;

import java.io.IOException;
import java.util.Arrays;

import static utils.Constants.INVALID_VALUE;
import static utils.Utilities.isInteger;
import static utils.Utilities.writeToCSV;

public class Application {

    public static void main(String[] args) {
        Options options = new Options();

        ArgumentSetup(options);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
            return;
        }

        Integer a = getIntegerArguement(cmd, "ArgumentA");
        String fileSource = cmd.getOptionValue("ReadFile");
        String outputDir = cmd.getOptionValue("Output");
        Integer round = getIntegerArguement(cmd, "ShuffleRound");

        Shuffle shuffle = AShuffle.getInstance();
        Integer[] inputs = Utilities.readCSV(fileSource);

        if (!a.equals(INVALID_VALUE)) {
            shuffle.setA(a);
        }

        if (!round.equals(INVALID_VALUE)) {
            shuffle.setShuffleRounds(round);
        }

        invokeShuffle(outputDir, shuffle, inputs);


    }

    private static void invokeShuffle(String outputDir, Shuffle shuffle, Integer[] inputs){
        try {
            Integer[] shuffleResult = shuffle.shuffle(inputs);
            System.out.println("Original sequence: ");
            Arrays.stream(inputs).forEach(integer -> System.out.print(integer+" "));
            System.out.println();
            System.out.println("After shuffling: ");
            Arrays.stream(shuffleResult).forEach(integer -> System.out.print(integer+" "));
            writeToCSV(shuffleResult,outputDir);
        } catch (ShuffleException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("ERROR: Failed to write to dir: "+outputDir);
        }
    }

    private static Integer getIntegerArguement(CommandLine cmd, String argumentA) {
        String argA = cmd.getOptionValue(argumentA);
        Integer a = INVALID_VALUE;
        if (isInteger(argA)) {
            a = Integer.valueOf(argA);
        }
        return a;
    }

    private static void ArgumentSetup(Options options) {
        Option arguA = new Option("a", "ArgumentA", true, "The argument A for A Shuffle");
        arguA.setRequired(false);
        options.addOption(arguA);

        Option filePath = new Option("rf", "ReadFile", true, "The file path for the card");
        filePath.setRequired(true);
        options.addOption(filePath);

        Option times = new Option("sr", "ShuffleRound", true, "Number of rounds for shuffle");
        times.setRequired(false);
        options.addOption(times);

        Option output = new Option("o","Output",true,"output file directory");
        output.setRequired(true);
        options.addOption(output);
    }
}
