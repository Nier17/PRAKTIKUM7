import java.util.Arrays;

// Exception kustom untuk matrix tidak valid
class InvalidMatrixException extends Exception {
    public InvalidMatrixException(String message) {
        super(message);
    }
}

public class MatrixValidator {
    private final int[][] matrix;
    private final int rows;
    private final int cols;

    // Constructor yang bisa melempar InvalidMatrixException
    public MatrixValidator(int[][] matrix) throws InvalidMatrixException {
        if (matrix == null) {
            throw new InvalidMatrixException("Matrix tidak boleh null");
        }
        
        this.rows = matrix.length;
        if (rows == 0) {
            throw new InvalidMatrixException("Matrix tidak boleh kosong");
        }
        
        this.cols = matrix[0].length;
        for (int[] row : matrix) {
            if (row.length != cols) {
                throw new InvalidMatrixException("Semua baris matrix harus memiliki panjang yang sama");
            }
        }
        
        this.matrix = matrix;
    }

    // Method untuk validasi matrix persegi
    public boolean isSquareMatrix() {
        return rows == cols;
    }

    // Method untuk validasi matrix diagonal
    public boolean isDiagonalMatrix() throws InvalidMatrixException {
        if (!isSquareMatrix()) {
            throw new InvalidMatrixException("Matrix harus persegi untuk dicek sebagai diagonal");
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i != j && matrix[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // Method untuk mencetak matrix
    public void printMatrix() {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        // Test case 1: Matrix valid
        int[][] validMatrix = {
            {1, 0, 0},
            {0, 2, 0},
            {0, 0, 3}
        };

        // Test case 2: Matrix tidak persegi
        int[][] nonSquareMatrix = {
            {1, 2, 3},
            {4, 5, 6}
        };

        // Test case 3: Matrix null
        int[][] nullMatrix = null;

        try {
            System.out.println("Testing valid matrix:");
            MatrixValidator validator1 = new MatrixValidator(validMatrix);
            validator1.printMatrix();
            System.out.println("Is square: " + validator1.isSquareMatrix());
            System.out.println("Is diagonal: " + validator1.isDiagonalMatrix());

            System.out.println("\nTesting non-square matrix:");
            MatrixValidator validator2 = new MatrixValidator(nonSquareMatrix);
            validator2.printMatrix();
            System.out.println("Is square: " + validator2.isSquareMatrix());
            // Baris berikut akan melempar exception
            System.out.println("Is diagonal: " + validator2.isDiagonalMatrix());
        } catch (InvalidMatrixException e) {
            System.err.println("Matrix Error: " + e.getMessage());
        }

        try {
            System.out.println("\nTesting null matrix:");
            MatrixValidator validator3 = new MatrixValidator(nullMatrix);
        } catch (InvalidMatrixException e) {
            System.err.println("Matrix Error: " + e.getMessage());
        }
    }
}