package client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TimeClient {

    private Socket socket;
    private Scanner scan;

    public void connect(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        scan = new Scanner(socket.getInputStream());
    }

    //Will not work from a GUI, Since it blocks.
    public String recieve() {
        return scan.nextLine();
    }

    public static void main(String[] args) throws IOException {
        TimeClient client = new TimeClient();
        client.connect("localhost", 1235);
        System.out.println(client.recieve());

    }
}
