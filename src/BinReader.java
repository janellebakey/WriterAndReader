/*
    Southern Oregon University - CS258 Computer Science III - Lab 2

    Author: Janelle Bakey
    Date: 4/26/2017
    Class: BinReader.java
    Desc: This program reads binary files; assumes that files are written from the Writers.java program.
        Displays all records before quitting.
 */

import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BinReader {
    public static final int NAME_SIZE=30;
    public static void main(String args[]) {
        String name;
        int age;
        double salary;

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
            NumberFormat money = NumberFormat.getCurrencyInstance();
            DecimalFormat fmt = new DecimalFormat("##0.##");
            String spaces = "                              ";
            try {
                DataInputStream in = new DataInputStream(new FileInputStream(fileName));
                do{
                    name = in.readUTF();
                    age = in.readInt();
                    salary = in.readDouble();
                    in.skipBytes(4);
                    System.out.print(name);
                    if (name.length() < NAME_SIZE)
                        System.out.print(spaces.substring(0, NAME_SIZE-name.length()) );
                    System.out.println ( " " + fmt.format(age) + " " + money.format(salary));
                } while(in.available() != 0);
                in.close();
                System.out.println("\nBinReader complete; data printed");
            }
            catch (FileNotFoundException e){System.out.println(e);}
            catch (IOException e){System.out.println(e);}
            catch (Exception e){System.out.println(e);}
        } else {System.out.println("You cancelled the file dialog");}
    }
}
