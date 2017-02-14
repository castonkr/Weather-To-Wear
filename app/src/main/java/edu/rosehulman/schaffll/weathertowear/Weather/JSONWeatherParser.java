package edu.rosehulman.schaffll.weathertowear.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kiana on 2/5/17.
 */

public class JSONWeatherParser {

    public static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();

        // Make JSON Object
        JSONObject jsonObject = new JSONObject(data);

        // Extract info
        Location location = new Location();

        JSONObject sysObj = getObject("sys", jsonObject);
        location.setCity(getString("name", jsonObject));
        location.setCountry(getString("country", sysObj));
        weather.location = location;

        // Get Weather info (array)
        JSONArray jsonArray = jsonObject.getJSONArray("weather");

        // Conditions
        JSONObject JSONWeather = jsonArray.getJSONObject(0);
        weather.currentCondition.setIcon(getString("icon", JSONWeather));
        weather.currentCondition.setDescription(getString("description", JSONWeather));
        weather.currentCondition.setCondition(getString("main", JSONWeather));

        // Temperature
        JSONObject mainObj = getObject("main", jsonObject);
        weather.temperature.setTemp(getFloat("temp", mainObj));

        return weather;
    }

    private static JSONObject getObject(String name, JSONObject jsonObject)  throws JSONException {
        JSONObject subObject = jsonObject.getJSONObject(name);
        return subObject;
    }

    private static String getString(String name, JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(name);
    }

    private static float  getFloat(String name, JSONObject jsonObject) throws JSONException {
        return (float) jsonObject.getDouble(name);
    }

    private static int  getInt(String name, JSONObject jsonObject) throws JSONException {
        return jsonObject.getInt(name);
    }
}