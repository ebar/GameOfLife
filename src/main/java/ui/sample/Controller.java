package ui.sample;

import game.Cell;
import game.CellState;
import game.GameOfLife;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    public static final int SIZE = 20;
    @FXML
    public Group group;

    private List<Rectangle> rectangles;
    private GameOfLife gameOfLife;

    public void startGame(ActionEvent actionEvent) {
        for (int i=0;i< 2;i++) {
            gameOfLife.nextGeneration();
            createRectangles();
        }
    }

    public void nextMove(ActionEvent actionEvent) {
            gameOfLife.nextGeneration();
            createRectangles();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rectangles = new ArrayList<Rectangle>();
        Set<Cell> cells = new HashSet<Cell>();
        gameOfLife = new GameOfLife(cells);
        createRectangles();
    }

    private void createRectangles() {
        for (int i =0;i< 10;i++) {
            for (int j=0;j<10;j++) {
                Rectangle rect = new Rectangle(i * SIZE, j * SIZE, SIZE, SIZE);
                final Cell cell = new Cell(i,j);
                if (gameOfLife.getCellState(new Cell(i,j)).equals(CellState.ALIVE)) {
                    rect.setFill(Color.WHITE);
                } else {
                    rect.setFill(Color.BLUE);
                }
                rect.setStroke(Color.BLACK);
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
}
