# toy-robot
## ***Application Overview***

The application is a simulation of a toy robot moving on a square tabletop, of dimensions 5 units x 5 units.

There are no other obstructions on the table surface.
The robot is free to roam around the surface of the table, but must be prevented from falling to destruction. Any movement
that would result in the robot falling from the table will be prevented, however further valid movement commands must still
be allowed.

This application can read in commands of the following form -
* PLACE X,Y,F
* MOVE
* LEFT
* RIGHT
* REPORT

PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.

The origin (0,0) can be considered to be the SOUTH WEST most corner.

The first valid command to the robot is a PLACE command, after that, any sequence of commands may be issued, in any order, including another PLACE command. The application will discard all commands in the sequence until a valid PLACE command has been executed.

MOVE will move the toy robot one unit forward in the direction it is currently facing.

LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the robot.

REPORT will announce the X,Y and orientation of the robot.

## ***SETUP***

Make sure you have JDK8 and Maven installed in your machine. 

To run the application on a *nix machine:
<pre>
$mvn clean install
$mvn exec:java
</pre>

