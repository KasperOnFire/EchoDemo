package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    private Socket socket;
    private Scanner scan;
    private PrintWriter pw;

    public void connect(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        scan = new Scanner(socket.getInputStream());
        pw = new PrintWriter(socket.getOutputStream(), true);

    }

    public void send(String msg) {
        pw.println(msg);
    }

    //Will not work from a GUI, Since it blocks
    public String recieve() {
        return scan.nextLine();
    }

    public static void main(String[] args) throws IOException {
        EchoClient client = new EchoClient();
        client.connect("localhost", 1234);
        client.send("testMessage");
        System.out.println(client.recieve());
        client.send("STOP");
        System.out.println("Done");
    }
}
