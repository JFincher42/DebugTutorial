public class FracTest{
    public static void main(String[] args){
        Fraction first = new Fraction(2,3);
        Fraction second = new Fraction(3,4);

        System.out.println("First : " + first);
        System.out.println("Second: " + second);
        System.out.println();
        
        first.add(second);

        System.out.println("First : " + first);
        System.out.println("Second: " + second);

    }
}