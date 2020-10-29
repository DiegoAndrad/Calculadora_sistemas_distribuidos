package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    Socket s1;
    Socket s2;
    // obtendo input e output streams
    DataInputStream dis1;
    DataOutputStream dos1;
    DataInputStream dis2;
    DataOutputStream dos2;
    @FXML
    public Label display;
    @FXML
    public BorderPane borderPane;

    public String valor1 = "", valor2 = "";
    public String operador, operadorAux, memoria = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTimeout(() -> {
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        }, 1000);

        try {
            Scanner scn1 = new Scanner(System.in);

            // pegando localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // estabelecer a conexão com a porta do servidor1 9888 e servidor2 9889
            s1 = new Socket(ip, 6789);
            s2 = new Socket(ip, 6790);
            // obtendo input e output streams
            dis1 = new DataInputStream(s1.getInputStream());
            dos1 = new DataOutputStream(s1.getOutputStream());
            dis2 = new DataInputStream(s2.getInputStream());
            dos2 = new DataOutputStream(s2.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    public void numeros(MouseEvent event) {
        display.setText("");
        String id = ((Control) event.getSource()).getId();
        String valor = "";

        switch (id) {
            case "um":
                valor = "1";
                break;
            case "dois":
                valor = "2";
                break;
            case "tres":
                valor = "3";
                break;
            case "quatro":
                valor = "4";
                break;
            case "cinco":
                valor = "5";
                break;
            case "seis":
                valor = "6";
                break;
            case "sete":
                valor = "7";
                break;
            case "oito":
                valor = "8";
                break;
            case "nove":
                valor = "9";
                break;
            case "zero":
                valor = "0";
                break;
            case "ponto":
                valor = ".";
                break;
        }

        //Regra

        if (operador == null) {
            // preencher o valor 1
            valor1 += valor;
            display.setText(valor1);
        } else {
            // preencher valor 2
            valor2 += valor;
            display.setText(valor2);
            memoria = valor1 + " " + operador + " " + valor2; //Protocolo de Envio
        }

    }

    public void operacao(MouseEvent event) throws IOException {
        String id = ((Control) event.getSource()).getId();

        if (valor1 != null) {

            switch (id) {
                case "somar":
                    operador = "+";
                    display.setText(operador);
                    break;
                case "subtrair":
                    operador = "-";
                    display.setText(operador);
                    break;
                case "mult":
                    operador = "*";
                    display.setText(operador);
                    break;
                case "dividir":
                    operador = "/";
                    display.setText(operador);
                    break;
                case "pot":
                    operador = "^";
                    display.setText(operador);
                    break;
                case "porcent":
                    operador = "%";
                    display.setText(operador);
                    break;
                case "raiz":
                    operador = "√";
                    display.setText(operador);
                    memoria = valor1 + " " + operador + " " + valor2;
                    break;
            }
        }
    }

    public void igual() throws IOException {

        if (operador.equals("+") || operador.equals("-") || operador.equals("*") || operador.equals("/")) {
            String aux = operador;
            dos1.writeUTF(aux);
            if (dis1.readUTF().trim().equals("Ok")) {
                dos1.writeUTF(memoria); //passando protocolo
                String resultado = dis1.readUTF();
                display.setText(resultado);
            }
        }

        if (operador.equals("^") || operador.equals("%") || operador.equals("√")) {
            if (operador.equals("^")) {
                display.setText("");
                String aux = operador;
                dos2.writeUTF(aux);
                if (dis2.readUTF().trim().equals("Ok")) {
                    dos2.writeUTF(memoria);
                    memoria = dis2.readUTF();
                    display.setText(memoria);
                }
            }
            if (operador.equals("√")) {
                display.setText("");
                String aux = operador;
                dos2.writeUTF(aux);
                if (dis2.readUTF().trim().equals("Ok")) {
                    dos2.writeUTF(memoria);
                    memoria = dis2.readUTF();
                    display.setText(memoria);
                }
            }
            if (operador.equals("%")) {
                display.setText("");
                String aux = operador;
                dos2.writeUTF(aux);
                if (dis2.readUTF().trim().equals("Ok")) {
                    dos2.writeUTF(memoria);
                    memoria = dis2.readUTF();
                    display.setText(memoria);
                }
            }

        }
    }

    public void limpar(MouseEvent event) {
        String id = ((Control) event.getSource()).getId();
        if (id.equals("c")) {
            display.setText("0");
            valor1 = "";
            valor2 = "";
            operador = null;
        }
    }

    private void closeWindowEvent(WindowEvent event) {
        System.out.println("Encerrando ...");
        try {
            if (dis1 != null && dis2 != null) {
                dos1.writeUTF("sair");
                dos2.writeUTF("sair");
                dis1.close();
                dis2.close();
                dos1.close();
                dos2.close();
                s1.close();
                s2.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
