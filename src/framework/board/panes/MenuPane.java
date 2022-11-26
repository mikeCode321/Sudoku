package framework.board.panes;

import framework.elements.timer.Timer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MenuPane extends GridPane{
    Timer timer = new Timer();
    Button newGame = new Button();
    Button solveBoard = new Button();
    Button checkBoard = new Button();

    public MenuPane(){
        super();
        super.setPrefWidth(10);
        super.setPadding(new Insets(10,10,10,0));
        super.setHgap(5.0);
        super.setVgap(5.0);

        newGame.setText("New Game");
        newGame.setPrefWidth(80.0);
        newGame.setStyle("-fx-background-color: #d3d3d3;");

        solveBoard.setText("Solve");
        solveBoard.setPrefWidth(80.0);
        solveBoard.setStyle("-fx-background-color: #d3d3d3;");

        checkBoard.setText("Check");
        checkBoard.setPrefWidth(80.0);
        checkBoard.setStyle("-fx-background-color: #d3d3d3;");
        
        super.add(timer,2,2,3,1);
        super.add(newGame,1,1);
        super.add(solveBoard,2,1);
        super.add(checkBoard, 3,1);
        super.setAlignment(Pos.CENTER);
    }

    public void reset(){
        timer.resetTimer();
    }
}
