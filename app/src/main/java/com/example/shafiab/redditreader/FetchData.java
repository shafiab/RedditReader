package com.example.shafiab.redditreader;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shafiab on 9/17/14.
 */
public class FetchData extends AsyncTask<Void, Void, List<String[]> >{
    final String HOT25_URL = "http://www.reddit.com/hot/.json?sort=new&count=2";
    List<String[]>  myList;

    @Override
    public List<String[]> doInBackground(Void... voids)
    {
        String data = readFromWeb();
        try {
            myList = getDataFromJSON(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return myList;
    }

    @Override
    protected void onPostExecute(List<String[]> strings) {
        super.onPostExecute(strings);
    }

    public String readFromWeb()
    {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            URL url = new URL(HOT25_URL);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                forecastJsonStr = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                forecastJsonStr = null;
            }
            forecastJsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            forecastJsonStr = null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }

        return forecastJsonStr;

    }

    private  List<String[]>  getDataFromJSON(String data) throws JSONException
    {
        JSONObject mainJSON = new JSONObject(data);

        JSONObject dataJSON = mainJSON.getJSONObject("data");
        JSONArray hotTopics = dataJSON.getJSONArray("children");

        String[] title = new String[hotTopics.length()];
        String[] url = new String[hotTopics.length()];
        String[] thumbnail = new String[hotTopics.length()];

        for (int counter=0;counter<hotTopics.length();counter++)
        {
            JSONObject topic = hotTopics.getJSONObject(counter).getJSONObject("data");
            title[counter] = topic.getString("title");
            url[counter] = topic.getString("url");
            thumbnail[counter] = topic.getString("thumbnail");
        }
        List<String[]> myList = new LinkedList<String[]>();

        myList.add(title);
        myList.add(url);
        myList.add(thumbnail);

        return  myList;
    }

}
