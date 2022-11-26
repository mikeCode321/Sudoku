package framework.elements.timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Timer extends Label {
    private static final Integer STARTTIME = 0;
    private Timeline timeline;
    private Integer timeSeconds = STARTTIME;

    public Timer() {
        super();
        super.setText("Time: " + timeSeconds.toString());
        super.setTextFill(Color.BLACK);
        super.setFont(Font.font ("Serif", 25));
        super.setAlignment(Pos.CENTER);
        super.setPadding(new Insets(5,10,10,5));
        updateTime();
    }

    public void updateTime() {
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds = STARTTIME;

        super.setText(timeSeconds.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                timeSeconds++;
                Timer.super.setText("Time: " + timeSeconds.toString());
                if (timeSeconds <= 0) {
                    timeline.stop();
                }
            }
        }));
        timeline.playFromStart();
    }

    public void resetTimer(){
        timeline.stop();
        timeSeconds = STARTTIME;
        updateTime();
    }

}