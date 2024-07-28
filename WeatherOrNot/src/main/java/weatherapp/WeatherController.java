package weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WeatherController {

    @FXML
    private TextField cityField;

    @FXML
    private TextField stateField;

    @FXML
    private Label weatherResult;

    private WeatherAPIService weatherService = new WeatherAPIService();

    @FXML
    private void handleGetWeather() {
        String city = cityField.getText().trim();
        String state = stateField.getText().trim();

        // Input validation
        if (!city.matches("[a-zA-Z ]+") || city.isEmpty()) {
            weatherResult.setText("Invalid city name. Please enter only letters.");
            return;
        }
        if (!state.matches("[a-zA-Z]{2}") || state.isEmpty()) {
            weatherResult.setText("Invalid state abbreviation. Please enter exactly two letters.");
            return;
        }

        // Collect and display weather data
        String weatherData = weatherService.getWeather(city, state);
        weatherResult.setText(weatherData);

        // Automatically resize the window
        Stage stage = (Stage) weatherResult.getScene().getWindow();
        stage.sizeToScene();
    }
}
