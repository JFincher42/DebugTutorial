public class FracTest{
    public static void main(String[] args){
        Fraction first = new Fraction(2,3);
        Fraction second = new Fraction(3,4);

        System.out.println("First: \n");
        System.out.println(first);

        System.out.println("\nSecond: \n");
        System.out.println(second);

        first.add(second);

        System.out.println("First: \n");
        System.out.println(first);

        System.out.println("\nSecond: \n");
        System.out.println(second);

    }
}