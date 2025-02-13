package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public  class SimpleExplorer extends Explorer {
        @Override
        public boolean exploreMaze(Maze maze, int[] start, int[] finish){
            pos[0] = start[0]; pos[1] = start[1];
            int[] east = {0,1}, west = {0,-1};

            //initialize directions, make avaialable for north and south too
            if(pos[1] == 0) dir = east;
            else dir = west;

            while(!checkWin(finish)){
                if(dir[0] == 0){//facing east or west
                    //if you can turn right, turn right and move forward
                    if(maze.GetMaze()[pos[0] + dir[1]][pos[1]] != '#'){
                        TurnRight(true);
                        MoveForward(true);
                    }//if you can move forward do so
                    else if(maze.GetMaze()[pos[0]][pos[1] + dir[1]] != '#'){
                        MoveForward(true);

                    }//turn left and move forward
                    else if(maze.GetMaze()[pos[0]-dir[1]][pos[1]] != '#'){
                        TurnLeft(true);
                        MoveForward(true);
                    }
                    else{//deadend, turnaround
                        TurnLeft(true);
                        TurnLeft(true);
                    }
                }
                else{//facing north or south
                    //if you can turn right, turn right and move forward
                    if(maze.GetMaze()[pos[0]][pos[1] - dir[0]] != '#'){
                        TurnRight(true);
                        MoveForward(true);
                        if(pos[0] == start[0] && pos[1] == start[1]) return false;//this needs to be here because the check can be skipped by the double move
                    }//if you can move forward do so
                    else if(maze.GetMaze()[pos[0] + dir[0]][pos[1]] != '#'){
                        MoveForward(true);
                    }//turn left and move forward
                    else if(maze.GetMaze()[pos[0]][pos[1] + dir[0]] != '#'){
                        TurnLeft(true);
                        MoveForward(true);
                    }
                    else{//deadend, turn around
                        TurnLeft(true);
                        TurnLeft(true);
                    }
                }
                if(pos[0] == start[0] && pos[1] == start[1]) return false;//went in loop, maze is impossible
            }
            System.out.println(FactorizePath(path));
            return true;
        }
    }
