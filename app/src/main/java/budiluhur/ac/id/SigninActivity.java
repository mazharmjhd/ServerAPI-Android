package budiluhur.ac.id;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static java.lang.System.in;

public class SigninActivity  extends AsyncTask<String, Void, String> {

    private TextView status, role;
    private Context context;
    private int byGetOrPost = 0;

    public SigninActivity(Context context, TextView statusField, TextView roleField, int flag) {
        this.context = context;
        this.status = statusField;
        this.role = roleField;
        byGetOrPost = flag;
    }

    protected void onPreExecute() {

    }
    protected String doInBackground (String...arg0){
        String hasil = "";
            if (byGetOrPost == 0) {
                //GET METHOD
                try {
                    String username = (String) arg0[0];
                    String password = (String) arg0[1];

                    String link = "http://localhost/serverGet.php?username=" + username + "%password=" + password;

                    HttpClient client = new DefaultHttpClient ();
                    HttpGet request = new HttpGet ();
                    request.setURI (new URI (link));
                    HttpResponse response = client.execute (request);
                    BufferedReader in = new BufferedReader (new InputStreamReader (response.getEntity().getContent ()));

                    StringBuffer sb = new StringBuffer ("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append (line);
                        break;
                    }
                    in.close ();
                    hasil = sb.toString ();
                    return hasil;
                } catch (Exception e) {
                    return new String ("Exception: " + e.getMessage ());
                }
            }else{
                    try {
                        String username = (String) arg0[0];
                        String password = (String) arg0[1];

                        String link = "http://localhost/serverPost.php";
                        String data = URLEncoder.encode("username" , "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                        data += "&" + URLEncoder.encode("password" , "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                        URL url = new URL(link);
                        URLConnection conn = url.openConnection();
                        conn.setDoOutput(true);
                        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                        wr.write(data);
                        wr.flush();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        StringBuilder sb = new StringBuilder();
                        String line = null;

                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                            break;
                        }
                        hasil = sb.toString();
                        return hasil;
                    } catch (Exception e) {
                        return new String("Exception : " + e.getMessage());
                    }
                }
            }
        }


