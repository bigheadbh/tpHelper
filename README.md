# tpHelper
Biblioteca para automatizar a leitura do pub.in nos Tps de AED2
-A ideia por trás dessa biblioteca é automatizar a leitura dos "pub.in" da matéria AED 2.

-Todo pub.in a partir do TP02 segue o padrão: caminho para leitura dos arquivos com informações para montar o Objeto, a keyword "FIM" e um conjunto de operações.


Pricipais Classes: 

-InputHelper<T>: private static final String FIM =  "FIM";
    		 private final BufferedReader bufferPubIn = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));
    		 T structure;

Construtor: public InputHelper(T structure) {
        	this.structure = structure;
    	    }

Exemplo: InputHelper<BinaryTree<StarWarsCharacter>> inputHelper = new InputHelper<>(binaryTree);		


	-A classe é responsavel por operar o BuffererReader que contém as informações do pub.in redirecionado pelo terminal e também é responsavel por montar os objetos
	 e inserir esses objetos na estrutura (T structure) desejada
		-Utiliza de reflexion e generics para poder receber qualquer estrutura e chamar o metodo "insert" da estrutura recebida de parametro no construtor
			- Method method = structure.getClass().getMethod("insert", Comparable.class);
            		  method.invoke(structure, parameters);


-TreeHelper<T>/HashHelper<T>: extends InputHelper<T>

Construtor: public TreeHelper(T structure) {
        	super(structure);
    	    }

Exemplo: TreeHelper<BinaryTree<StarWarsCharacter>> treeHelper = new TreeHelper<>(binaryTree);
	
	-Essas classes são responsaveis por operar o conjunto de operações do TP03, onde todos os pub.in, depois da primeira keyword "FIM", possuem várias Strings a serem pesquisadas na estrutura
	 e uma segunda keywork "FIM" indicando o fim do arquivo.
		-Utiliza do bufferPubIn que já está aberto e já foi consumida a primeira parte do pub.in para ler a String e chamar o metodo de pesquisar na estrutura recebida de parametro no construtor
			- Method method = structure.getClass().getMethod("searchIfExistsByString", String.class);
                	  Object doesStringExists = method.invoke(structure, parameters);

-Tad Helper - INCOMPLETO

//TODO Instruções para buildar e utilizar a biblioteca.
