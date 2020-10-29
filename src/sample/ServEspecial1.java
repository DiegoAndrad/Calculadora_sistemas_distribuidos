package sample;

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

public class ServEspecial1 {

    public static void main(String[] args) throws IOException {
        //servidor está escutando na porta 6789
        ServerSocket ss1 = new ServerSocket(6789);

        // executando um loop infinito para obter solicitação do cliente
        while (true) {
            Socket s1 = null;

            try {
                // objeto de soquete para receber solicitações de clientes de entrada
                s1 = ss1.accept();

                System.out.println("Novo Cliente Conectado : " + s1);

                //obtendo entrada e saida
                DataInputStream dis1 = new DataInputStream(s1.getInputStream());
                DataOutputStream dos1 = new DataOutputStream(s1.getOutputStream());

                System.out.println("Atribuindo novo tópico para este cliente");

                // cria um novo objeto de thread
                Thread t1 = new ClientHandler1(s1, dis1, dos1);

                // Invocando o método start ()
                t1.start();

            } catch (Exception e) {
                s1.close();
                e.printStackTrace();
            }
        }
    }
}
