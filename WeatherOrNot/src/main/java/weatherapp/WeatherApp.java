package weatherapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherApp extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    private WeatherAPIService weatherService = new WeatherAPIService();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // custom title bar
        Label title = new Label("WeatherOrNot");
        title.getStyleClass().add("header");

        Button minimizeButton = new Button("-");
        minimizeButton.getStyleClass().add("window-button");
        minimizeButton.setOnAction(e -> primaryStage.setIconified(true));

        Button expandButton = new Button("□");
        expandButton.getStyleClass().add("window-button");
        expandButton.setOnAction(e -> primaryStage.setMaximized(!primaryStage.isMaximized()));

        Button closeButton = new Button("X");
        closeButton.getStyleClass().add("window-button");
        closeButton.setOnAction(e -> primaryStage.close());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox titleBar = new HBox(title, spacer, minimizeButton, expandButton, closeButton);
        titleBar.getStyleClass().add("title-bar");
        titleBar.setAlignment(Pos.CENTER_LEFT);
        titleBar.setSpacing(10);

        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        // UI elements
        Label cityLabel = new Label("City:");
        TextField cityInput = new TextField();
        Label stateLabel = new Label("State:");
        TextField stateInput = new TextField();
        Button getWeatherButton = new Button("Get Weather");
        Label resultLabel = new Label();

        // button actions
        getWeatherButton.setOnAction(e -> {
            String city = cityInput.getText().trim();
            String state = stateInput.getText().trim();
            if (!city.isEmpty() && !state.isEmpty()) {
                String weatherData = weatherService.getWeather(city, state);
                String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mm a"));
                resultLabel.setText("Currently at " + currentTime + ", " + weatherData);

                // Automatically expand the window to fit the content
                primaryStage.sizeToScene();
            } else {
                resultLabel.setText("Please enter both city and state.");
            }
        });

        // event handler for text fields to trigger the button action on Enter key press
        cityInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                getWeatherButton.fire();
            }
        });

        stateInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                getWeatherButton.fire();
            }
        });

        // Arrange UI elements in a vertical box
        VBox vbox = new VBox(10, cityLabel, cityInput, stateLabel, stateInput, getWeatherButton, resultLabel);
        vbox.setPadding(new javafx.geometry.Insets(15));
        vbox.setAlignment(Pos.CENTER); // Center the content
        vbox.getStyleClass().add("root"); // Add CSS class

        // Arrange the layout in a border pane
        BorderPane root = new BorderPane();
        root.setTop(titleBar);
        root.setCenter(vbox);

        // Set scene and stage with a larger initial size
        Scene scene = new Scene(root, 640, 290); // Increased initial size
        scene.getStylesheets().add(getClass().getResource("/weatherapp/styles.css").toExternalForm()); // Add CSS file
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
