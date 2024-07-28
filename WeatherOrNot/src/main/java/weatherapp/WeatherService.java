package weatherapp;

public class WeatherService {
    private WeatherAPIService weatherAPIService;

    public WeatherService() {
        this.weatherAPIService = new WeatherAPIService();
    }

    // Make sure to pass both city and state parameters as required by getWeather method
    public String collectWeatherData(String city, String state) {
        return weatherAPIService.getWeather(city, state);
    }
}
