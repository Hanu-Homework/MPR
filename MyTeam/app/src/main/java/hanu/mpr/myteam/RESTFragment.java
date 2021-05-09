package hanu.mpr.myteam;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class RESTFragment extends Fragment {

    private TextView tvTemp, tvDescription;

    /**
     * run code in different thread
     * advised by google
     * ideal to run code in a thread different from the main thread -> dont want to delay the UI
     * @param String datatype of data sent to this task as input (e.g. URL)
     * @param Void used to show progress of the task, we do not do it here -> Void
     * @param String datatype of data returned by this task (e.g. JSON = weather info)
     */
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream is = urlConnection.getInputStream();

                Scanner sc = new Scanner(is);
                StringBuilder result = new StringBuilder();
                String line;
                while(sc.hasNextLine()) {
                    line = sc.nextLine();
                    result.append(line);
                }

                return result.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        // where you may interact with the UI
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result == null) {
                Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
                return;
            }

            try {
                JSONObject root = new JSONObject(result);

                // description
                JSONArray weather = root.getJSONArray("weather");
                JSONObject weather0 = weather.getJSONObject(0);
                String description = weather0.getString("description");

                tvDescription.setText(description);

                // temp
                JSONObject main = root.getJSONObject("main");
                int tempMin = main.getInt("temp_min");
                int tempMax = main.getInt("temp_max");

                tvTemp.setText(tempMin + "-" + tempMax);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_r_e_s_t, container, false);

        // refs
        tvTemp = view.findViewById(R.id.tvTemp);
        tvDescription = view.findViewById(R.id.tvDescription);

        // load weather info
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Hanoi&appid=7e8bd3f1a0b42a01e12fa8fd0033fe34&units=metric";
        DownloadTask task = new DownloadTask();
        task.execute(url);

        return view;
    }
}