package nyc.c4q.jrod.past.nyc.c4q.jrod.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeServer {
  public static final int PORT = 5228;
  public static final int HOURS_TO_MILLISECONDS = 60 * 60 * 1000;

  public static void main(String[] args) throws IOException {
    final ServerSocket ss = new ServerSocket(PORT);

    while (!ss.isClosed()) {
      Socket inboundConnection = ss.accept();

      try {
        // read UTF offset from client
        BufferedReader in = new BufferedReader(new InputStreamReader(inboundConnection.getInputStream()));
        int utcOffset = Integer.parseInt(in.readLine());
        System.out.println("SERVER received: " + utcOffset);

        // write datetime string to client
        PrintWriter out = new PrintWriter(inboundConnection.getOutputStream(), true);
        out.println(convertUTCOffsetToISO8601(utcOffset));
      }
      catch (NumberFormatException e) {
        // got invalid UTC offset from client, ignore
        System.err.println("invalid UTC offset from client!");
      }
      finally {
        inboundConnection.close();
      }
    }
  }

  // http://www.timeanddate.com/worldclock/usa/new-york
  // UTC -5 = Eastern Standard Time
  // UTC -4 = Eastern Daylight Time (until Nov 1 2015)
  // UTC -8 = Pacific Standard Time
  // UTC -7 = Pacific Daylight Time (until Nov 1 2015)

  // UTC offset is measured in hours
  private static String convertUTCOffsetToISO8601(int utcOffset) {
    // update timezone
    TimeZone utc = TimeZone.getTimeZone("UTC");
    utc.setRawOffset(utcOffset * HOURS_TO_MILLISECONDS);

    // apply timezone to datetime formatter
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    dateFormat.setTimeZone(utc);

    // apply datetime formatter to current date
    return dateFormat.format(new Date());  // now
  }
}
