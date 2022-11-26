package framework.board.panes;

import framework.elements.cube.Cube;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class OptionsPane extends HBox {
    private static final String optionColor = "-fx-border-color: #000000; -fx-border-width: 2; -fx-background-color: #778899";
    public Cube[] optionsCubes = new Cube[10];


    public OptionsPane(){
        super();
        setOptionsCubes();
        super.setAlignment(Pos.CENTER);
        super.setPadding(new Insets(10, 10, 30, 10));
    }

    public void setOptionsCubes(){
        for(int i = 1; i < optionsCubes.length; i ++){
            Cube cube = new Cube(i, 0, i);
            cube.setStyle(optionColor);
            cube.setPrefSize(35, 35);
            optionsCubes[i] = cube;
            super.getChildren().add(optionsCubes[i]);
            super.setSpacing(5);
        }
    }
}
