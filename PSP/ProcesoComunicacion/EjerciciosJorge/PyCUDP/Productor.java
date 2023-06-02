package PyCUDP;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;

public class Productor extends Thread {

	static String line;

	public Productor(String line) {
		// TODO Esbozo de constructor generado automáticamente
		this.line = line;
	}

	@Override
	public void run() {
		try {
			byte[]buffer = readToBuffer(line);
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	public static byte[] readToBuffer(String linea) throws IOException {
		try (FileInputStream fileInputStream = new FileInputStream(linea);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			byte[] data = new byte[4096];
			int bytesRead;
			while ((bytesRead = fileInputStream.read(data)) != -1) {
				byteArrayOutputStream.write(data, 0, bytesRead);
			}
			return byteArrayOutputStream.toByteArray();
		}
	}
}
