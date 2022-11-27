import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import script.board.InteractingBoard;

public class SudokuGame extends Application{
    InteractingBoard interactingBoard = new InteractingBoard();
    
    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("Sudoku Game");
        
        stage.setScene(new Scene(interactingBoard, 575, 600));

        stage.setResizable(false);
  
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}