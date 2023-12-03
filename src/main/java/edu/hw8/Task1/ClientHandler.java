package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import static edu.hw8.Task1.Server.RESPONSES;

public record ClientHandler(Socket clientSocket) implements Runnable {

    @Override
    public void run() {
        try (InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream()) {

            while (true) {
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                if (bytesRead == -1) {
                    System.out.println("Клиент отключился: " + clientSocket);
                    break;
                }
                String message = new String(buffer, 0, bytesRead);
                String response = getResponse(message);
                outputStream.write(response.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            System.err.println("Ошибка обработки запроса!");
        }
    }

    private String getResponse(String message) {
        for (String keyword : RESPONSES.keySet()) {
            if (keyword.equalsIgnoreCase(message.trim())) {
                return RESPONSES.get(keyword);
            }
        }
        return "Извините, не могу найти цитату по такому ключевому слову.";
    }
}
