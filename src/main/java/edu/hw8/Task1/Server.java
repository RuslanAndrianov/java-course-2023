package edu.hw8.Task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int MAX_CONNECTIONS = 5;
    private static final int PORT = 8080;

    public static void main(String[] args) {
        ServerSocket serverSocket;
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен. Ожидание подключения клиентов...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Подключился новый клиент: " + clientSocket);

                executorService.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }


}
