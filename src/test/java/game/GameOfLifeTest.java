package game;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameOfLifeTest {

    @Test
    public void shouldInitialiseBoard()
    {
        Set<Cell> cells = createCells();
        GameOfLife gameOfLife = new GameOfLife(cells);
        assertThat(gameOfLife.getCellState(new Cell(0, 0)), is(CellState.DEAD));
    }

    private Set<Cell> createCells() {
        return new HashSet<Cell>();
    }

    @Test
    public void shouldInitialiseBoardWithOriginCell()
    {
        Set<Cell> cells = createCells();
        cells.add(new Cell(0,0));
        GameOfLife gameOfLife = new GameOfLife(cells);
        assertThat(gameOfLife.getCellState(new Cell(0, 0)), is(CellState.ALIVE));
    }

    @Test
    public void shouldInitialiseWithOriginCellAndAllOtherCellsShouldBeDead()
    {
        Set<Cell> cells = createCells();
        cells.add(new Cell(0,0));
        GameOfLife gameOfLife = new GameOfLife(cells);
        assertThat(gameOfLife.getCellState(new Cell(0, 1)), is(CellState.DEAD));
    }

    @Test
    public void shouldInitialiseWithTwoCells()
    {
        Set<Cell> cells = createCells();

        cells.add(new Cell(0,0));
        cells.add(new Cell(0,1));
        GameOfLife gameOfLife = new GameOfLife(cells);

        assertThat(gameOfLife.getCellState(new Cell(0, 0)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(0, 1)), is(CellState.ALIVE));
    }

    @Test
    public void liveCellOnItsOwnShouldDieAfterNextGeneration()
    {
        Set<Cell> cells = createCells();

        cells.add(new Cell(0,0));

        GameOfLife gameOfLife = new GameOfLife(cells);

        assertThat(gameOfLife.getCellState(new Cell(0, 0)), is(CellState.ALIVE));
        gameOfLife.nextGeneration();

        assertThat(gameOfLife.getCellState(new Cell(0, 0)), is(CellState.DEAD));
    }

    @Test
    public void cellWithTwoNeighboursShouldSurviveToNextGeneration()
    {
        Set<Cell> cells = createCells();

        cells.add(new Cell(0,0));
        cells.add(new Cell(0,1));
        cells.add(new Cell(0,2));

        GameOfLife gameOfLife = new GameOfLife(cells);

        assertThat(gameOfLife.getCellState(new Cell(0, 0)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(0, 1)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(0, 2)), is(CellState.ALIVE));
        gameOfLife.nextGeneration();

        assertThat(gameOfLife.getCellState(new Cell(0, 0)), is(CellState.DEAD));
        assertThat(gameOfLife.getCellState(new Cell(0, 1)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(0, 2)), is(CellState.DEAD));

    }


    @Test
    public void cellWithTwoNeighboursShouldSurviveToNextGenerationVertical()
    {
        Set<Cell> cells = createCells();

        cells.add(new Cell(0,0));
        cells.add(new Cell(1,0));
        cells.add(new Cell(2,0));

        GameOfLife gameOfLife = new GameOfLife(cells);

        assertThat(gameOfLife.getCellState(new Cell(0, 0)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(1, 0)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(2, 0)), is(CellState.ALIVE));
        gameOfLife.nextGeneration();

        assertThat(gameOfLife.getCellState(new Cell(0, 0)), is(CellState.DEAD));
        assertThat(gameOfLife.getCellState(new Cell(1, 0)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(2, 0)), is(CellState.DEAD));

    }

    @Test
    public void cellWithThreeNeighboursShouldSurviveToNextGeneration()
    {
        Set<Cell> cells = createCells();

        cells.add(new Cell(0,0));
        cells.add(new Cell(1,1));
        cells.add(new Cell(1,0));
        cells.add(new Cell(2,0));

        GameOfLife gameOfLife = new GameOfLife(cells);

        gameOfLife.nextGeneration();

        assertThat(gameOfLife.getCellState(new Cell(0, 0)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(1, 0)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(1, 1)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(2, 0)), is(CellState.ALIVE));

    }

    @Test
    public void cellWithFourNeighboursShouldDieByOvercrowding()
    {
        Set<Cell> cells = createCells();

        cells.add(new Cell(0,0));
        cells.add(new Cell(1,1));
        cells.add(new Cell(1,0));
        cells.add(new Cell(2,0));
        cells.add(new Cell(2,1));

        GameOfLife gameOfLife = new GameOfLife(cells);

        gameOfLife.nextGeneration();

        assertThat(gameOfLife.getCellState(new Cell(0, 0)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(1, 0)), is(CellState.DEAD));
        assertThat(gameOfLife.getCellState(new Cell(1, 1)), is(CellState.DEAD));
        assertThat(gameOfLife.getCellState(new Cell(2, 0)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(2, 1)), is(CellState.ALIVE));

    }

    @Test
    public void cellWithFourOrMoreNeighboursShouldDieByOvercrowding()
    {
        Set<Cell> cells = createCells();

        cells.add(new Cell(0,0));
        cells.add(new Cell(0,1));
        cells.add(new Cell(0,2));
        cells.add(new Cell(1,0));
        cells.add(new Cell(1,1));
        cells.add(new Cell(1,2));
        cells.add(new Cell(2,0));
        cells.add(new Cell(2,1));
        cells.add(new Cell(2,2));

        GameOfLife gameOfLife = new GameOfLife(cells);

        gameOfLife.nextGeneration();

        assertThat(gameOfLife.getCellState(new Cell(0, 0)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(0, 1)), is(CellState.DEAD));
        assertThat(gameOfLife.getCellState(new Cell(0, 2)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(1, 0)), is(CellState.DEAD));
        assertThat(gameOfLife.getCellState(new Cell(1, 1)), is(CellState.DEAD));
        assertThat(gameOfLife.getCellState(new Cell(1, 2)), is(CellState.DEAD));
        assertThat(gameOfLife.getCellState(new Cell(2, 0)), is(CellState.ALIVE));
        assertThat(gameOfLife.getCellState(new Cell(2, 1)), is(CellState.DEAD));
        assertThat(gameOfLife.getCellState(new Cell(2, 2)), is(CellState.ALIVE));

    }

    @Test
    public void deadCellShouldBecomeAliveWhenSurroundedByThreeLiveCells()
    {
        Set<Cell> cells = createCells();
        cells.add(new Cell(0,0));
        cells.add(new Cell(1,0));
        cells.add(new Cell(2,0));

        GameOfLife gameOfLife = new GameOfLife(cells);

        gameOfLife.nextGeneration();

        assertThat(gameOfLife.getCellState(new Cell(1, 1)), is(CellState.ALIVE));

    }

}
