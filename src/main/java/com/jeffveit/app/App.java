package com.jeffveit.app;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    public static int getAge()
    {
        int age = 0;

        Scanner input = new Scanner(System.in);
        System.out.println("What is your age?");
        age = input.nextInt();
        return age;
    }
    public static void main( String[] args )
    {
        System.out.println( "Running movie recommender...." );
        int mainAge = getAge();
    }
}
