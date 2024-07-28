package weatherapp;

public class ClientController {
    private WeatherService weatherService;

    public ClientController() {
        weatherService = new WeatherService();
    }

    public void getWeatherData() {
        // Example: Fetching weather data for New York, NY
        String weatherData = weatherService.collectWeatherData("New York", "NY");
        System.out.println(".");
        System.out.println("..");
        System.out.println("...");
        System.out.println("Weather data collected.");
        System.out.println();
        displayWeatherData(weatherData);
    }

    private void displayWeatherData(String data) {
        System.out.println("Displaying weather data:");
        System.out.println();
        System.out.println(data);
        System.out.println();
    }
}
