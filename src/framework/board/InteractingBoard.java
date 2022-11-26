package framework.board;

import framework.board.panes.BoardPane;
import framework.board.panes.MenuPane;
import framework.board.panes.OptionsPane;
import framework.elements.cube.Cube;
import framework.elements.cube.CubeStatus;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InteractingBoard extends BorderPane {
    private BoardPane boardCubes = new BoardPane();
    private OptionsPane optionsCubes = new OptionsPane();
    private MenuPane menuPane = new MenuPane();
    public int numberOnHold;

    public InteractingBoard() {
        super();
        boardCubes.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    setBoardCubeToOptionCubeValue(arg0);
                }

            });
        });
        super.setCenter(boardCubes);

        optionsCubes.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent arg0) {
                    setNumberOnHold(arg0);
                }
            });
        });

        menuPane.getChildren().get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                newGame();
            }

        });

        menuPane.getChildren().get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                solve();
            }

        });

        menuPane.getChildren().get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                if(isSolved()){
                final Stage popUp = new Stage();
                popUp.initModality(Modality.APPLICATION_MODAL);
                VBox popUpVbox = new VBox(20);
                Text solved = new Text("You Solved the Sudoku!!");
                solved.setFont(Font.font ("Serif", 20));
                popUpVbox.getChildren().add(solved);
                popUpVbox.setAlignment(Pos.CENTER);
                Scene dialogScene = new Scene(popUpVbox, 300, 200);
                popUp.setResizable(false);
                popUp.setScene(dialogScene);
                popUp.show();
                }else{
                    final Stage popUp = new Stage();
                    popUp.initModality(Modality.APPLICATION_MODAL);
                    VBox popUpVbox = new VBox(20);
                    Text notSolved = new Text("You Have Not Solved the Sudoku");
                    notSolved.setFont(Font.font ("Serif", 20));
                    popUpVbox.getChildren().add(notSolved);
                    popUpVbox.setAlignment(Pos.CENTER);
                    Scene dialogScene = new Scene(popUpVbox, 300, 200);
                    popUp.setResizable(false);
                    popUp.setScene(dialogScene);
                    popUp.show();
                }

            }

        });


        super.setBottom(optionsCubes);
        super.setTop(menuPane);
        super.autosize();
    }

    public void setBoardCubeToOptionCubeValue(MouseEvent event) {
        if (((Cube) event.getSource()).cubeStatus == CubeStatus.GIVEN) {
            System.out.println("Can't place option here!");
        } else if (((Cube) event.getSource()).cubeStatus == CubeStatus.WRONG_GUESS) {
            Cube cube = ((Cube) event.getSource());
            cube.cubeStatus = CubeStatus.NEED_GUESS;
            cube.paint();
        } else if (((Cube) event.getSource()).cubeStatus == CubeStatus.CORRECT_GUESS) {
            Cube cube = ((Cube) event.getSource());
            cube.cubeStatus = CubeStatus.NEED_GUESS;
            cube.paint();
        } else if (((Cube) event.getSource()).cubeStatus == CubeStatus.NEED_GUESS) {
            Cube cube = ((Cube) event.getSource());
            cube.setCubeValue(numberOnHold);
            if (boardCubes.valueIsInRow(cube.getRow(), cube) || boardCubes.valueIsInColumn(cube.getColumn(), cube)
                    || boardCubes.valueIsInBox(cube.getRow(), cube.getColumn(), cube)) {
                cube.cubeStatus = CubeStatus.WRONG_GUESS;
                cube.paint();
            } else {
                cube.cubeStatus = CubeStatus.CORRECT_GUESS;
                cube.paint();
            }
        }
    }

    public void setNumberOnHold(MouseEvent event) {
        numberOnHold = ((Cube) event.getSource()).getCubeValue();
    }

    public void newGame() {
        boardCubes.newGame();
        menuPane.reset();
        boardCubes.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    setBoardCubeToOptionCubeValue(arg0);
                }

            });
        });
    }

    public void solve() {
        boardCubes.solveBoard();
    }

    public boolean isSolved(){
        return boardCubes.isSolved();
    }
}