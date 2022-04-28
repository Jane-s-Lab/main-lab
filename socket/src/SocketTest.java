import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketTest {

	private static final int DEFAULT_PORT = 80;
	private static final String SAMPLE = "{\"test\": 100}";

	@SuppressWarnings("InfiniteLoopStatement")
	public static void main(String[] args) throws IOException {
		// TODO: Leader/Followers, Worker Thread
		try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
			while (true) {
				Socket socket = serverSocket.accept();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				String line = null;
				do {
					line = bufferedReader.readLine();
					System.out.println(line);
				} while (line != null && !line.equals(""));

				DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
				byte[] body = SAMPLE.getBytes(StandardCharsets.UTF_8);
				dataOutputStream.write(body);
				dataOutputStream.flush();
				socket.close();
			}
		}
	}
}
