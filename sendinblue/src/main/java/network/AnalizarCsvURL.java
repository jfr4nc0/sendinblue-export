package network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;

public class AnalizarCsvURL 
{
	public static void descargarCSV(String fileURL, String saveDir) throws IOException {

		URL url = new URL(fileURL);
		String fecha = LocalDate.now().toString();
		Path path = Paths.get(saveDir,"EXPORT_Contactos_"+fecha +".csv");
		
		CSVFormat.Builder csvFormat = CSVFormat.Builder.create()
				.setDelimiter(';')
				.setHeader().setSkipHeaderRecord(true)
				.setIgnoreHeaderCase(true);
		
		CSVFormat csvFormat2 = csvFormat.build();
		
		final Reader reader = new InputStreamReader(new BOMInputStream(url.openStream()), "UTF-8");
		final CSVParser parser = new CSVParser(reader, csvFormat2);
		try {
		    for (final CSVRecord record : parser) {
//		    	String email = record.get("EMAIL");
//				String addedTime = record.get("ADDED_TIME");
//				String modTime = record.get("MODIFIED_TIME");

				BufferedWriter writer = Files.newBufferedWriter( path , StandardCharsets.UTF_8 ) ;
		        CSVPrinter printer = new CSVPrinter( writer , csvFormat2 );
				
		        printer.printRecords( parser.getRecords() );
				printer.close();
		    }
		} finally {
		    parser.close();
		    reader.close();
		}		
		
		
	}
}
