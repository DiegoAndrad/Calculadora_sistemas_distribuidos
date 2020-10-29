Calculadora_JavaFx

Ferramentas utilizadas
Java
Intellij IDEA
Socket
JavaFX

Sobre
Calculadora que faz conexão com diferentes servidores, um resolve operações básicas e outro resolve operações especiais

Operações básicas:

Soma +;
Subtração -;
Divisão /;
Multiplicação *;

Operações Especiais:

-> Porcentagem %;
-> Potenciação ^;
-> Raiz Quadrada √;

Interface:

-> Elaborada utilizando JavaFX com extensão calculadora.fxml
-> Em sua Scene Builder foram utilizadas um BorderPane, um Pane, um Label  e um GridPane;
-> Todos os campos de texto e botões foram identificados.
-> Foi aplicado o evento MouseEvent em todos os botões.

Regras:

-> Similiar a uma calculadora do windows, o usuário realiza interação com o mouse clicando nos numeros e operações disponíveis na interface.
-> Calculadora realiza 1(uma) operação de cada vez, a cada clique do botão "C" ao final de cada operação, pode-se realizar outra operação.

Adendos

Servidor

Protocolo ServEspecial1: porta: 9888

-> responsável pelas Operações básicas;
-> recebe string após usuário acionar um botão de igual na interface;
-> servidor recebe esta string e a separa com os metodos trim e split tendo como regra o espaçamemto " ";
-> vetor valores recebe recebe cada parte desta string;
-> em cima dos valores em cada do posição do vetor é realizado as operações e devolvido ao cliente o resultado;
-> tratamento de erro de divisão por "0";

Protocolo ServEspecial2: porta 9889

--> recebe string após usuário acionar um botão de igual na interface;
 -> servidor recebe esta string e a separa com os metodos trim e split tendo como regra o espaçamemto " ";
 -> vetor valores recebe recebe cada parte desta string;
 -> em cima dos valores em cada do posição do vetor é realizado as operações e devolvido ao cliente o resultado;
 
Cliente

Mostra uma interface de calculadora agradável para uso.
Mostra erro de divisão por "0".
