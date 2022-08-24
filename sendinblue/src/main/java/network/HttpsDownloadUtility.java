package network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class HttpsDownloadUtility 
{
	private static final int BUFFER_SIZE = 4096;
	
	public static void downloadFile(String fileURL, String saveDir) throws IOException
	{
		URL url = new URL(fileURL);
		HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
		int responseCode = httpsConn.getResponseCode();
		
		// Chequear si la respuesta esta ok
		if (responseCode == HttpsURLConnection.HTTP_OK) {
			String fileName = "";
			String disposition = httpsConn.getHeaderField("Content-Disposition");
			String contentType = httpsConn.getContentType();
			int contentLength = httpsConn.getContentLength();
			
			if (disposition != null) {
				// Extraer nombre del archivo del encabezado
				int index = disposition.indexOf("filename=");
				if (index > 0)
				{
					fileName = disposition.substring(index + 10, disposition.length() - 1);
				} else {
					// Extraer nombre de la URL
					fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
				}
				
				System.out.println("Content-Type = " + contentType);
				System.out.println("Content-Disposition = " + disposition);
				System.out.println("Content-Length = " + contentLength);
				System.out.println("File-Name = " + fileName);
				
				// Abrir input stream de la conexion URL
				InputStream inputStream = httpsConn.getInputStream();
				String saveFilePath = saveDir + File.separator + fileName;
				
				// Abrir output stram para guardar dentro del archivo
				FileOutputStream outputStream = new FileOutputStream(saveFilePath);
				
				int bytesRead = -1;
				byte[] buffer = new byte[BUFFER_SIZE];
				while ((bytesRead = inputStream.read(buffer)) != -1)
				{
					outputStream.write(buffer,0,bytesRead);
				}
				
				outputStream.close();
				inputStream.close();
				
				System.out.println("File downloaded");
			} else {
				System.out.println("No file to download. Server replied https code: " + responseCode);
			}
			
			httpsConn.disconnect();
		}
	}
}
