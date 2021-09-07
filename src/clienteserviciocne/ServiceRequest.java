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
        String postURL = "https://consultahn2021.000webhostapp.com/consultar/getdatosservice";

        URL url = new URL(postURL);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);

        Map<String, String> arguments = new HashMap<>();
        arguments.put("identidad", identidad);
        arguments.put("por", solicitante); // This is a fake password obviously
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

        JSONObject jsonObject = new JSONObject(r);

//        
        return jsonObject;
    }

    public String getDataFromService2(String identidad, String solicitante) throws Exception {
        String postURL = "https://consultahn2021.000webhostapp.com/consultar/getdatosservice";

//        URL url = new URL("https://httpbin.org/post");
//        Map params = new LinkedHashMap<>();
//        params.put("identidad", identidad);
//        params.put("por", solicitante);
//
//        StringBuilder postData = new StringBuilder();
//        for (Map.Entry param : params.entrySet()) {
//            if (postData.length() != 0) {
//                postData.append('&');
//            }
//            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
//            postData.append('=');
//            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
//        }
//        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
//        conn.setDoOutput(true);
//        conn.getOutputStream().write(postDataBytes);
//        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//        StringBuilder sb = new StringBuilder();
//        for (int c; (c = in.read()) >= 0;) {
//            sb.append((char) c);
//        }
//
//        String response = sb.toString();
//        System.out.println(response);
//        JSONObject myResponse = new JSONObject(response.toString());
//        System.out.println("result after Reading JSON Response");
//        System.out.println("origin- " + myResponse.getString("origin"));
//        System.out.println("url- " + myResponse.getString("url"));
//        JSONObject form_data = myResponse.getJSONObject("form");
//        System.out.println("CODE- " + form_data.getString("CODE"));
//        System.out.println("email- " + form_data.getString("email"));
//        System.out.println("message- " + form_data.getString("message"));
//        System.out.println("name" + form_data.getString("name"));
        return "";
    }

}
