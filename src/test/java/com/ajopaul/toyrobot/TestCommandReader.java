package com.ajopaul.toyrobot;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ajopaul on 26/6/18.
 */
public class TestCommandReader {
    CommandReader commandReader;

    @Before
    public void setup(){
        commandReader = new CommandReader();
    }

    @Test
    public void testInvalidCommand() {
        String message = commandReader.processCommand("hello junk");
        assertTrue(message.toLowerCase().contains("unknown command"));
    }

    @Test
    public void testInvalidInitialCommand() {
        String message = commandReader.processCommand("left");
        assertTrue(message.toLowerCase().contains("invalid initial command"));
    }

    @Test
    public void testInvalidPlaceCommand1() {
        String message = commandReader.processCommand("place x,y,f");
        assertTrue(message.toLowerCase().contains("invalid place command"));
    }
    @Test
    public void testInvalidPlaceCommand2() {
        String message = commandReader.processCommand("place 0,0,northeast");
        assertTrue(message.toLowerCase().contains("invalid place command"));
    }

    @Test
    public void testInvalidPlaceCommand3() {
        String message = commandReader.processCommand(null);
        assertTrue(message == null);
    }

    @Test
    public void testPlaceCommand() {
        commandReader.processCommand("place 0,0,north");
        String message = commandReader.processCommand("report");
        assertEquals("0,0,NORTH", message);
    }

    @Test
    public void testMoveCommand() {
        commandReader.processCommand("place 0,0,north");
        commandReader.processCommand("move");
        String message = commandReader.processCommand("report");
        assertEquals("0,1,NORTH", message);
    }

    @Test
    public void testRightCommand() {
        commandReader.processCommand("place 4,4,east");
        commandReader.processCommand("move");
        commandReader.processCommand("right");
        commandReader.processCommand("move");
        String message = commandReader.processCommand("report");
        assertEquals("4,3,SOUTH", message);
    }
}
