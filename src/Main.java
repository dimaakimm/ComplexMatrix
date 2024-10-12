import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите количество ваших матриц: ");
            int numberOfMatrix = scanner.nextInt();
            if (numberOfMatrix <= 0) {
                throw new IllegalArgumentException("Кол-во матриц должно быть числом больше нуля");
            }
            ArrayComplexMatrix array = new ArrayComplexMatrix(numberOfMatrix);
            for (int i = 0; i < numberOfMatrix; i++) {
                System.out.println("Введите количество строк и столбцов матрицы " + (i + 1) + ": ");
                int rows = scanner.nextInt();
                int columns = scanner.nextInt();
                ComplexNumber[][] matrix = new ComplexNumber[rows][columns];
                for (int j = 0; j < rows; j++) {
                    for (int k = 0; k < columns; k++) {
                        System.out.println("Введите ячейку матрицы " + (i + 1) + " под индексом [" + j + "][" + k + "]: ");
                        double real = scanner.nextDouble();
                        double imaginary = scanner.nextDouble();
                        ComplexNumber complexNumber = new ComplexNumber(real, imaginary);
                        matrix[j][k] = complexNumber;
                    }
                }
                ComplexMatrix superMatrix = new ComplexMatrix(matrix);
                array.insertMatrix(superMatrix, i);
            }
            int action = 0;
            do {
                array.printAll();
                System.out.print(
                        "Введите действие, которое хотите произвести\n" +
                                "Сложение: 1\n" +
                                "Вычитание: 2\n" +
                                "Умножение матрицы на число: 3\n" +
                                "Умножение матриц: 4\n" +
                                "Транспонирование: 5\n" +
                                "Детерминант: 6\n" +
                                "Обратная матрица: 7\n" +
                                "Деление: 8\n" +
                                "Выход: 0\n"
                );
                int nFirst, nSecond;
                ComplexMatrix resultMatrix;
                ComplexNumber resultComplex;
                try {
                    switch (action = scanner.nextInt()) {
                        case 1:
                            System.out.println("Введите номера двух матриц, которые хотите сложить: ");
                            nFirst = scanner.nextInt();
                            nSecond = scanner.nextInt();
                            resultMatrix = array.addMatrix(nFirst, nSecond);
                            System.out.println("Результат: ");
                            resultMatrix.printMatrix();
                            break;
                        case 2:
                            System.out.println("Введите номера двух матриц. Сначала уменьшаемое, затем - вычитаемое: ");
                            nFirst = scanner.nextInt();
                            nSecond = scanner.nextInt();
                            resultMatrix = array.substractMatrix(nFirst, nSecond);
                            System.out.println("Результат: ");
                            resultMatrix.printMatrix();
                            break;
                        case 3:
                            System.out.println("Введите номер матрицы, которую хотите умножить на число");
                            nFirst = scanner.nextInt();
                            System.out.println("Введите число");
                            double real = scanner.nextDouble();
                            double imaginary = scanner.nextDouble();
                            ComplexNumber complexNumber = new ComplexNumber(real, imaginary);
                            resultMatrix = array.multiplyByNumberMatrix(nFirst, complexNumber);
                            resultMatrix.printMatrix();
                            break;
                        case 4:
                            System.out.println("Введите номера двух матриц, которые хотите умножить: ");
                            nFirst = scanner.nextInt();
                            nSecond = scanner.nextInt();
                            resultMatrix = array.multiplyMatrix(nFirst, nSecond);
                            System.out.println("Результат: ");
                            resultMatrix.printMatrix();
                            break;
                        case 5:
                            System.out.println("Введите номер матрицы которую хотите транспонировать: ");
                            nFirst = scanner.nextInt();
                            resultMatrix = array.transposeMatrix(nFirst);
                            System.out.println("Результат: ");
                            resultMatrix.printMatrix();
                            break;
                        case 6:
                            System.out.println("Введите номер матрицы у которой хотите найти детерминант: ");
                            nFirst = scanner.nextInt();
                            resultComplex = array.determinantMatrix(nFirst);
                            System.out.println("Результат: " + resultComplex.getReal() + " " + resultComplex.getImaginary() + " i");

                            break;
                        case 7:
                            System.out.println("Введите номер матрицы, от которой хотите найти обратную: ");
                            nFirst = scanner.nextInt();
                            resultMatrix = array.inverseMatrix(nFirst);
                            System.out.println("Результат: ");
                            resultMatrix.printMatrix();
                            break;
                        case 8:
                            System.out.println("Введите номера двух матриц. Сначала делимое, затем - делитель: ");
                            nFirst = scanner.nextInt();
                            nSecond = scanner.nextInt();
                            resultMatrix = array.divideMatrix(nFirst, nSecond);
                            System.out.println("Результат: ");
                            resultMatrix.printMatrix();
                            break;
                        case 0:
                            break;
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            } while (action != 0);
        }catch (Exception e) {
            System.out.println("На вход должны поступать только числовые значения!");
        }
    }
}
