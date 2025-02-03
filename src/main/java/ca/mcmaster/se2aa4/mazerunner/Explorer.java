package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public abstract class Explorer {
    private char[] path;//where moves will be stored
    private int[] pos;//explorer position
    private int[] dir;//west = [-1, 0], north = [0,1], east = [1,0], south = [0,-1]
    private int moveNum = 0;//number of moves
    
    private boolean checkWin(int[] destination){
        return pos == destination;
    }
    public char[] getPath(){
        return path;
    }
    private void TurnRight(){
        if(dir[1] == 0){
            dir[1] = -dir[0];
            dir[0] = 0;
        }
        else{
            dir[0] = dir[1];
            dir[1] = 0;
        }

        path[moveNum] = 'R';
        moveNum++;
    }
    private void TurnLeft(){
        if(dir[0] == 0){
            dir[0] = -dir[1];
            dir[1] = 0;
        }
        else{
            dir[1] = dir[0];
            dir[0] = 0;
        }

        path[moveNum] = 'L';
        moveNum++;
    }
    private void MoveForward(){
        pos[0] += dir[0];
        pos[1] += dir[1];

        path[moveNum] = 'F';
        moveNum++;
    }
    private String FactorizePath(char[][] path){
        return "";
    }

    abstract void exploreMaze(Maze maze);
}
