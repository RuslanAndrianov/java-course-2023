package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public record ClientHandler(Socket clientSocket) implements Runnable {

    @Override
    public void run() {
        try (
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream()
        ) {
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
            e.printStackTrace();
        }
    }

    private String getResponse(String message) {
        String response;

        switch (message) {
            case "личности":
                response = "Не переходи на личности там, где их нет";
                break;
            case "оскорбления":
                response =
                    "Если твои противники перешли на личные оскорбления, будь уверен(а) — твоя победа не за горами";
                break;
            case "глупый":
                response =
                    "А я тебе говорил(а), что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.";
                break;
            case "интеллект":
                response = "Чем ниже интеллект, тем громче оскорбления";
                break;
            default:
                response = "Я не понимаю";
                break;
        }

        return response;
    }
}
