public class ComplexNumber {
    private double real;
    private double imaginary;
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    public double getReal() { return real; }
    public double getImaginary() { return imaginary; }
    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }
    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
    }
    public ComplexNumber multiply(ComplexNumber other) {
        return new ComplexNumber(
                this.real * other.real - this.imaginary * other.imaginary,
                this.real * other.imaginary + this.imaginary * other.real
        );
    }
    public ComplexNumber divide(ComplexNumber other) {
        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        double realPart = (this.real * other.real + + this.imaginary * other.imaginary) / denominator;
        double imgPart = (other.real * this.imaginary - this.real * other.imaginary) / denominator;
        return new ComplexNumber(realPart, imgPart);
    }
    @Override
    public String toString() {
        if (imaginary >= 0) {
            return "(" + real + " + " + imaginary + " i) ";
        }
        else {
            return "(" + real + " - " + (-imaginary) + " i) ";
        }
    }
}