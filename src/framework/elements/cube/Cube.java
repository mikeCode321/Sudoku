package framework.elements.cube;

import javafx.scene.control.Button;

public class Cube extends Button{
    private static final String CORRECT_GUESS = "-fx-border-color: #00ff00; -fx-background-color: #778899;";
    private static final String WRONG_GUESS = "-fx-background-color: #f08080";
    private static final String NEED_GUESS = "-fx-background-color: #d3d3d3";
    private static final String GIVEN = "-fx-background-color: #778899";

    private int row;
    private int column; 
    private int cubeValue;
    private String cubeName;
    public CubeStatus cubeStatus;

    public Cube(int row, int column){
        super("");
        this.row = row;
        this.column = column;
        super.setPrefSize(35,35);
    }

    public Cube(int cubeValue, int row, int column){
        super();
        this.cubeValue = cubeValue;
        this.row = row;
        this.column = column;
        super.setText(String.valueOf(cubeValue));
        super.setPrefSize(35, 35);
        
    }

    public Cube(String cubeName, int row, int column){
        super();
        this.cubeName = cubeName;
        this.row = row;
        this.column = column;
        super.setText(cubeName);
    }

    public void newGame(boolean isGiven) {
        cubeStatus = isGiven ? CubeStatus.GIVEN : CubeStatus.NEED_GUESS;
        paint();
     }

     public int getCubeValue(){
        return cubeValue;
     }

     public int getRow(){
        return row;
     }

     public int getColumn(){
        return column;
     }

     public String getCubeName(){
        return cubeName;
     }

     public void setCubeValue(int cubeValue){
        this.cubeValue = cubeValue;
     }
  
     public void paint() {
        if (cubeStatus == CubeStatus.GIVEN) {
           super.setText(cubeValue + "");
           super.setStyle(GIVEN);
        } else if (cubeStatus == CubeStatus.NEED_GUESS) {
           super.setText("");
           this.cubeValue = 0;
           super.setStyle(NEED_GUESS);
        } else if (cubeStatus == CubeStatus.CORRECT_GUESS) { 
           super.setText("" + cubeValue); 
           super.setStyle(CORRECT_GUESS);
        } else if (cubeStatus == CubeStatus.WRONG_GUESS) {  
           super.setText("" + cubeValue);
           super.setStyle(WRONG_GUESS);
        }
     }
}