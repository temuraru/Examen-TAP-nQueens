package com.temuraru;

public class Board {
    private static final char BLOCKED_FIELD = 'x';
    private static final char QUEEN_SYMBOL = 'Q';

    private int size;
    private Character[][] tiles;

    public Board(Integer nr) {
        size = nr;
        tiles = new Character[size][size];
        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++) {
                tiles[i][j] = '-';
            }
        }
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

    public Board addQueen(int x, int y) {
        blockRow(x);
        blockColumn(y);
        tiles[x][y] = QUEEN_SYMBOL;

        return this;
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
}
