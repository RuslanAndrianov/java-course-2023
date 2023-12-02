package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private static final Map<String, String> RESPONSES = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

    private static final String DEFAULT_RESPONSE = "К сожалению, не могу ничего придумать!";

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            String clientInput = in.readLine();
            System.out.println(clientInput);
            if (RESPONSES.containsKey(clientInput)) {
                out.write("Держите остроту: " + RESPONSES.get(clientInput) + "\n");
            }
            else {
                out.write(DEFAULT_RESPONSE);
            }
            out.flush();
        } catch (IOException e) {
            System.err.println("Ошибка чтения или записи!");
            e.printStackTrace();
        }
    }
}
