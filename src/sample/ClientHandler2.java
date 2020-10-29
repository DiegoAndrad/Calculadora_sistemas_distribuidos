package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.io.IOException;

class ClientHandler2 extends Thread {

    final DataInputStream dis2;
    final DataOutputStream dos2;
    final Socket s2;


    //Construtor
    public ClientHandler2(Socket s2, DataInputStream dis2, DataOutputStream dos2) {
        this.s2 = s2;
        this.dis2 = dis2;
        this.dos2 = dos2;

    }

    @Override
    public void run() {
        String str, operador, resultado = "";
        String[] valores;

        while (true) {

            try {
                // receber a resposta do cliente
                operador = dis2.readUTF();
                if (operador.equals("sair")) {
                    System.out.println("Cliente " + this.s2 + " enviou sair...");
                    System.out.println("Fechando esta conexão");
                    this.s2.close();
                    System.out.println("Conexão Fechada");
                    break;
                }
                if (operador != null) {
                    //operador = dis2.readUTF();
                    if (operador.equals("%") || operador.equals("√") || operador.equals("^")) {
                        dos2.writeUTF("Ok");
                    }
                    str = dis2.readUTF();
                    valores = str.trim().split(" ");
                    double v1, v2;
                    switch (operador) {
                        case "%":
                            v1 = (Double.parseDouble(valores[0]));
                            v2 = (Double.parseDouble(valores[2]));
                            resultado = String.valueOf((v1 * v2) / 100);
                            dos2.writeUTF(resultado);
                            break;

                        case "√":
                            v1 = (Double.parseDouble(valores[0]));
                            resultado = String.valueOf(Math.sqrt(v1));
                            dos2.writeUTF(resultado);
                            break;

                        case "^":
                            v1 = (Double.parseDouble(valores[0]));
                            v2 = (Double.parseDouble(valores[2]));
                            resultado = String.valueOf(Math.pow(v1, v2));
                            dos2.writeUTF(resultado);
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
            this.dis2.close();
            this.dos2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
