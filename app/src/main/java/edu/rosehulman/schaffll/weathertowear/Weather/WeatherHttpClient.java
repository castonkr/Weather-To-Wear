package edu.rosehulman.schaffll.weathertowear.Weather;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.rosehulman.schaffll.weathertowear.Constants;

import static edu.rosehulman.schaffll.weathertowear.Constants.API_KEY;
import static edu.rosehulman.schaffll.weathertowear.Constants.IMG_URL;
import static edu.rosehulman.schaffll.weathertowear.Constants.WEATHER_URL;

/**
 * Created by Kiana on 2/5/17.
 */

public class WeatherHttpClient {

    // Get Weather Data
    public String getWeatherData(String zipcode) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) (new URL(WEATHER_URL + zipcode + API_KEY)).openConnection();
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

    // Get Weather Image
    public byte[] getImage(String imageCode) {
        HttpURLConnection connection = null ;
        InputStream inputStream = null;
        try {

            connection = (HttpURLConnection) ( new URL(IMG_URL + imageCode + ".png")).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Let's read the response
            inputStream = connection.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            while ( inputStream.read(buffer) != -1) {
                byteArrayOutputStream.write(buffer);
            }

            return byteArrayOutputStream.toByteArray();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch(Throwable t) {

            }
            try {
                connection.disconnect();
            } catch(Throwable t) {

            }
        }

        return null;

    }
}