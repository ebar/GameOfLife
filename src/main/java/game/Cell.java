package game;

/**
 * Created by Emily on 15/12/13.
 */
public class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CellState coordinatesEqual(int x, int y) {
        if (this.x == x && this.y == y)
        {
            return CellState.ALIVE;
        }
        return CellState.DEAD;
    }

    @Override
    public boolean equals(Object another)
    {
        if (another instanceof Cell)
        {
            Cell anotherCell = (Cell) another;
            if (this.x == anotherCell.x && this.y == anotherCell.y)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return 100000 * x + y;
    }

    public Cell forOffset(int i, int j) {

        return new Cell(x + i, y+j);
    }
}
