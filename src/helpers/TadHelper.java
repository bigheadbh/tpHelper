package helpers;

import tpClass.StarWarsCharacter;
import utils.MyIO;
import java.lang.reflect.Method;

public class TadHelper<T> extends InputHelper<T> {

        public TadHelper(T structure) {
            super(structure);
        }

        public void operateListWithPubInOperations() {
            int operationsLeft = Integer.parseInt(readOperationFromPubIn());
            String operation = readOperationFromPubIn();
            for(int i = 0; i < operationsLeft; i++, operation = readOperationFromPubIn()) {
                try {
                    if(operation.contains("II")) {
                        inserirInicioHelper(operation);
                    } else if(operation.contains("IF") || operation.contains("I /tmp/")) {
                        inserirFimHelper(operation);
                    } else if(operation.contains("RI")) {
                        removerInicioHelper(operation);
                    } else if(operation.contains("RF") || operation.equals("R")) {
                        removerFimHelper(operation);
                    } else if(operation.contains("I*")) {
                        inserirPosHelper(operation);
                    } else if(operation.contains("R*")) {
                        removerPosHelper(operation);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void inserirInicioHelper(String operation) throws Exception {
            if(operation == null || operation.isEmpty()) {
                throw new Exception("Invalid Operation -> ERRO: inserirInicioHelper");
            } else {

                int indexAux = operation.indexOf("/");
                String filePath = "";

                for(int i = indexAux; i < operation.length(); i++) {
                    filePath += operation.charAt(i);
                }
                try {
                    StarWarsCharacter starWarsCharacter = new StarWarsCharacter();
                    starWarsCharacter.readCharacter(filePath);

                    Object[] parameters = new Object[1];
                    parameters[0] = starWarsCharacter;

                    Method method = structure.getClass().getMethod("insertInit", Comparable.class);
                    method.invoke(structure, parameters);

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void inserirFimHelper(String operation) throws Exception {
            if(operation == null || operation.isEmpty()) {
                throw new Exception("Invalid Operation -> ERRO: inserirFimHelper");
            } else {

                int indexAux = operation.indexOf("/");
                String filePath = "";

                for(int i = indexAux; i < operation.length(); i++) {
                    filePath += operation.charAt(i);
                }
                try {
                    StarWarsCharacter starWarsCharacter = new StarWarsCharacter();
                    starWarsCharacter.readCharacter(filePath);

                    Object[] parameters = new Object[1];
                    parameters[0] = starWarsCharacter;

                    Method method = structure.getClass().getMethod("insert", Comparable.class);
                    method.invoke(structure, parameters);

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void inserirPosHelper(String operation) throws Exception {
            if(operation == null || operation.isEmpty()) {
                throw new Exception("Operacao invalida");
            } else {
                int pos = 0;
                String aux = "";
                String filePath = "";
                int indexAux = operation.indexOf("/");
                int index = operation.indexOf(" ");

                for(int i = index+1; i < indexAux-1; i++) {
                    aux += operation.charAt(i);
                }
                pos = Integer.parseInt(aux);

                for(int i = indexAux; i < operation.length(); i++) {
                    filePath += operation.charAt(i);
                }
                try {
                    StarWarsCharacter starWarsCharacter = new StarWarsCharacter();
                    starWarsCharacter.readCharacter(filePath);

                    Object[] parameters = new Object[2];
                    parameters[0] = starWarsCharacter;
                    parameters[1] = pos;

                    Method method = structure.getClass().getMethod("insertPosition", Comparable.class, Integer.class);
                    method.invoke(structure, parameters);

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void removerInicioHelper(String operation) throws Exception {
            if(operation == null || operation.isEmpty()) {
                throw new Exception("Operacao invalida");
            } else {
                try {
                    Method method = structure.getClass().getMethod("removeInit");
                    method.invoke(structure);

                    StarWarsCharacter character = (StarWarsCharacter)method.invoke(structure);
                    MyIO.println("(R) "+ character.getName());
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void removerFimHelper(String operation) throws Exception {
            if(operation == null || operation.isEmpty()) {
                throw new Exception("Operacao invalida");
            } else {
                try {
                    Method method = structure.getClass().getMethod("removeEnd");
                    method.invoke(structure);

                    StarWarsCharacter character = (StarWarsCharacter)method.invoke(structure);
                    MyIO.println("(R) "+ character.getName());

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void removerPosHelper(String operation) throws Exception {

            if(operation == null || operation.isEmpty()) {
                throw new Exception("Operacao invalida");
            } else {
                int pos = 0;
                int index = operation.indexOf(" ");
                String aux = "";

                for(int i = index+1; i < operation.length(); i++) {
                    aux += operation.charAt(i);
                }
                pos = Integer.parseInt(aux);
                try {
                    Object[] parameters = new Object[1];
                    parameters[0] = pos;

                    Method method = structure.getClass().getMethod("removePosition", Integer.class);
                    method.invoke(structure, parameters);

                    StarWarsCharacter character = (StarWarsCharacter)method.invoke(structure, parameters);
                    MyIO.println("(R) "+ character.getName());
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

}
