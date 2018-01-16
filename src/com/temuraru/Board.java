package com.temuraru;

import java.util.*;

public class Board {
    private static final char EMPTY_FIELD = '-';
    private static final char BLOCKED_FIELD = '*';
    private static final char QUEEN_SYMBOL = 'Q';

    private int size;
    private Character[][] tiles;

    Board(Integer nr) {
        size = nr;
        tiles = new Character[size][size];
        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++) {
                tiles[i][j] = EMPTY_FIELD;
            }
        }
    }

    /**
     * Validate an (x, y) position - that there is no queen intersecting it
     * @param x int
     * @param y int
     * @return boolean
     */
    private boolean isSafeField(int x, int y) {
        if (tiles[x][y] == QUEEN_SYMBOL) {
            return false;
        }
        if (rowHasQueen(x) || columnHasQueen(y)) {
            return false;
        }
        if (diagonalsHaveQueen(x, y)) {
            return false;
        }

        return true;
    }
    private List<Integer> getValidPositionsOnRow(int row) {

        List<Integer> validPositions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (tiles[row][i] == EMPTY_FIELD) {
                validPositions.add(i);
            }
        }

        return validPositions;
    }
    private int getValidRandomColumnOnRow(List<Integer> validPositions, Random rand) {
        int randomColumn = -1;
        int validPositionsCount = validPositions.size();
        if (validPositionsCount > 0) {
            int randomIndex = (validPositionsCount > 1 ? rand.nextInt(validPositionsCount - 1) : 0);
            randomColumn = validPositions.get(randomIndex);
        }

        return randomColumn;
    }
    /**
     * Try to find a solution for the current board
     * @param retryQueenOnSameRow boolean
     * @return boolean
     */
    public boolean solve(boolean retryQueenOnSameRow) {
        Random rand = new Random();
        int validQueensFound = 0;
        for(int row = 0; row < size; row++) {
            int randomColumn = -1;
            boolean queenPositionFound = false;

            // get the list of valid positions on the current row
            List<Integer> validPositions = getValidPositionsOnRow(row);
//            System.out.println("validPositions for row " + row + ": " + validPositions.toString());

            // 2 alternatives: either choose one (x, y) position - random and valid,
            // or try to choose one for size failures
            int limit = this.size - 1;
            if (retryQueenOnSameRow) {
                limit = 0;
            }

            while (limit ++ < this.size) {
                // choose a random column from the available valid positions on the current row
                randomColumn = getValidRandomColumnOnRow(validPositions, rand);
//                System.out.println("random validPosition for row " + row + ": " + randomColumn);
                if (randomColumn < 0) {
                    System.out.println("No valid position found on row: " + row);
                    break;
                }
//                System.out.println("Testing field: " + row + " x " + randomColumn);
                if (isSafeField(row, randomColumn)) {
                    queenPositionFound = true;
                    break;
                }
            }
            if (queenPositionFound) {
                addQueen(row, randomColumn);
                validQueensFound++;
            } else {
                break;
            }
        }

        return (validQueensFound == size);
    }

    /**
     * Check if there are other queens on the diagonals intersecting the (x, y) position
     * @param x int
     * @param y int
     * @return boolean
     */
    private boolean diagonalsHaveQueen(int x, int y) {
        boolean hasQueen = false;
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            if (rowIndex == x) {
                // skip testing the same row as the validating field
                continue;
            }
            // cellSpacing = the distance between the validating field's column and the current row's first and second positions
            int cellSpacing = x - rowIndex;
            int firstPositionY = (rowIndex < x ? y - cellSpacing : y + cellSpacing);
            int secondPositionY = (rowIndex > x ? y - cellSpacing : y + cellSpacing);
//            System.out.println("Verify positions: " + rowIndex + " x " + firstPositionY + ", " + rowIndex + " x " + secondPositionY);

            // only test for queen if the first position is within the range [0 <-> size-1]
            if ((firstPositionY >= 0) && (firstPositionY < size) && tiles[rowIndex][firstPositionY] == QUEEN_SYMBOL) {
                hasQueen = true;
                break;
            }
            // only test for queen if the second position is within the range [0 <-> size-1]
            if ((secondPositionY >= 0) && (secondPositionY < size) && tiles[rowIndex][secondPositionY] == QUEEN_SYMBOL) {
                hasQueen = true;
                break;
            }
        }

        return hasQueen;
    }

    private boolean rowHasQueen(int x) {
        boolean hasQueen = false;
        for (int i = 0; i < size; i++) {
            if (tiles[x][i] == QUEEN_SYMBOL) {
                hasQueen = true;
                break;
            }
        }

        return hasQueen;
    }
    private boolean columnHasQueen(int y) {
        boolean hasQueen = false;
        for (int i = 0; i < size; i++) {
            if (tiles[i][y] == QUEEN_SYMBOL) {
                hasQueen = true;
                break;
            }
        }

        return hasQueen;
    }

    private Board blockRow(int x) {
        for (int i = 0; i < size; i++) {
            tiles[x][i] = BLOCKED_FIELD;
        }
        return this;
    }
    private Board blockColumn(int y) {
        for (int i = 0; i < size; i++) {
            tiles[i][y] = BLOCKED_FIELD;
        }
        return this;
    }
    private Board addQueen(int x, int y) {
        blockRow(x);
        blockColumn(y);
        // blockDiagonals(y);
        tiles[x][y] = QUEEN_SYMBOL;

        return this;
    }

    public void display() {
        System.out.println();
        System.out.print("=================================== Begin Board");
        for (int i=0; i < size; i++) {
            System.out.println();
            for (int j=0; j < size; j++) {
                System.out.print(tiles[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println("=================================== End Board");
    }

    public void displayFailure(boolean displayBoard) {
        if (displayBoard) {
            display();
        }
        System.out.println("=================================== Failed Board " + size + " x " + size + "; Restart!");
    }
}
