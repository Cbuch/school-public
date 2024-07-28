package weatherapp;

public class ServerAPILayer {

    public String communicateWithServer(String request) {
        System.out.println("Communicating with server...");
        // Simulate server communication
        String serverResponse = "Server response to " + request;
        return serverResponse;
    }

    // Additional methods for server communication can be added here
}
