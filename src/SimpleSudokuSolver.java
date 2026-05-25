public class SimpleSudokuSolver {

    static void print(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j]);
                if (j < 8) System.out.print(" ");
            }
            System.out.println();
        }
    }

    static boolean canPlace(int[][] grid, int row, int col, int num) {

        // Check row and column
        for (int x = 0; x < 9; x++) {
            if (grid[row][x] == num || grid[x][col] == num)
                return false;
        }

        // Check 3x3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[boxRow + i][boxCol + j] == num)
                    return false;
            }
        }

        return true;
    }

    static boolean solve(int[][] grid) {

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                // Empty cell found
                if (grid[row][col] == -1) {

                    // Try numbers 1 to 9
                    for (int num = 1; num <= 9; num++) {

                        if (canPlace(grid, row, col, num)) {

                            grid[row][col] = num;

                            // Recursive call
                            if (solve(grid))
                                return true;

                            // Backtrack
                            grid[row][col] = -1;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int[][] puzzle = {
                {-1, 7, -1, -1, -1, -1, -1, -1, 9},
                {5, 1, 4, 2, -1, 6, -1, -1, -1},
                {-1, 8, 3, -1, -1, 7, -1, -1, -1},
                {-1, -1, -1, 8, 1, 3, 7, -1, -1},
                {-1, 2, 3, -1, 8, -1, -1, -1, 4},
                {4, -1, -1, 9, -1, -1, 1, -1, -1},
                {9, 6, 2, 8, -1, -1, -1, 3, -1},
                {-1, -1, -1, -1, -1, 1, -1, 4, -1},
                {7, -1, -1, 2, 3, -1, 9, 6, -1}
        };

        System.out.println("Unsolved:");
        print(puzzle);

        if (solve(puzzle)) {

            System.out.println("\nSolved:");
            print(puzzle);

        } else {

            System.out.println("No solution exists.");
        }
    }
}