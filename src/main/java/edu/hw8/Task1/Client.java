package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 4004);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            while (true) {
                System.out.println("HINT! Для завершения работы введите команду exit\n" +
                    "Введите ключевое слово для генерации цитаты: ");
                String request = reader.readLine();

                if (request.equals("exit")) {
                    break;
                }

                out.write(request + "\n");
                out.flush();

                String response = in.readLine();
                System.out.println("\nДержите сгенерированную остроту:\n" + response + "\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка соединения с сервером!");
        }
    }
}
