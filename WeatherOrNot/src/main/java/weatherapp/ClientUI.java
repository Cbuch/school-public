package weatherapp;

public class ClientUI {
    private ClientController controller;

    public ClientUI() {
        controller = new ClientController();
    }

    public void displayWeatherData() {
        System.out.println("Standby, collecting weather data.");
        controller.getWeatherData();
    }
}




/* 

public class ClientUI {
    private ClientController controller;

    public ClientUI() {
        controller = new ClientController();
    }

    public void displayWeatherData() {
        System.out.println("Standby, collecting weather data.");
        controller.getWeatherData();
    }

    public void updateUserPreferences() {
        System.out.println("Updating user preferences...");
    }

    public void showNotifications() {
        System.out.println("Showing notifications...");
    }
}
*/