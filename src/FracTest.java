public class FracTest{

    public static void main(String[] args){
        Fraction first = new Fraction(2,3);
        Fraction second = new Fraction(3,4);

        System.out.println("Before");
        System.out.println("======");
        System.out.println("First : " + first + ", value: " + first.value());
        System.out.println("Second: " + second + ", value: " + second.value());
        
        first.add(second);

        System.out.println("\nAfter");
        System.out.println("=====");
        System.out.println("First : " + first + ", value: " + first.value());
        System.out.println("Second: " + second + ", value: " + second.value());

    }
}