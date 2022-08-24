package network;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class AnalizarCsvURL 
{
	@SuppressWarnings("deprecation")
	public static void descargarCSV(String fileURL) throws MalformedURLException {
		URL url = new URL(fileURL);
		
		CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().withIgnoreHeaderCase();
		
		try(CSVParser csvParser = CSVParser.parse(url, StandardCharsets.UTF_8, csvFormat))
		{
			for(CSVRecord csvRecord : csvParser)
			{
				String email = csvRecord.get("EMAIL");
				String addedTime = csvRecord.get("ADDED_TIME");
				String modTime = csvRecord.get("MODIFIED_TIME");
				
				System.out.println(email+","+addedTime+","+modTime);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
