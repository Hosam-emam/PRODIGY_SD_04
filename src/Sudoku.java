public class Sudoku {
    private static final int BOARD_SIZE = 9;
    private static final int SUBGRID_SIZE = 3;
    private static final int EMPTY_CELL = 0;

    public static void main(String[] args) {

                // HERE YOU HAVE 3 GRID EXAMPLES

//        int[][] board = {
//                {5, 3, 0, 0, 7, 0, 0, 0, 0},
//                {6, 0, 0, 1, 9, 5, 0, 0, 0},
//                {0, 9, 8, 0, 0, 0, 0, 6, 0},
//                {8, 0, 0, 0, 6, 0, 0, 0, 3},
//                {4, 0, 0, 8, 0, 3, 0, 0, 1},
//                {7, 0, 0, 0, 2, 0, 0, 0, 6},
//                {0, 6, 0, 0, 0, 0, 2, 8, 0},
//                {0, 0, 0, 4, 1, 9, 0, 0, 5},
//                {0, 0, 0, 0, 8, 0, 0, 7, 9}
//        };
//        int[][] board = {
//                {0, 2, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 6, 0, 0, 0, 0, 3},
//                {0, 7, 4, 0, 8, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 3, 0, 0, 2},
//                {0, 8, 0, 0, 4, 0, 0, 1, 0},
//                {6, 0, 0, 5, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 1, 0, 7, 8, 0},
//                {5, 0, 0, 0, 0, 9, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 4, 0}
//        };
        int[][] board = {
                {0, 9, 0, 7, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 6, 4, 0, 0},
                {0, 0, 0, 0, 9, 0, 0, 0, 0},
                {0, 0, 0, 8, 0, 0, 2, 0, 0},
                {0, 0, 9, 0, 0, 0, 0, 7, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
                {7, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 0, 0},
                {0, 6, 0, 0, 0, 0, 0, 5, 9}
        };

        solveSudoku(board);

        System.out.println("The Solution:");
        showGrid(board);
    }

    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    for (int num = 1; num <= BOARD_SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

                            board[row][col] = EMPTY_CELL;
                        }
                    }

                    return false; // No valid solution
                }
            }
        }

        return true; // Board is filled completely
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        for (int d = 0; d < BOARD_SIZE; d++) {
            if (board[row][d] == num) {
                return false;
            }
        }

        for (int r = 0; r < BOARD_SIZE; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        int subgridStartRow = row - row % SUBGRID_SIZE;
        int subgridStartCol = col - col % SUBGRID_SIZE;
        for (int r = subgridStartRow; r < subgridStartRow + SUBGRID_SIZE; r++) {
            for (int d = subgridStartCol; d < subgridStartCol + SUBGRID_SIZE; d++) {
                if (board[r][d] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void showGrid(int[][] board){
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                System.out.print(board[row][col]+ " ");
                if((col+1)%3 == 0){
                    System.out.print("| ");
                }
            }

            if((row+1)%3 == 0){
                System.out.println();
                System.out.print("-----------------------");
            }
            System.out.println();
        }
    }
}