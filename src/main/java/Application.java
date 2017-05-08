import org.apache.commons.cli.*;

import static utils.Utilities.isInteger;

public class Application {
    public static void main(String[] args){
        Options options = new Options();

        Option arguA = new Option("a","ArgumentA",true,"The argument A for A Shuffle");
        arguA.setRequired(false);
        options.addOption(arguA);

        Option filePath = new Option("rf","ReadFile",true,"The file path for the card");
        filePath.setRequired(true);
        options.addOption(filePath);

        Option times = new Option("sr","ShuffleRound",true,"Number of rounds for shuffle");
        times.setRequired(false);
        options.addOption(times);

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

        String argA = cmd.getOptionValue("ArgumentA");
        Integer a = 0;
        if(isInteger(argA)){
            a = Integer.valueOf(argA);
        }

        String fileSource = cmd.getOptionValue("ReadFile");

    }
}
