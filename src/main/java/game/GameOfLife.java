package game;

import java.util.HashSet;
import java.util.Set;

public class GameOfLife {

    private Set<Cell> cells = new HashSet<Cell>();

    public GameOfLife(Set<Cell> cells) {
        this.cells = cells;
    }

    public void nextGeneration() {
        HashSet<Cell> cellsToAdd = new HashSet<Cell>();
        for (Cell cell : cells) {
            if (getNumberOfNeighbours(cell) == 2 || getNumberOfNeighbours(cell) == 3) {
                cellsToAdd.add(cell);
                for (int i = -1; i<2;i++) {
                    for (int j = -1; j < 2; j++) {
                        Cell neighbouringCell = cell.forOffset(i, j);
                        if (getCellState(neighbouringCell).equals(CellState.DEAD)
                                && getNumberOfNeighbours(neighbouringCell) == 3)
                        {
                            cellsToAdd.add(neighbouringCell);
                        }
                    }

                }
            }
        }
        cells.clear();
        cells.addAll(cellsToAdd);
    }

    private int getNumberOfNeighbours(Cell cell) {
        int neighbours = 0;
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (getCellState(cell.forOffset(i, j)).equals(CellState.ALIVE) && ((i != 0)|| (j!=0))) {
                    neighbours++;
                }
            }
        }
        return neighbours;
    }

    public CellState getCellState(Cell cell) {
        if (cells.contains(cell)) {
            return CellState.ALIVE;
        }
        return CellState.DEAD;
    }

    public void toggle(Cell cell) {
        if (cells.contains(cell)) {
            cells.remove(cell);
        } else {
            cells.add(cell);
        }
    }

    public Set<Cell> getCells() {
        return cells;
    }
}
