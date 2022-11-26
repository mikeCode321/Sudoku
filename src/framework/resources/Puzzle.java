package framework.resources;

import framework.board.panes.BoardPane;

public class Puzzle {
    public static int[][] numbers = new int[BoardPane.GRID_SIZE][BoardPane.GRID_SIZE];

    public int[][] puzzle = {  
                        {0,0,6,0,9,0,2,0,0},
                        {0,0,0,7,0,2,0,0,0},
                        {0,9,0,5,0,8,0,7,0},
                        {9,0,0,0,3,0,0,0,6},
                        {7,5,0,0,0,0,0,1,9},
                        {1,0,0,0,4,0,0,0,5},
                        {0,1,0,3,0,9,0,8,0},
                        {0,0,0,2,0,1,0,0,0},
                        {0,0,9,0,8,0,1,0,0}
                    };
                
    public void setNumbers(){
        for (int row = 0; row < BoardPane.GRID_SIZE; ++row) {
            for (int col = 0; col < BoardPane.GRID_SIZE; ++col) {
               numbers[row][col] = puzzle[row][col];
            }
         }
    }

    public int[][] getPuzzleNumbers(){
        return numbers;
    }
}
