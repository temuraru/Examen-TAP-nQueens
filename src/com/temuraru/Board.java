package com.temuraru;

public class Board {
    private static final char EMPTY_FIELD = '-';
    private static final char BLOCKED_FIELD = '*';
    private static final char QUEEN_SYMBOL = 'Q';

    private int size;
    private Character[][] tiles;

    public Board(Integer nr) {
        size = nr;
        tiles = new Character[size][size];
        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++) {
                tiles[i][j] = EMPTY_FIELD;
            }
        }
    }

    protected boolean isSafeField(int x, int y) {
        boolean isSafe = true;
        if (rowHasQueen(x) || columnHasQueen(y)) {
            isSafe = false;
        }


        return isSafe;
    }

    public Board solve() {
        for(int i = 0; i < size; i++) {

            while (true) {

            }
        }

        return this;
    }

    protected boolean diagonalsHaveQueen(int x, int y) {
        boolean hasQueen = false;
        // test the top diagonal fields
        int row_top = x, col_top = y, i = 0;
        while ((row_top - i) > 0) {
            i++;
            if (tiles[row_top - i][col_top - i] == QUEEN_SYMBOL || tiles[row_top - i][col_top + i] == QUEEN_SYMBOL){
                hasQueen = true;
                break;
            }
        }

        // test the bottom diagonal fields
        int row_bottom = x, col_bottom = y;
        i = 0;
        while ((row_bottom + i) < size) {
            i++;
            if (tiles[row_bottom + i][col_bottom + i] == QUEEN_SYMBOL || tiles[row_bottom + i][col_bottom - i] == QUEEN_SYMBOL){
                hasQueen = true;
                break;
            }
        }

        return hasQueen;
    }

    protected boolean rowHasQueen(int x) {
        boolean hasQueen = false;
        for (int i = 0; i < size; i++) {
            if (tiles[x][i] == QUEEN_SYMBOL) {
                hasQueen = true;
                break;
            }
        }

        return hasQueen;
    }
    protected boolean columnHasQueen(int y) {
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
    public Board addQueen(int x, int y) {
        blockRow(x);
        blockColumn(y);
        tiles[x][y] = QUEEN_SYMBOL;

        return this;
    }

    public void display() {
        System.out.println();
        System.out.print("=================================== Begin Board " + size + " x " + size);
        for (int i=0; i < size; i++) {
            System.out.println();
            for (int j=0; j < size; j++) {
                System.out.print(tiles[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println("=================================== End Board " + size + " x " + size);
    }

}
