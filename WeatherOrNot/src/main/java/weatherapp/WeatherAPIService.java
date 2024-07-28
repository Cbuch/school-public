package weatherapp;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherAPIService {

    private static final String API_KEY = "9c9685fbfc29db9503c15ac6c6b82d5b"; // Replace with your actual API key

    public String getWeather(String city, String state) {
        try {
            // URL encode city and state
            String encodedCity = URLEncoder.encode(city, "UTF-8");
            String encodedState = URLEncoder.encode(state, "UTF-8");

            // Build URL
            String apiUrl = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s,%s,US&appid=%s&units=imperial",
                    encodedCity, encodedState, API_KEY);
            StringBuilder result = new StringBuilder();

            // Create URL object
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
            in.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(result.toString());
            String description = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");
            double temp = jsonResponse.getJSONObject("main").getDouble("temp");

            // Format description
            String formattedDescription;
            switch (description) {
                case "clear sky":
                    formattedDescription = "the skies are clear";
                    break;
                case "few clouds":
                    formattedDescription = "there are a few clouds";
                    break;
                case "scattered clouds":
                    formattedDescription = "the clouds are scattered";
                    break;
                case "broken clouds":
                    formattedDescription = "the clouds are broken";
                    break;
                case "overcast clouds":
                    formattedDescription = "the skies are overcast";
                    break;
                default:
                    formattedDescription = "the skies are " + description;
                    break;
            }

            // Return formatted string
            return String.format("%s and the temperature is %.1f°F.", formattedDescription, temp);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Unable to retrieve weather data. Please check the city and state names or try again later.";
        }
    }
}
