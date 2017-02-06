package edu.rosehulman.schaffll.weathertowear.Weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kiana on 2/5/17.
 */

public class WeatherHttpClient {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?zip=";
    private static String END_URL = ",us&appid=4a476f2a27660f270fc55157a2b4247c";
//    private static String IMG_URL = "http://openweathermap.org/img/w/";


    public String getWeatherData(String zipcode) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) (new URL(BASE_URL + zipcode + END_URL)).openConnection();
//            connection = (HttpURLConnection) (new URL("http://api.openweathermap.org/data/2.5/weather?zip=47803,us&appid=4a476f2a27660f270fc55157a2b4247c")).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //  Read input
            StringBuffer buffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line + "\r\n");
            }
            inputStream.close();
            connection.disconnect();
            return buffer.toString();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (Throwable t) {
            }
            try {
                connection.disconnect();
            } catch (Throwable t) {

            }
        }
        return null;
    }
}