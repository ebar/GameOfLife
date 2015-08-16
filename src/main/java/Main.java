import game.Cell;
import game.GameOfLife;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main (String[] args) {
        try {
            FileReader fileReader = new FileReader(args[0]);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            int yCoord = 0;
            Set<Cell> cells = new HashSet<Cell>();
            while ((line = reader.readLine()) != null) {
                readChars(line, yCoord, cells);
                yCoord++;
            }
            GameOfLife game = new GameOfLife(cells);
            for (int i=0;i<4;i++) {
                printCells(game.getCells());
                game.nextGeneration();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void printCells(Set<Cell> cells) {
        for (int i=0; i< 5; i++) {
            for (int j =0;j<5;j++) {
                if (cells.contains(new Cell(i,j))) {
                    System.out.print("x");
                } else {
                    System.out.print("-");
                }
            }
            System.out.print("\n");
        }
    }

    private static void readChars(String line, int yCoord, Set<Cell> cells) {
        int xCoord = 0;
        for (char c : line.toCharArray()) {
            if (c == 'x') {
                cells.add(new Cell(xCoord, yCoord));
            }
            xCoord++;
        }
    }
}
