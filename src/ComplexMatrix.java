public class ComplexMatrix {
    private int rows, cols;
    private ComplexNumber[][] matrix;
    public ComplexMatrix(int n, int m) {
        this.rows = n;
        this.cols = m;
        this.matrix = new ComplexNumber[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = new ComplexNumber(0, 0);
            }
        }
    }
    public ComplexMatrix(ComplexNumber[][] matrix) {
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.matrix = matrix;
    }

    public ComplexMatrix add(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Нельзя складывать матрицы с разными размерностями!");
        }
        ComplexMatrix result = new ComplexMatrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.matrix[i][j] = this.matrix[i][j].add(other.matrix[i][j]);
            }
        }
        return result;
    }
    public ComplexMatrix subtract(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Нельзя вычитать друг из друга матрицы с разными размерностями");
        }
        ComplexMatrix result = new ComplexMatrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.matrix[i][j] = this.matrix[i][j].subtract(other.matrix[i][j]);
            }
        }
        return result;
    }
    public ComplexMatrix multiply(ComplexMatrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Кол-во столбцов у первой матрицы не равняется кол-ву строк у второй!");
        }
        ComplexMatrix result = new ComplexMatrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                ComplexNumber res = new ComplexNumber(0, 0);
                for (int k = 0; k < this.cols; k++) {
                    res = res.add(this.matrix[i][k].multiply(other.matrix[k][j]));
                }
                result.matrix[i][j] = res;
            }
        }
        return result;
    }
    public ComplexMatrix multiplyByNumber(ComplexNumber num) {
        ComplexMatrix result = new ComplexMatrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.matrix[i][j] = this.matrix[i][j].multiply(num);
            }
        }
        return result;
    }
    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(this.cols, this.rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.matrix[j][i] = this.matrix[i][j];
            }
        }
        return result;
    }
    public ComplexNumber determinant() {
        if (this.rows != this.cols) {
            throw new IllegalArgumentException("Детерминант определяется только у квадратной матрицы");
        }
        return calculateDeterminant(this.matrix);
    }
    private ComplexNumber calculateDeterminant(ComplexNumber[][] matrix) {
        int size = matrix.length;
        if (size == 1) {
            return matrix[0][0];
        }
        if (size == 2) {
            return matrix[0][0].multiply(matrix[1][1]).subtract(matrix[0][1].multiply(matrix[1][0]));
        }
        ComplexNumber det = new ComplexNumber(0, 0);
        for (int i = 0; i < size; i++) {
            det = det.add(matrix[0][i].multiply(cofactor(matrix, 0, i)));
        }
        return det;
    }
    private ComplexNumber cofactor(ComplexNumber[][] matrix, int i, int j) {
        ComplexNumber[][] subMatrix = new ComplexNumber[matrix.length - 1][matrix[0].length - 1];
        int subRow = -1;
        for (int k = 0; k < matrix.length; k++) {
            if (k == i) continue;
            subRow++;
            int subCol = 0;
            for (int l = 0; l < matrix[0].length; l++) {
                if (j == l) continue;
                subMatrix[subRow][subCol++] = matrix[k][l];
            }
        }
        ComplexNumber cofactor = calculateDeterminant(subMatrix);
        return ((i + j) % 2 == 0) ? cofactor : cofactor.multiply(new ComplexNumber(-1, 0));
    }
    public ComplexMatrix divide(ComplexMatrix matrix) {
        return this.multiply(matrix.inverse());
    }
    public ComplexMatrix inverse() {
        if (this.rows != this.cols) {
            throw new IllegalArgumentException("Матрицы должна быть квадратной");
        }
        ComplexNumber determinant = calculateDeterminant(this.matrix);
        if (determinant.equals(new ComplexNumber(0, 0))) {
            throw new ArithmeticException("Детерминант равен нулю");
        }

        ComplexMatrix adjointMatrix = new ComplexMatrix(this.cols, this.rows);

        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                ComplexNumber cofactorTransposed = new ComplexNumber(0, 0);
                adjointMatrix.matrix[i][j] = cofactor(this.matrix, i, j);
            }
        }
        return adjointMatrix.transpose().multiplyByNumber(new ComplexNumber(1, 0).divide(determinant));
    }
    public void printMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}