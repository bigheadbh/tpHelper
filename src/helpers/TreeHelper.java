package helpers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TreeHelper<T> extends InputHelper<T> {

    public TreeHelper(T structure) {
        super(structure);
    }

    public void operateTreeWithPubInOperations() {
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
                    System.out.println("NAO");
                }
            } catch(InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            operation = readOperationFromPubIn();
        }
    }
}
