package bullscows;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {


        int balls=0;
        int count=0;

        System.out.println("Input the length of the secret code:");

        Scanner scanner = new Scanner(System.in);
//Example 2
//      int el = scanner.nextInt();
        String sel = scanner.next();
        int el = 0;

        try {
            el = Integer.parseInt(sel);
        }catch (Exception e){
            System.out.println("Error: \""+sel+"\" isn't a valid number.");
            return;
        }

        if (el == 0 ) {
            System.out.println("error");
            return;
        }


        System.out.println("Input the number of possible symbols in the code:");

        Scanner scanner1 = new Scanner(System.in);
        int elAbc = scanner1.nextInt();


//Example 1
        if(el > elAbc) {
            System.out.println("Error: it's not possible to generate a code with a length of "+el+" with "+elAbc+" unique symbols.");
        }else
//Example 3
        if(elAbc>36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        }else



        if(el >= 36){
            System.out.println("Error: can't generate a secret number with a length of 36 because there aren't enough unique digits.");
        }else {
            String randomNum = randomNumber(el, elAbc);
//           5 System.out.println("randomNum"+randomNum);

//            System.out.println("Okay, let's start a game!");

            String starNN = randomStar(el);

            if (elAbc<=10){
                System.out.println("The secret is prepared: "+starNN+"(0-9)");
            }else {
                char endS = endSimvol(elAbc);
                System.out.println("The secret is prepared: "+starNN+"(0-9, a-"+endS+")");
            }

            System.out.println("Okay, let's start a game!");
            while (true) {

                count++;
                System.out.println("Turn " + count);

                balls = countBullCows(randomNum);

                if (balls == el) {
                    System.out.println("Congratulations! You guessed the secret code.");
                    break;
                }
            }
        }

    }

    public static String randomStar(int el){
        String starN = "";
        for(int i=0; i< el; i++){
            starN +="*";
        }
        return starN;
    }

    public static char endSimvol(int elAbc){
        StringBuilder abcAbc = new StringBuilder();
        abcAbc.append("abcdefghijklmnopqrstuvwxyz");
        char endSimvol = abcAbc.charAt(elAbc-11);

        return endSimvol;
    }




    public static String randomNumber(int el, int elAbc) {

        Random random = new Random();
        String string1="";
        String stringAbc="";

        while (true) {
           long pseudoRandomNumber = Math.abs(random.nextLong());
//          long pseudoRandomNumber = System.nanoTime();
//          1 System.out.println("pseudoRandomNumber"+pseudoRandomNumber);

            StringBuilder str = new StringBuilder();
            str.append(pseudoRandomNumber);

            String string = str.substring(0);

            char[] chars = string.toCharArray();
            Set<Character> charSet = new LinkedHashSet<Character>();
            for (char c : chars) {
                charSet.add(c);
            }

            StringBuilder sb = new StringBuilder();
            for (Character character : charSet) {
                sb.append(character).reverse();
            }


            StringBuilder sbAbc = new StringBuilder();
            sbAbc.append("abcdefghijklmnopqrstuvwxyz");
//           2 System.out.println("sbAbc:"+sbAbc);


            if(el>10) {
                stringAbc = sbAbc.substring(0, elAbc-10);
                System.out.println("sssssssss:"+stringAbc);
                sb.append(stringAbc);
            }

//          3  System.out.println("aaaaaaaaa:"+sb);




            if(sb.length() >= el){
                sb = sb.charAt(0) == '0' ? sb.reverse() : sb;
                string1 = sb.substring(0, el);
                break;
            }
        }
//       6 System.out.println("secret:"+string1); // secret code
        return string1;
    }

    public static int countBullCows(String randomNum) {

        Scanner scanner = new Scanner(System.in);

        // corrected
//        int str1 = Integer.parseInt(randomNum);
        String str1 = randomNum;

        String str2 = scanner.next();

        int sum1 = 0; //balls
        int sum2 = 0;
        int sum3 = 0; //cows

        char[] char1 = ("" + str1).toCharArray();
        char[] char2 = (str2).toCharArray();
        int nn = char1.length;

        for (int i = 0; i <= nn-1; i++) {
            if (char1[i] == char2[i])
                sum1++;
        }

        for (int i = 0; i <= nn-1; i++) {
            for (int j = 0; j <= nn-1; j++) {
                if ((char2[i] == char1[j]) && (char2[i] != char1[i]))
                    sum3++;
            }

        }
        System.out.print("Grade: ");

        if (sum1 > 0 && sum3 > 0) {
            String answerBallCow = (sum1 + ((sum1==1)? " bull" : " bulls" )) + " and " + sum3 + (((sum3==1)? " cow" : " cows" ));
            System.out.println(answerBallCow);

        } else if (sum1 == 0 && sum3 > 0) {
            String answerCow = ((sum3==1)? " cow" : " cows" );
            System.out.println(sum3 + answerCow);

        } else if (sum1 > 0 && sum3 == 0) {
            String answerBall=((sum1==1)? " bull" : " bulls" );
            System.out.println(sum1 + answerBall);

        } else if (sum1 == 0 && sum3 == 0) {
            System.out.println("None");
        }
        return sum1;
    }
}
