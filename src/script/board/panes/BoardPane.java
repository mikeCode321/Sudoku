package script.board.panes;

import java.io.FileInputStream;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import script.elements.cube.Cube;
import script.elements.cube.CubeStatus;
import script.resources.Puzzle;

public class BoardPane extends GridPane {
    public static final int GRID_SIZE = 9;
    public static final int NEED_GUESS_CUBE_SIZE = 25;
    public static final int BOARD_WIDTH = NEED_GUESS_CUBE_SIZE * GRID_SIZE;
    public static final int BOARD_HEIGHT = NEED_GUESS_CUBE_SIZE * GRID_SIZE;

    private Cube[][] cubes = new Cube[GRID_SIZE][GRID_SIZE];
    private Puzzle puzzle = new Puzzle();

    public BoardPane() {
        super();
        super.setPadding(new Insets(10, 0, 10, 10));
        super.setVgap(5);
        super.setHgap(5);
        super.setAlignment(Pos.CENTER);
        try{
        FileInputStream input = new FileInputStream("sudoku.png");
        Image image = new Image(input);
        BackgroundSize backgroundSize = new BackgroundSize(375.0, 375.0, false, false, false, false);
        BackgroundPosition backgroundPosition = new BackgroundPosition(Side.LEFT, 104.25, false, Side.TOP, 25.0, false);
        BackgroundImage backgroundimage = new BackgroundImage(image, 
                                         BackgroundRepeat.NO_REPEAT, 
                                         BackgroundRepeat.NO_REPEAT, 
                                         backgroundPosition, 
                                            backgroundSize);
        Background background = new Background(backgroundimage);
        super.setBackground(background);
        }catch(Exception e){
        }
        puzzle.setNumbers();
        newGame();
    }

    public boolean valueIsInRow(int row, Cube cube) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (i < cube.getColumn() || i > cube.getColumn()) {
                if (cubes[row][i].getCubeValue() == cube.getCubeValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Cube[][] getCubes() {
        return cubes;
    }

    public boolean valueIsInColumn(int col, Cube cube) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (i < cube.getRow() || i > cube.getRow()) {
                if (cubes[i][col].getCubeValue() == cube.getCubeValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean valueIsInBox(int row, int col, Cube cube) {
        int localBoxRow = row - (row % 3);
        int localBoxColumn = col - (col % 3);

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (cubes[i][j] == cube) {
                } else {
                    if (cubes[i][j].getCubeValue() == cube.getCubeValue()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void newGame() {
        for (int row = 0; row < GRID_SIZE; ++row) {
            for (int col = 0; col < GRID_SIZE; ++col) {
                int cubeValue = puzzle.puzzle[row][col];
                if (cubeValue != 0) {
                    Cube cube = new Cube(cubeValue, row, col);
                    cube.cubeStatus = CubeStatus.GIVEN;
                    cubes[row][col] = cube;
                    cubes[row][col].paint();
                    super.add(cubes[row][col], col, row);
                } else {
                    Cube cube = new Cube(row, col);
                    cube.cubeStatus = CubeStatus.NEED_GUESS;
                    cubes[row][col] = cube;
                    cubes[row][col].paint();
                    super.add(cubes[row][col], col, row);
                }
            }
        }
    }

    public boolean solveBoard() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (cubes[row][column].cubeStatus == CubeStatus.NEED_GUESS) {
                    for (int numToTry = 1; numToTry <= GRID_SIZE; numToTry++) {
                        cubes[row][column].setCubeValue(numToTry);
                        cubes[row][column].cubeStatus = CubeStatus.CORRECT_GUESS;
                        cubes[row][column].paint();
                        if (!valueIsInRow(row, cubes[row][column]) && !valueIsInColumn(column, cubes[row][column])
                                && !valueIsInBox(row, column, cubes[row][column]) && solveBoard()) {
                               return true;
                        }
                        cubes[row][column].setCubeValue(0);
                        cubes[row][column].cubeStatus = CubeStatus.NEED_GUESS;
                        cubes[row][column].paint();
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSolved(){
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (cubes[row][column].cubeStatus == CubeStatus.WRONG_GUESS || cubes[row][column].cubeStatus == CubeStatus.NEED_GUESS) {
                   return false;
                }
            }
        }
        return true;
    }
}
