package weatherapp;

public class UserPreferences {

    public void setPreference(String key, String value) {
        System.out.println("Setting preference " + key + " to " + value);
        // Code to set preference
    }

    public String getPreference(String key) {
        System.out.println("Getting preference for " + key);
        // Simulate getting a preference
        String preference = "Sample preference value";
        return preference;
    }


}
