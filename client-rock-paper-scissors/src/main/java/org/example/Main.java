package org.example;

import com.fazecast.jSerialComm.SerialPort;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SerialCommunicator communicator = new SerialCommunicator();

        // Отримуємо всі доступні порти
        SerialPort[] ports = SerialPort.getCommPorts();
        System.out.println("Доступні порти:");
        for (int i = 0; i < ports.length; i++) {
            System.out.println(i + 1 + ": " + ports[i].getSystemPortName());
        }

        // Вибір порту користувачем
        System.out.print("Виберіть порт (введіть номер): ");
        Scanner scanner = new Scanner(System.in);
        int chosenPort = scanner.nextInt() - 1;
        scanner.nextLine(); // Щоб уникнути проблем із наступним зчитуванням

        if (chosenPort < 0 || chosenPort >= ports.length) {
            System.out.println("Невірний номер порту. Вихід.");
            return;
        }

        String portName = ports[chosenPort].getSystemPortName();

        // Відкриття порту
        if (!communicator.openPort(portName, 9600)) {
            return; // Вихід, якщо не вдалося відкрити порт
        }

        // Створюємо окремий потік для читання даних від Arduino
        Thread readThread = new Thread(() -> {
            while (true) {
                if (communicator.hasAvailableData()) {
                    String response = communicator.readMessage();
                    if (response != null) {
                        System.out.println("Відповідь від Arduino: " + response);
                    }
                }
                try {
                    Thread.sleep(100); // невелика затримка
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        readThread.start(); // запускаємо потік для читання даних

        // Введення тексту для відправлення на Arduino
        System.out.println("Введіть текст для відправлення на Arduino (або 'exit' для завершення):");
        while (true) {
            System.out.print("> ");
            String message = scanner.nextLine();

            // Вихід з програми, якщо користувач вводить 'exit'
            if (message.equalsIgnoreCase("exit")) {
                break;
            }

            // Надсилаємо дані до Arduino
            communicator.sendMessage(message);
        }

        // Закриття порту і завершення програми
        communicator.closePort();
    }
}