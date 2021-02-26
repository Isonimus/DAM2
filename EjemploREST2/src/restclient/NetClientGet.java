package restclient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetClientGet {
    public static void main(String[] args) {
        try {

            URL url = new URL("http://localhost:8080/EjemploREST2/rest/miservicio/hola");//LA URL A LA QUE PIDES
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //ABRIR LA CONEXIÓN
            conn.setRequestMethod("GET");	//EL TIPO DE PETICIÓN
            conn.setRequestProperty("Accept", "application/json"); //EL TIPO DE CONTENIDO A ACEPTAR
            if (conn.getResponseCode() != 200) { //SI NO ES EL CODE OK
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in); //CREAR BUFFEREDREADER DESDE EL STREAM
            String output;
            while ((output = br.readLine()) != null) { //MIENTRAS QUEDE OUTPUT POR MOSTRAR
                System.out.println(output);
            }
            conn.disconnect(); //CERRAR LA CONEXIÓN

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
    }
}