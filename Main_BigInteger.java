import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
     public static void main(String[] args) throws FileNotFoundException {
          Scanner sc = new Scanner(new File("DAYSO.in"));
          int n = sc.nextInt();
          BigInteger min = null;
          BigInteger max = null;
          BigInteger sum = BigInteger.ZERO;
          while (n-- > 0) {
               BigInteger number = sc.nextBigInteger();
               if(min == null) {
                    min = number;
                    max = number;
               }
               else {
                    if(number.compareTo(min) < 0) {
                         min = number;
                    }
                    if(number.compareTo(max) > 0) {
                         max = number;
                    }
               }
               sum = sum.add(number);
          }
          System.out.println(min);
          System.out.println(max);
          System.out.println(sum);
     }
}
