package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

@SuppressWarnings({"RegexpSinglelineJava", "UncommentedMain"})
public class Client extends Thread {

    private static final String SERVER_ADDRESS = "localhost";

    private static final int SERVER_PORT = 8080;

    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try {
            // Установление соединения с сервером
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Соединение установлено.");

            // Получение входного и выходного потоков
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // Создание сканнера для чтения пользовательского ввода
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Ожидание пользовательского ввода
                System.out.print("Введите сообщение (или 'exit' для выхода): ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                // Отправка сообщения на сервер
                byte[] messageBytes = message.getBytes();
                outputStream.write(messageBytes);

                // Получение ответа от сервера
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = inputStream.read(buffer);

                if (bytesRead != -1) {
                    String response = new String(buffer, 0, bytesRead);
                    System.out.println("Ответ от сервера: " + response);
                }
            }

            // Закрытие соединения
            socket.close();
            System.out.println("Соединение закрыто.");
        } catch (IOException e) {
            System.err.println("Не удалось связаться с сервером!");
        }
    }
}
