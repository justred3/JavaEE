package day07_04_socket;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerService {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(6789);
		System.out.println("服务器等待中.........");
		Socket socket = server.accept();

		FileInputStream fis = new FileInputStream(
				"../../day1_html/01-hello.html");
		byte[] bs = new byte[1024];
		int b = 0;
		// 创建输出流
		OutputStream os = socket.getOutputStream();

		while ((b = fis.read(bs)) != -1) {
			os.write(bs, 0, b);
		}
		fis.close();
		os.close();
	}
}
