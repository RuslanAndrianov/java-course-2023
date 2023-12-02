package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {

    private static final int PORT = 4004;
    private static final int MAX_CONNECTIONS = 2;

    private final ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен!");
            int clientsCount = 0;
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                clientsCount++;
                while (true) {
                    String word = in.readLine();
                    if (word == null) {
                        System.out.println("Клиент " + clientSocket + " покинул чат!");
                    }
                    out.write(word + "\n");
                    out.flush();
                }
            } catch (IOException e) {
                System.err.println("Ошибка связи с клиентом!");
            }
        } catch (IOException e) {
            System.err.println("Ошибка запуска сервера!");
        }
    }
}
