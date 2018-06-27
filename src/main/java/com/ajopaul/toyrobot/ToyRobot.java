package com.ajopaul.toyrobot;

import com.ajopaul.toyrobot.exceptions.AppException;

/**
 * Created by ajopaul on 25/6/18.
 * ToyRobot Application to simulate toy robot moving on a square
 * table top of 5x5
 */
public class ToyRobot {

    private int x=0,y=0;
    private int boundary = 5;
    private int base = 0;


    private DIRECTION direction;

    /**
     * Initialize the toy with given (x,y) position
     * and the direction.
     * @param x
     * @param y
     * @param directionStr
     */
    public void commandPlace(int x,int y, String directionStr) throws AppException {
        this.x = x >= base && x < boundary ? x:this.x;
        this.y = y >= base && y < boundary ? y:this.y;
        try {
            this.direction = DIRECTION.valueOf(directionStr.toUpperCase());
        }catch(Exception aex){
            throw new AppException("Unknown direction");
        }
    }

    /**
     * Moves either x or y axis by 1 unit, based on the direction
     * of the toy.
     */
    public void commandMove(){
        switch (direction){
            case NORTH:y = y < (boundary -1) ? y+1 : y;
                break;
            case SOUTH:y = y > base ? y-1:y;
                break;
            case WEST:x = x > base ? x-1:x;
                break;
            case EAST:x = x < (boundary -1) ? x+1:x;
                break;
        }
    }

    /**
     * Rotates the direction of the toy by 90degree.
     * to the left of the direction.
     */
    public void commandLeft() {
        switch (direction){

            case NORTH:direction = DIRECTION.WEST;
                break;
            case SOUTH:direction = DIRECTION.EAST;
                break;
            case WEST:direction = DIRECTION.SOUTH;
                break;
            case EAST:direction = DIRECTION.NORTH;
                break;
        }
    }

    /**
     * Rotates the direction of the toy by 90degree.
     * to the right of the direction.
     */
    public void commandRight() {
        switch (direction){

            case NORTH:direction = DIRECTION.EAST;
                break;
            case SOUTH:direction = DIRECTION.WEST;
                break;
            case WEST:direction = DIRECTION.NORTH;
                break;
            case EAST:direction = DIRECTION.SOUTH;
                break;
        }
    }

    /**
    Responds with the current position and facing
     direction of the toy.
     */
    public String commandReport() {
        return (x+","+y+","+direction);
    }
}

enum DIRECTION {
    NORTH ("NORTH"),
    SOUTH ("SOUTH"),
    EAST ("EAST"),
    WEST ("WEST");

    private final String direction;

    DIRECTION(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return this.direction;
    }
}

