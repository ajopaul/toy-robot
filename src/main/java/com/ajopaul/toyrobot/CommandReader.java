package com.ajopaul.toyrobot;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by ajopaul on 26/6/18.
 * Commandline reader application frontend
 */
public class CommandReader {


    private static final Scanner scan = new Scanner(System.in);
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_PLACE = "place";
    private static final String COMMAND_MOVE = "move";
    private static final String COMMAND_LEFT = "left";
    private static final String COMMAND_RIGHT = "right";
    private static final String COMMAND_REPORT = "report";
    private static final String COMMAND_HELP = "help";

    private boolean loaded = false;

    private ToyRobot toyRobot;

    private static String[] validCommands = new String[] {COMMAND_EXIT,COMMAND_MOVE,COMMAND_LEFT, COMMAND_RIGHT, COMMAND_REPORT, COMMAND_HELP};

    public CommandReader() {
        toyRobot = new ToyRobot();
    }

    /**
     * Displays and prompt for user to enter commands
     */
    public static void printPrompt(){
        System.out.println();
        System.out.print("$> ");
    }

    /**
     * To initialise scanning of input from command line.
     */
    public void init(){
        System.out.println("\n\n#################### TOY ROBOT ###############################");
        System.out.println    ("Type commands here. type 'help' for options or 'exit' to quit.");
        System.out.println    ("#################### TOY ROBOT ###############################");
        String commandText = "";


        while(!commandText.toLowerCase().equals(COMMAND_EXIT)){
            printPrompt();
            commandText = scan.nextLine().trim().toLowerCase();
            String message = processCommand(commandText);
            if(null != message){
                System.out.println(message);
            }
        }
        System.out.println("Thank you for using ToyRobot!");
    }

    /**
     * Process the given command and redirect accordingly.
     * @param commandText
     * @return
     */
    public String processCommand(String commandText){

        String message = null;
        if(commandText.contains(COMMAND_PLACE)){
            if(!isPlaceCommandValid(commandText)){
                message = "Invalid PLACE command. type 'help' for options.";
            }else{
                String placeArgs = commandText.substring(6);
                loaded = true;
                processPlaceCommand(placeArgs);
            }
        }else if(!Arrays.asList(validCommands).contains(commandText)){
            message = "Unknown Command. type 'help' for options.";
        }else if(!loaded && !commandText.equals(COMMAND_PLACE) && !commandText.equals(COMMAND_HELP) && !commandText.equals(COMMAND_EXIT)){
            message = "Invalid initial command. type 'help' for options.";
        }else{
            message = processOtherCommand(commandText);
        }
        return message;
    }


    private void processPlaceCommand(String placeArgs) {
        String[] split = placeArgs.split(",");
        toyRobot.commandPlace(Integer.parseInt(split[0]), Integer.parseInt(split[1]),split[2]);
    }

    /**
     * Checks against the regex pattern if the command is valid for issuing
     * place command to toyrobot.
     * @param commandText
     * @return
     */
    private static boolean isPlaceCommandValid(String commandText) {
        boolean isValid = false;

        if(commandText.length() == 15 || commandText.length() == 14)
        {
            Pattern p = Pattern.compile("[place]\\s\\d[,]\\d[,](?:north|south|west|east)");
            java.util.regex.Matcher m = p.matcher(commandText);
            if (m.find()) {
                isValid = true;
            }
        }
        return isValid;
    }

    /**
     * Process the navigation or other commands like help and report.
     * @param commandText
     * @return
     */
    private  String processOtherCommand(String commandText) {
        String message = null;
        switch (commandText){
            case COMMAND_MOVE : toyRobot.commandMove();
                break;
            case COMMAND_LEFT: toyRobot.commandLeft();
                break;
            case COMMAND_RIGHT: toyRobot.commandRight();
                break;
            case COMMAND_REPORT: message = toyRobot.commandReport();
                break;
            case COMMAND_HELP : printHelp();
        }
        return message;
    }

    /**
     * Displays help summary.
     */
    private static void printHelp() {
        String message = "Type a command and press ENTER key" +
                "\nAvailable commands:\n" +
                "PLACE X,Y,F\n" +
                "MOVE\n" +
                "LEFT\n" +
                "RIGHT\n" +
                "REPORT\n" +
                "EXIT\n" +
                "\n" +
                "PLACE will put the toy robot on the table (5x5) in position X,Y and facing NORTH, SOUTH, EAST or WEST.\n" +
                "valid values for X,Y are 0,1,2,3,4. \n" +
                "The first valid command to the robot is a PLACE command\n" +
                "MOVE will move the toy robot one unit forward in the direction it is currently facing.\n" +
                "LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the robot.\n" +
                "REPORT will announce the X,Y and orientation of the robot.\n" +
                "EXIT will quit the application";
        System.out.println(message);
    }
}
