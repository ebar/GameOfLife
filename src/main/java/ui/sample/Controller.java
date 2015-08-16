package ui.sample;

import game.Cell;
import game.CellState;
import game.GameOfLife;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    public static final int SIZE = 15;
    public static final int GRIDSIZE = 20;

    @FXML
    public Group group;

    private List<Rectangle> rectangles;
    private GameOfLife gameOfLife;
    private Timeline timeline;

    public void startGame(ActionEvent actionEvent) {
        timeline.play();
    }

    public void nextMove() {
        gameOfLife.nextGeneration();
        createRectangles();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rectangles = new ArrayList<Rectangle>();
        Set<Cell> cells = new HashSet<Cell>();
        gameOfLife = new GameOfLife(cells);
        createTimeline();
        createRectangles();
    }

    private void createTimeline() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                nextMove();
            }
        };

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), onFinished));
    }

    private void createRectangles() {
        for (int i =0;i< GRIDSIZE;i++) {
            for (int j=0;j<GRIDSIZE;j++) {
                Rectangle rect = new Rectangle(i * SIZE, j * SIZE, SIZE, SIZE);
                final Cell cell = new Cell(i,j);
                if (gameOfLife.getCellState(new Cell(i,j)).equals(CellState.ALIVE)) {
                    rect.setFill(Color.ALICEBLUE);
                } else {
                    rect.setFill(Color.CADETBLUE);
                }
                rect.setStroke(Color.DARKSLATEGRAY);
                rect.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        gameOfLife.toggle(cell);
                        createRectangles();
                    }
                });
                rectangles.add(rect);

                this.group.getChildren().add(rect);
            }
        }
    }

    public void stopGame(ActionEvent actionEvent) {
        timeline.stop();
    }
}
