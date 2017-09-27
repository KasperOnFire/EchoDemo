package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class EchoServer {

    private static int PORT = 1234;
    private static String IP = "localhost";
    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            IP = args[0];
            PORT = Integer.parseInt(args[1]);
        }
        serverSocket = new ServerSocket(); // remember to bind
        serverSocket.bind(new InetSocketAddress(IP, PORT));
        System.out.println("Waiting for connection...");
        while (true) {
            Socket socket = serverSocket.accept(); // IMPORTANT: BLOCKING CALL!
            System.out.println("Connection made!");
            handleClient(socket);
        }
    }

    public static void handleClient(Socket socket) throws IOException {
        Scanner scan = new Scanner(socket.getInputStream());
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); //DONT FORGET AUTOFLUSH
        //IMPORTANT: BLOCKING
        pw.println("command and message?");
        String input;
        String[] split;
        String cmd;
        String msg;
        while (true) {
            input = scan.nextLine();
            if (input.toLowerCase().equals("exit") || input.toLowerCase().equals("quit")) {
                pw.println("Disconnected!");
                break;
            }
            split = input.split("#");
            cmd = split[0];
            msg = split[1];
            pw.println(parseCommand(cmd, msg));
        }

    }

    private static String parseCommand(String cmd, String msg) {
        HashMap<String, String> translations = new HashMap<>();
        translations.put("hund", "dog");
        translations.put("kat", "cat");
        translations.put("dog", "hund");
        translations.put("cat", "kat");
        switch (cmd.toUpperCase()) {
            case "UPPER":
                return msg.toUpperCase();
            case "LOWER":
                return msg.toLowerCase();
            case "REVERSE":
                return new StringBuilder(msg).reverse().toString();
            case "TRANSLATE":
                return translations.get(msg);
            default:
                break;
        }
        return null;
    }

    
}
