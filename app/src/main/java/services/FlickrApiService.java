package services;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by utsav on 24/05/2017.
 */

public class FlickrApiService extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {

        String result = null;
        int resCode;
        InputStream inputStream;
        String error = null;

        try {

            URL imageUrl = new URL(params[0]);

            HttpsURLConnection httpsConn = (HttpsURLConnection)imageUrl.openConnection();
            httpsConn.setAllowUserInteraction(false);
            httpsConn.setInstanceFollowRedirects(true);
            httpsConn.setRequestMethod("GET");
            httpsConn.connect();
            resCode = httpsConn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpsConn.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);

                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }

                inputStream.close();
                result = stringBuilder.toString();
                result = result.replace("(", "");
                result = result.replace(")", "");

            } else {
                error += resCode;
                Log.i("error",error);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}