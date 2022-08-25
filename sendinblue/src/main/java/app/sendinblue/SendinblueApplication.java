package app.sendinblue;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import sendinblue.*;
import sendinblue.auth.*;
import sibModel.*;
import sibApi.*;
import network.AnalizarCsvURL;




@SpringBootApplication
public class SendinblueApplication {
	
	private static void DescargaURL(String archivo) {
		String fileURL = archivo;
		String saveDir = "Z:\\SendinBlue";
		try {
			AnalizarCsvURL.descargarCSV(fileURL,saveDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        
        // Configure API key authorization: api-key
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey("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");

        try {
        	
        	// Solicita el EXPORT a SendinBlue
//            ContactsApi apiContactos = new ContactsApi();
//            List<String> exportAttributes = new ArrayList<String>();
//            exportAttributes.add("EMAIL");
//            RequestContactExportCustomContactFilter requestContactExportCustomContactFilter = new RequestContactExportCustomContactFilter();
//            requestContactExportCustomContactFilter.setActionForContacts(RequestContactExportCustomContactFilter.ActionForContactsEnum.ALLCONTACTS);
//            requestContactExportCustomContactFilter.setListId(522L);
//            RequestContactExport requestContactExport = new RequestContactExport();
//            requestContactExport.setExportAttributes(exportAttributes);
//            requestContactExport.setCustomContactFilter(requestContactExportCustomContactFilter);
//            CreatedProcessId processId = apiContactos.requestContactExport(requestContactExport);
        	// Espera 5 min a que generen el archivo .csv en el servidor
//            Thread.sleep(5*60*1000);
//            Long id = processId.getProcessId();
        	// Toma los datos del proceso y la url
            ProcessApi apiProceso = new ProcessApi();
            GetProcess res = apiProceso.getProcess(1251l);
            String url = res.getExportUrl();
            System.out.println(url);
            System.out.println(res.toString());
            DescargaURL(url);
            
        } catch (Exception e) {
            System.out.println("Exception occurred:- " + e.getMessage());
        }
    }
}