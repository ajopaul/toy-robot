package com.ajopaul.toyrobot;

import com.ajopaul.toyrobot.exceptions.AppException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


/**
 * Created by ajopaul on 26/6/18.
 */
public class TestToyRobot {

    ToyRobot toyRobot;
    @Before
    public void setup(){
        toyRobot = new ToyRobot();
    }

    @Test
    public void testToyRobotPlaceInvalidDirection1(){
        try {
            toyRobot.commandPlace(0, 0, "northeast");
            fail();
        }catch(AppException ae){
            assertThat(ae.getMessage(),is("Unknown direction"));
        }
    }

    @Test
    public void testToyRobotPlaceInvalidDirection2(){
        try {
            toyRobot.commandPlace(0, 0, null);
            fail();
        }catch(AppException ae){
            assertThat(ae.getMessage(),is("Unknown direction"));
        }
    }

    @Test
    public void testToyRobotPlaceInvalidPoints1()  {
        toyRobot.commandPlace(-1,-1,"north");
        String message = toyRobot.commandReport();
        assertEquals("0,0,NORTH", message);
    }

    @Test
    public void testToyRobotPlaceInvalidPoints2()  {
        toyRobot.commandPlace(-1,-1,"north");
        String message = toyRobot.commandReport();
        assertEquals("0,0,NORTH", message );
    }

    @Test
    public void testDirectionLeftNorth()  {
        toyRobot.commandPlace(0,0,"north");
        toyRobot.commandLeft();
        String message = toyRobot.commandReport();
        assertEquals("0,0,WEST", message);
    }
    @Test
    public void testDirectionLeftWest()  {
        toyRobot.commandPlace(0,0,"west");
        toyRobot.commandLeft();
        String message = toyRobot.commandReport();
        assertEquals("0,0,SOUTH",message);
    }
    @Test
    public void testDirectionLeftSouth()  {
        toyRobot.commandPlace(0,0,"SOUTH");
        toyRobot.commandLeft();
        String message = toyRobot.commandReport();
        assertEquals("0,0,EAST", message);
    }
    @Test
    public void testDirectionLeftEast()  {
        toyRobot.commandPlace(0,0,"EAST");
        toyRobot.commandLeft();
        String message = toyRobot.commandReport();
        assertEquals("0,0,NORTH", message);
    }

    @Test
    public void testDirectionRightNorth()  {
        toyRobot.commandPlace(0,0,"north");
        toyRobot.commandRight();
        String message = toyRobot.commandReport();
        assertEquals("0,0,EAST", message);
    }
    @Test
    public void testDirectionRightWest()  {
        toyRobot.commandPlace(0,0,"west");
        toyRobot.commandRight();
        String message = toyRobot.commandReport();
        assertEquals("0,0,NORTH", message);
    }
    @Test
    public void testDirectionRightSouth()  {
        toyRobot.commandPlace(0,0,"SOUTH");
        toyRobot.commandRight();
        String message = toyRobot.commandReport();
        assertEquals("0,0,WEST", message);
    }
    @Test
    public void testDirectionRightEast()  {
        toyRobot.commandPlace(0,0,"EAST");
        toyRobot.commandRight();
        String message = toyRobot.commandReport();
        assertEquals("0,0,SOUTH",message);
    }

    @Test
    public void testMovement1() {
        toyRobot.commandPlace(1,2,"east");
        toyRobot.commandMove();
        toyRobot.commandMove();
        toyRobot.commandLeft();
        toyRobot.commandMove();
        String message = toyRobot.commandReport();
        assertEquals("3,3,NORTH", message);
    }
    @Test
    public void testMovement2() {
        toyRobot.commandPlace(0,2,"east");
        toyRobot.commandMove();
        toyRobot.commandMove();
        toyRobot.commandLeft();
        toyRobot.commandMove();
        toyRobot.commandMove();
        toyRobot.commandMove();
        toyRobot.commandMove();
        toyRobot.commandMove();
        String message = toyRobot.commandReport();
        assertEquals("2,4,NORTH", message);
    }
}
