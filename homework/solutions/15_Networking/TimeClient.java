package nyc.c4q.jrod.past.nyc.c4q.jrod.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TimeClient {
  public static void main(String[] args) throws IOException, InterruptedException {
    Socket client = new Socket("localhost", Server.PORT);

    // send UTC offset request to server
    PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
    int utcOffset = readUTCOffsetInput();
    pw.println(utcOffset);

    // receive time response from server
    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
    String iso8601DateString = br.readLine();
    System.out.println(iso8601DateString);

    client.close();
  }

  private static int readUTCOffsetInput() {
    Scanner input = new Scanner(System.in);
    int utcOffset;
    while(true) {
      System.out.print("Enter UTF offset: ");
      if(input.hasNextInt()) {
        utcOffset = input.nextInt();
        break;
      }
    }
    return utcOffset;
  }
}
