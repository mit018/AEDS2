public class Quiz5_8 {
    public static void main(String[] args) {
        int n = 5;
        double a = 5, b = 2;
        System.out.println(somatorioPA(a, b, n));
    }

    public static double somatorioPA(double a, double b, int n){
        return (((2*a) + (b*n)) * (n + 1)) / 2;
    }
}
