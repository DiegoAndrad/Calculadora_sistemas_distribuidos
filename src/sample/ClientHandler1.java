package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.io.IOException;

class ClientHandler1 extends Thread {

    final DataInputStream dis1;
    final DataOutputStream dos1;
    final Socket s1;

    //Construtor
    public ClientHandler1(Socket s1, DataInputStream dis1, DataOutputStream dos1) {
        this.s1 = s1;
        this.dis1 = dis1;
        this.dos1 = dos1;
    }

    @Override
    public void run() {
        String str, resultado = "", operador;
        String[] valores;

        while (true) {

            try {

                // receber a resposta do cliente
                operador = dis1.readUTF();
                if (operador.equals("sair")) {
                    System.out.println("Cliente " + this.s1 + " enviou sair...");
                    System.out.println("Fechando esta conexão");
                    this.s1.close();
                    System.out.println("Conexão Fechada");
                    break;
                }
                if (operador != null) {
                    //operador = dis1.readUTF();
                    if (operador.equals("+") || operador.equals("-") || operador.equals("*") || operador.equals("/")) {
                        dos1.writeUTF("Ok");
                    }

                    str = dis1.readUTF();
                    valores = str.trim().split(" ");
                    double v1, v2;
                    switch (operador) {
                        case "+":
                            v1 = Double.parseDouble(valores[0]);
                            v2 = Double.parseDouble(valores[2]);
                            resultado = String.valueOf(v1 + v2);
                            dos1.writeUTF(resultado);
                            break;

                        case "-":
                            v1 = Double.parseDouble(valores[0]);
                            v2 = Double.parseDouble(valores[2]);
                            resultado = String.valueOf(v1 - v2);
                            dos1.writeUTF(resultado);
                            break;

                        case "*":
                            v1 = Double.parseDouble(valores[0]);
                            v2 = Double.parseDouble(valores[2]);
                            resultado = String.valueOf(v1 * v2);
                            dos1.writeUTF(resultado);
                            break;

                        case "/":
                            v1 = Double.parseDouble(valores[0]);
                            v2 = Double.parseDouble(valores[2]);
                            ;
                            if (v2 != 0) {
                                resultado = String.valueOf(v1 / v2);

                            } else {
                                resultado = "Não existe divisão por 0";
                            }
                            dos1.writeUTF(resultado);
                            break;
                    }
                } else {
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            // recursos de fechamento
            this.dis1.close();
            this.dos1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
