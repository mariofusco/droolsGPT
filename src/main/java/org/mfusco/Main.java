package org.mfusco;

import java.util.Scanner;

public class Main {

    // Mario, the firstborn of the Fusco family is born on the day of the attack to the twin towers. Nowadays he works as software engineer and earns a quarter million a year.
    // Mario, the firstborn of the Fusco family is born on the day of the attack to the twin towers. Unfortunately he is unemployed at the moment.

    // Can we grant a mortgage for Mario?

    // Sofia, the daughter of Mario Fusco, is born on the twentysixth day of the ninth month of 2011. She is a very smart student.
    // Can Sofia get a mortgage?

    public static void main(String[] args) {

        MortgageChat mortgageChat = new MortgageChat();

        Scanner sc = new Scanner(System.in);

        System.out.print("> ");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println( mortgageChat.chat(line) );
            System.out.print("> ");
        }
    }
}
