import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Krypto {

    static Scanner scanner = new Scanner(System.in);


// do zadania 2

    public static BigInteger EucliAlgor(BigInteger b, BigInteger n) {

        BigInteger b0 = b;
        BigInteger n0 = n;
        BigInteger u0 = new BigInteger("0");
        BigInteger u = new BigInteger("1");
        BigInteger v = n.divide(b0);


        //reszta
        BigInteger r = n0.mod(b0);

//		System.out.println("u "+u);
//		System.out.println("r "+r);

        // dopóki reszta większa od 0
        while (r.compareTo(new BigInteger("0")) > 0) {


            BigInteger temp = u0.subtract(v.multiply(u));
            // temp =   0 - 1*1 = -1

            // przepisuje zmienne
            u0 = u;
            u = temp;
            n0 = b0;
            b0 = r;
            v = n0.divide(b0);
            r = n0.subtract(v.multiply(b0));

			System.out.println("u "+u);
			System.out.println("r "+r);
        }


//		System.out.println("nwd "+b0);
        return u.mod(n);
    }

    // koniec zadania 2
//////////////////////////////////////////////////////////////////////////////////////////////////////

    // zadanie nr 3


    private static BigInteger fastModPow(BigInteger base, BigInteger exponent, final BigInteger modulo) {

        BigInteger result = BigInteger.ONE;
        while (exponent.compareTo(BigInteger.ZERO) > 0) {
            if (exponent.testBit(0)) // to wykładnik jest nieparzysty
                result = (result.multiply(base)).mod(modulo);
            exponent = exponent.shiftRight(1);
            base = (base.multiply(base)).mod(modulo);
        }
        return result.mod(modulo);
    }

    // koniec zadania nr3
///////////////////////////////////////////////////////////////////////////////////////
// zadanie 6 - sprawdzanie czy podana liczba jest liczbą pierwszą na Biginteger

    private final static Random rand = new Random();

    private static BigInteger getRandomFermatBase(BigInteger n)
    {

        while (true)
        {
            final BigInteger a = new BigInteger (n.bitLength(), rand);
            // must have 1 <= a < n
            if (BigInteger.ONE.compareTo(a) <= 0 && a.compareTo(n) < 0)
            {
                return a;
            }
        }
    }

    public static boolean checkPrime(BigInteger n, int maxIterations)
    {
        if (n.equals(BigInteger.ONE))
            return false;

        for (int i = 0; i < maxIterations; i++)
        {
            BigInteger a = getRandomFermatBase(n);
            a = a.modPow(n.subtract(BigInteger.ONE), n);

            if (!a.equals(BigInteger.ONE))
                return false;
        }

        return true;
    }

    // koniec zadania 6 :)
////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) throws IOException {


        modul1();

    }

    public static void modul1() {

        int choice;


        Scanner keyboard = new Scanner(System.in);


        System.out.println("Wybierz, które zadanie z pierwszego modulu będzie wykonywane (wpisz od 1 do 6)");
        choice = (int) read_range(keyboard, 1, 6);

        if (choice == 1) {
            System.out.println("Modul nr1 - Zadanie nr1");

            System.out.println("Podaj liczbę n");
            BigInteger n;
            n = scanner.nextBigInteger();


            System.out.println("Podaj liczbę bitów k");
            BigInteger k;
            k = scanner.nextBigInteger();

            //long max = (long) (Math.pow(2,k) - 1);
            BigInteger result = BigInteger.ONE;
            BigInteger exponent = k;
            BigInteger base = BigInteger.valueOf(2);
            while (exponent.signum() > 0) {
                if (exponent.testBit(0))
                    result = result.multiply(base);
                base = base.multiply(base);
                exponent = exponent.shiftRight(1);
            }

            BigInteger result2 = BigInteger.valueOf(1);
            BigInteger max = result.subtract(result2);
            System.out.println("Górna granica "+ max);


            // long p = (long) Math.pow(2, k-1);
            BigInteger p = k.subtract(BigInteger.valueOf(1));
            BigInteger result1 = BigInteger.ONE;
            BigInteger base1 = BigInteger.valueOf(2);
            while (p.signum() > 0) {
                if (p.testBit(0))
                    result1 = result1.multiply(base1);
                base1 = base1.multiply(base1);
                p = p.shiftRight(1);
            }
            System.out.println("Dolna granica "+ result1);

            BigInteger x = n.subtract(BigInteger.valueOf(1));

            Random r = new Random();


            int res = result1.compareTo(x);

            if(res == 1){

                System.out.println("Błąd !! Podana liczba bitów jest za duża w stosunku do pierwszej podanej liczby !! Spróbuj ponownie !! ");
                modul1();

            }
            else if (res == 0 || res == -1){
                int res1 = max.compareTo(x);

                if (res1 == -1) {



                    BigInteger bigInteger = max.subtract(result1);
                    Random randNum = new Random();
                    int len = max.bitLength();
                    BigInteger resum = new BigInteger(len, randNum);
                    if (resum.compareTo(result1) < 0)
                        resum = resum.add(result1);
                    if (resum.compareTo(bigInteger) >= 0)
                        resum = resum.mod(bigInteger).add(result1);
                    System.out.println("Twoja wylosowana liczba to: "+ resum);
                    System.out.println("Z zadania nr 6: Czy twoja wylosowana liczba jest liczbą pierwszą?? --> "+ checkPrime(resum, 20));
                }


                else if (res1 == 0 || res1 == 1 ){

                    BigInteger bigInteger1 = x.subtract(result1);
                    Random randNum1 = new Random();
                    int len = x.bitLength();
                    BigInteger resum1 = new BigInteger(len, randNum1);
                    if (resum1.compareTo(result1) < 0)
                        resum1 = resum1.add(result1);
                    if (resum1.compareTo(bigInteger1) >= 0)
                        resum1 = resum1.mod(bigInteger1).add(result1);
                    System.out.println("Twoja wylosowana liczba to: "+ resum1);
                    System.out.println("Z zadania nr 6: Czy twoja wylosowana liczba jest liczbą pierwszą?? --> "+ checkPrime(resum1, 20));
                }
            }
            modul1();

        } else if (choice == 2) {

            System.out.println("Modul nr1 - Zadanie nr2");

            System.out.println("Podaj liczbę n");
            BigInteger n;
            n = scanner.nextBigInteger();


            System.out.println("Podaj liczbę bitów b");
            BigInteger b;
            b = scanner.nextBigInteger();





            System.out.println("Wynik wynosi: " + EucliAlgor(b, n));
            System.out.println("Z zadania nr 6: Czy twój wynik jest liczbą pierwszą?? --> "+ checkPrime(EucliAlgor(b, n), 20));





        modul1();
        } else if (choice == 3) {
            System.out.println("Modul nr1 - Zadanie nr3");


            System.out.println("Podaj pierwszą liczbę b");
            BigInteger b9;
            b9 = scanner.nextBigInteger();

            System.out.println("Podaj drugą liczbę k (wykładnik)");

            BigInteger k9;
            k9 = scanner.nextBigInteger();

            System.out.println("Podaj trzecią liczbę n (modulo)");

            BigInteger n9;
            n9 = scanner.nextBigInteger();


            System.out.println("Wynik --> " + fastModPow(b9, k9, n9));
            System.out.println("Z zadania nr 6: Czy twój wynik jest liczbą pierwszą?? --> "+ checkPrime(fastModPow(b9, k9, n9), 20));


            modul1();
        } else if (choice == 4) {
            System.out.println("Modul nr1 - Zadanie nr4");

            System.out.println("Podaj pierwszą liczbę b");
            BigInteger b5;
            b5 = scanner.nextBigInteger();

            System.out.println("Podaj drugą liczbę p");
            BigInteger p5;
            p5 = scanner.nextBigInteger();

            BigInteger p6 = p5.subtract(BigInteger.valueOf(1));
            //(p-1)/2;

            BigInteger one1 = BigInteger.valueOf(1);
            BigInteger two1 = BigInteger.valueOf(2);
            BigInteger k = p5.subtract(one1);
            //System.out.println("p - 1 " +k);
            BigInteger k1 = k.divide(two1);
            //System.out.println("k/2 " +k1);

            BigInteger result0 = fastModPow(b5, k1, p5);

            System.out.println("wynik " + result0);

            BigInteger x1 = BigInteger.valueOf(1);

            int res0 = result0.compareTo(x1);
            int res2 = b5.compareTo(p5);

            // porównywanie

            //if (res2 == 1 || res2 == 0){
            //    System.out.println("Błąd !! Liczba b musi być mniejsza od liczby p --> zbiór od (0,...," + p6 + ")!! Spróbuj ponownie !!");
            //}

            if (res0 == 0){
                System.out.println("Jest resztą kwadratową? True");
            }
            if (res0 == 1 || res0 == -1){
                System.out.println("Jest resztą kwadratową? False");
            }

            modul1();
        }

        if (choice == 5) {
            System.out.println("Modul nr1 - Zadanie nr5");


            System.out.println("Podaj pierwszą liczbę b");
            BigInteger a1;
            a1 = scanner.nextBigInteger();

            System.out.println("Podaj drugą liczbę p");
            BigInteger p7;
            p7 = scanner.nextBigInteger();

            // (p+1)/4

            BigInteger one1 = BigInteger.valueOf(1);
            BigInteger four1 = BigInteger.valueOf(4);

            BigInteger t = p7.add(one1);
            System.out.println("p + 1 " + t);
            BigInteger t1 = t.divide(four1);
            System.out.println("t/4 " + t1);

            BigInteger result100 = fastModPow(a1, t1, p7);
            System.out.println("Twoja liczba wynosi --> " + result100);
            System.out.println("Z zadania nr 6: Czy twoja liczba jest liczbą pierwszą?? --> "+ checkPrime(result100, 20));



            modul1();
        }

        if (choice == 6) {
            System.out.println("Modul nr1 - Zadanie nr5");

            System.out.println("Wpisz numer, który ma być sprawdzony pod względem bycie liczbą pierwszą:");

            BigInteger b10;
            b10 = scanner.nextBigInteger();

            System.out.println("Czy twoja liczba jest liczbą pierwszą?? --> "+ checkPrime(b10, 20));

            modul1();
        }

        if (choice > 6 || choice < 1) {
            System.out.println("Błąd! Wybierz numer z klawiatury od 1 do 6 ponownie");

            modul1();
        }


    }




    private static int read_range (Scanner scanner, int low, int high) {
        int value;
        value = scanner.nextInt();
        while (value < low || value > high) {
            System.out.print("Błąd! Wybierz numer z klawiatury od " + low + " do " + high + ": ");
            value = scanner.nextInt();
        }
        return value;
    }
}
