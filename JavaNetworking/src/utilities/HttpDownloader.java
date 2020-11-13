package utilities;
 
import java.io.IOException;
 
public class HttpDownloader {
 
    public static void main(String[] args) {
    	
        String fileURL = "https://internetpasoapaso.com/wp-content/uploads/archivos-con-formato-ASF.jpg";
        String saveDir = "F:/DAM2";
        
        try {
        	
            HttpDownloadUtility.downloadFile(fileURL, saveDir);
            
        } catch (IOException ex) {
        	
            ex.printStackTrace();
        }
    }
}