package Models;

// Super Board holds a 2d Board more than Board
public class SBoard extends Board{
    // can hold many boards, and of course piece when a small board game ends
    private Board[][] boards;
    private int insideDim;

    public SBoard(int dim, int idim) {
        super(dim);

        this.boards = new Board[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                boards[i][j] = new Board(idim);
            }
        }

        this.insideDim = idim;
    }

    public Board[][] getBoards() {
        return boards;
    }

    public int getInsideDim() {
        return insideDim;
    }
}
