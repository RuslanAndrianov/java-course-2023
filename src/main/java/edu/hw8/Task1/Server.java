package edu.hw8.Task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {

    public static final Map<String, String> RESPONSES = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверен(а) — твоя победа не за горами",
        "глупый", "А я тебе говорил(а), что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

    public static final int MAX_CLIENTS = 2;

    private static final int PORT = 8080;

    public static final ExecutorService executorService = Executors.newFixedThreadPool(MAX_CLIENTS);

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен. Ожидание подключения клиентов...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Подключился новый клиент: " + clientSocket);
                executorService.execute(new ClientHandler(clientSocket));
                // Можно добавить логику для выключения сервера
            }
        } catch (IOException e) {
            System.err.println("Ошибка запуска сервера!");
        } finally {
            executorService.shutdown();
        }
    }
}
