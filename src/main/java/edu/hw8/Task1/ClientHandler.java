package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public record ClientHandler(Socket clientSocket) implements Runnable {

    private static final Map<String, String> RESPONSES = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверен(а) — твоя победа не за горами",
        "глупый", "А я тебе говорил(а), что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

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
        for (String keyword : RESPONSES.keySet()) {
            if (keyword.equalsIgnoreCase(message.trim())) {
                return RESPONSES.get(keyword);
            }
        }
        return "Извините, не могу найти цитату по такому ключевому слову.";
    }
}
