package nyc.c4q.jrod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
  public static void main(String[] args) throws IOException, InterruptedException {
    Socket client = new Socket("localhost", Server.PORT);

    PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
    pw.println("PING");

    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
    System.out.println("CLIENT received: " + br.readLine());

    client.close();
  }
}
