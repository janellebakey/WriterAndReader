/*
    Southern Oregon University - CS258 Computer Science III - Lab 2

    Author: Janelle Bakey
    Date: 4/26/2017
    Class: TextReader.java
    Desc: This program reads the SeqText.dat and seqText2.dat files created by TextWriter
        In sequential format; contain records of name, age, and salary.
 */

import cs258.*;
import java.io.*;
import java.text.*;


public class TextReader
{   public static final int NAME_SIZE=30;
    public static void main(String args[])
    {
        //instance variables
        String name;
        int age;
        double salary;

        if (!new File("seqtext.dat").exists() ||
                !new File("seqtext2.dat").exists()) {
            System.out.println("Files don't exist");
            System.exit(1);
        }
        TextRead file1 = null;
        TextRead file2 = null;
        try {
            file1 = new TextRead("seqtext.dat");
            file2 = new TextRead("seqtext2.dat");
        }
        catch (Exception e)
        {
            System.err.println("An unexpected exception, " +
                    e.toString() + " occurred:");
            e.printStackTrace(System.err);
            System.err.println("\nPlease report this error to Pete Nordquist");
            System.exit(1);
        }
        NumberFormat money = NumberFormat.getCurrencyInstance();
        DecimalFormat fmt = new DecimalFormat("##0.##");
        String spaces = "                              ";

        System.out.println("Text file reader\n");
        do {
            if (!file1.endOfFile()) {
                name = file1.readString();
                age  = file1.readInt();
                salary = file1.readDouble();
                System.out.print(name);
                if (name.length() < NAME_SIZE)
                    System.out.print(spaces.substring(0, NAME_SIZE-name.length()) );
                System.out.println ( " " + fmt.format(age) + " " + money.format(salary) );
            }
            if (!file2.endOfFile()) {
                name = file2.readString();
                age  = file2.readInt();
                salary = file2.readDouble();
                System.out.print(name);
                if (name.length() < NAME_SIZE)
                    System.out.print
                            ( spaces.substring(0, NAME_SIZE-name.length()) );
                System.out.println
                        ( " " + fmt.format(age) + " " + money.format(salary) );
            }
        }  while (!file1.endOfFile()||!file2.endOfFile());

        try {
            file1.close();
            file2.close();
        }
        catch (Exception e)
        {
            System.err.println("An unexpected exception, " +
                    e.toString() + " occurred:");
            e.printStackTrace(System.err);
            System.err.println("\nPlease report this error to Pete Nordquist");
            System.exit(1);
        }
        System.out.println("\nTextReader complete; data printed");
    }
}