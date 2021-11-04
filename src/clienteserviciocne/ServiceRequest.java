/*

 * @Nombre    : ServiceRequest
 * @Author    : Erick Rodriguez
 * @Copyright : Erick Rodriguez
 * @Creado el : 06-sep-2021, 07:21:59 PM
 */
package clienteserviciocne;

import java.io.Reader;
import java.net.URLEncoder;
import java.util.Map;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.StringJoiner;

import org.json.JSONObject;

/**
 *
 * @author erams
 */
public class ServiceRequest {

    //public static HttpURLConnection con;

    public JSONObject getDataFromService(String identidad, String solicitante) throws Exception {
        String postURL = "https://consultahn2021.000webhostapp.com/consultar/getinfoservice";
        //String postURL = "http://cne.local/consultar/getinfoservice";

        URL url = new URL(postURL);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);

        Map<String, String> arguments = new HashMap<>();
        arguments.put("identidad", identidad);
        arguments.put("solicitante", solicitante); // This is a fake password obviously
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> entry : arguments.entrySet()) {
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        http.connect();
        try (OutputStream os = http.getOutputStream()) {
            os.write(out);
        }

        InputStream inputStream = http.getInputStream();
        // Read the InputStream into a Reader
        Reader reader = new InputStreamReader(inputStream);

        // Instantiate a StringBuilder to save the result
        StringBuilder result = new StringBuilder();

        // Read each byte and convert into a char, adding to the StringBuilder
        for (int data = reader.read(); data != -1; data = reader.read()) {
            result.append((char) data);
        }

        // Convert StringBuilder to String
        System.out.println(result.toString());

        String r = result.toString().substring(1);

        //Convertir a un objeto JSON 
        //Usar libreria java-json.jar
        JSONObject jsonObject = new JSONObject(r);

//        
        return jsonObject;
    }

}
