/*
    Southern Oregon University - CS258 Computer Science III - Lab 2

    Author: Janelle Bakey
    Date: 4/26/2017
    Class: RandReader.java
    Desc: Reads random files. Assumes files are written by Writers.java program. Reads and displays records in reverse order
 */

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.*;

public class RandReader {
    public static final int NAME_SIZE=30;
    public static void main(String args[]) {
        String name;
        int age;
        double salary;
        NumberFormat money = NumberFormat.getCurrencyInstance();
        DecimalFormat fmt = new DecimalFormat("##0.##");
        String spaces = "                              ";

        String str = System.getProperty ("user.dir");
        JFileChooser chooser = new JFileChooser(str);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String fileName = file.getName();
            System.out.println("You selected " + fileName);
            if (!new File(fileName).exists()) {
                System.out.println("File does not exist");
                System.exit(1);
            }
            try {
                RandomAccessFile ran = new RandomAccessFile(fileName, "r");
                long count = ran.length();

                do{
                    count = count - 48;
                    ran.seek(count);
                    name = ran.readUTF();
                    age = ran.readInt();
                    salary = ran.readDouble();
                    System.out.print(name);
                    if (name.length() < NAME_SIZE)
                        System.out.print(spaces.substring(0, NAME_SIZE-name.length()) );
                    System.out.println ( " " + fmt.format(age) + " " + money.format(salary));
                } while(count > 0);
                    ran.close();
                System.out.println("\nBinReader complete; data printed");
            }
            catch (FileNotFoundException e){System.out.println("File not found."); e.printStackTrace(System.err);}
            catch (IOException e){System.out.println("I/O Exception"); e.printStackTrace(System.err);}
        } else {System.out.println("You cancelled the file dialog");}
    }
}
