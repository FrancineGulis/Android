import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Francine on 16/10/2017.
 */

public class HttpHandler {


    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            //Faz o request
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //Pega a resposta
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToStrinng(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    private String convertStreamToStrinng(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStream(is));
        StringBuilder sb = new StringBuilder();

        try {
            while ((line = reader.readline()) != null) {
                sb.append(line).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}