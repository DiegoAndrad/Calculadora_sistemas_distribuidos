package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServEspecial2 {
    public static void main(String[] args) throws IOException {
        //servidor está escutando na porta 6790
        ServerSocket ss2 = new ServerSocket(6790);

        // executando um loop infinito para obter solicitação do cliente
        while (true) {
            Socket s2 = null;

            try {
                // objeto de soquete para receber solicitações de clientes de entrada
                s2 = ss2.accept();

                System.out.println("Novo Cliente Conectado : " + s2);

                //obtendo entrada e saida
                DataInputStream dis2 = new DataInputStream(s2.getInputStream());
                DataOutputStream dos2 = new DataOutputStream(s2.getOutputStream());

                System.out.println("Atribuindo novo tópico para este cliente");

                // cria um novo objeto de thread
                Thread t2 = new ClientHandler2(s2, dis2, dos2);

                // Invocando o método start ()
                t2.start();

            } catch (Exception e) {
                s2.close();
                e.printStackTrace();
            }
        }
    }
}
