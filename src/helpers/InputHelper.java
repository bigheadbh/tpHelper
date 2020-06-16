package helpers;

import tpClass.StarWarsCharacter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/*
    This class helps to read, create and save, in the chosen data structure, the StarWarsCharacter object from the pub.in file.
    The pub.in file contains multiples paths for txt files that carries the information to mount these objects(Ex. /tmp/personagens/Ackbar.txt).
    It also contains the FIM keyword indicating the end of the paths to the txt file, after that it contains the operation to be done
    or the key to search for something in the structure.
 */
public class InputHelper<T> {
    private static final String FIM =  "FIM";
    private final BufferedReader bufferPubIn = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));
    T structure;

    public InputHelper(T structure) {
        this.structure = structure;
    }

    public void readPathFromPubInFile() {
        try {
            String path = bufferPubIn.readLine();
            while(!isFim(path)) {
                createAndSaveStarWarsCharacter(path);
                path = bufferPubIn.readLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void createAndSaveStarWarsCharacter(String filePath) {
        try {

            BufferedReader starWarsBuffer = new BufferedReader(new FileReader(filePath));
            StarWarsCharacter starWarsCharacter = new StarWarsCharacter();
            String json = starWarsBuffer.readLine();

            starWarsCharacter.readCharacter(json);

            Object[] parameters = new Object[1];
            parameters[0] = starWarsCharacter;

            Method method = structure.getClass().getMethod("insert", Comparable.class);
            method.invoke(structure, parameters);

        } catch(IOException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public String readOperationFromPubIn() {
        String operation = "";
        try {
            operation = bufferPubIn.readLine();
            if(isFim(operation)) {
                operation = null;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return operation;
    }

    public boolean isFim(String s) {
        return FIM.equals(s);
    }
}
