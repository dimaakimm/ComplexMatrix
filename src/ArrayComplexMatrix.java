public class ArrayComplexMatrix {
    private ComplexMatrix[] matrixes;
    public ArrayComplexMatrix(int n) {
        this.matrixes = new ComplexMatrix[n];
    }
    public void printAll(){
        for (int i = 0; i < this.matrixes.length; i++) {
            System.out.println("Матрица под номером " + (i + 1) + ":");
            this.matrixes[i].printMatrix();
        }
    }
    public ComplexMatrix getMatrix(int n){
        return this.matrixes[n-1];
    }
    public void insertMatrix(ComplexMatrix matrix, int index){
        this.matrixes[index] = matrix;
    }
    public ComplexMatrix addMatrix(int first, int second){
        return matrixes[first-1].add(matrixes[second-1]);
    }
    public ComplexMatrix substractMatrix(int first, int second){
        return matrixes[first-1].subtract(matrixes[second-1]);
    }
    public ComplexMatrix multiplyMatrix(int first, int second){
        return matrixes[first-1].multiply(matrixes[second-1]);
    }
    public ComplexMatrix divideMatrix(int first, int second){
        return matrixes[first-1].divide(matrixes[second-1]);
    }
    public ComplexMatrix multiplyByNumberMatrix(int first, ComplexNumber num){
        return matrixes[first-1].multiplyByNumber(num);
    }
    public ComplexMatrix transposeMatrix(int n){
        return matrixes[n-1].transpose();
    }
    public ComplexNumber determinantMatrix(int n){
        return matrixes[n-1].determinant();
    }
    public ComplexMatrix inverseMatrix(int n){
        return matrixes[n-1].inverse();
    }

}
