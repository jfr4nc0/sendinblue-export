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
import network.HttpsDownloadUtility;




@SpringBootApplication
public class SendinblueApplication {
	
	private static void DescargaURL(String archivo) {
		String fileURL = archivo;
		String saveDir = "Z:\\SendinBlue";
		try {
			AnalizarCsvURL.descargarCSV(fileURL);
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
//            ContactsApi apiContactos = new ContactsApi();
//            List<String> exportAttributes = new ArrayList<String>();
//            exportAttributes.add("EMAIL");
//            RequestContactExportCustomContactFilter requestContactExportCustomContactFilter = new RequestContactExportCustomContactFilter();
//            requestContactExportCustomContactFilter.setActionForContacts(RequestContactExportCustomContactFilter.ActionForContactsEnum.ALLCONTACTS);
//            requestContactExportCustomContactFilter.setListId(522L);
//            RequestContactExport requestContactExport = new RequestContactExport();
//            requestContactExport.setExportAttributes(exportAttributes);
////            requestContactExport.setNotifyUrl("http://127.0.0.1:5500");
//            requestContactExport.setCustomContactFilter(requestContactExportCustomContactFilter);
//            CreatedProcessId processId = apiContactos.requestContactExport(requestContactExport);
//            Thread.sleep(5*60*1000);
//            Long id = processId.getProcessId();
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