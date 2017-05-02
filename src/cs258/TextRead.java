/*
    Southern Oregon University - CS258 Computer Science III - Lab 2

    Author: Janelle Bakey
    Date: 4/26/2017
    Class: TextRead.java
    Desc: Class that extends the Keyboard.java and instantiates a sequential text file
        as well as a close method and endOfFile method
 */

package cs258;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TextRead extends Keyboard
{
    /**
     *
     * @param filename
     * @throws FileNotFoundException
     */
    public TextRead(String filename) throws FileNotFoundException {
       in = new BufferedReader(new FileReader(filename));
    }

    /**
     *
     * @throws IOException
     */
    public void close() throws IOException {
        in.close();
    }

    /**
     *
     * @return
     */
    public boolean endOfFile(){ //check to see if at the end of file
        try{
            if(in.ready() == false){
                return true;
            }
        }
        catch(IOException e){
            e.printStackTrace(System.err);
        }
        return false;
    }
}