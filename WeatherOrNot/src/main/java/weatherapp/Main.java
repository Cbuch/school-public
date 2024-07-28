package weatherapp;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WeatherAPIService weatherService = new WeatherAPIService();
        Scanner scanner = new Scanner(System.in);
        String city;
        String state;

        // Input validation for city
        do {
            System.out.print("Enter city name: ");
            city = scanner.nextLine().trim();
            if (city.isEmpty() || !city.matches("[a-zA-Z ]+")) {
                System.out.println("Invalid city name. Please enter only letters.");
            }
        } while (city.isEmpty() || !city.matches("[a-zA-Z ]+"));

        // Input validation for state
        do {
            System.out.print("Enter state abbreviation (e.g., NY for New York): ");
            state = scanner.nextLine().trim();
            if (state.isEmpty() || !state.matches("[a-zA-Z]{2}")) {
                System.out.println("Invalid state abbreviation. Please enter exactly two letters.");
            }
        } while (state.isEmpty() || !state.matches("[a-zA-Z]{2}"));

        // Collect and display weather data
        String weatherData = weatherService.getWeather(city, state);
        System.out.println(weatherData);
    }
}
