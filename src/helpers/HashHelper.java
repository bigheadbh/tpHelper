package helpers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HashHelper<T> extends InputHelper<T> {

    public HashHelper(T structure) {
        super(structure);
    }

    public void operateHashWithPubInOperations() {
        String operation = readOperationFromPubIn();
        while(operation != null) {
            try {
                Object[] parameters = new Object[1];
                parameters[0] = operation;

                Method method = structure.getClass().getMethod("searchIfExistsByString", String.class);
                Object doesStringExists = method.invoke(structure, parameters);

                if((boolean) doesStringExists) {
                    System.out.println("SIM");
                } else {
                    System.out.println("NÃO");
                }
            } catch(InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            operation = readOperationFromPubIn();
        }
    }
}
