package sample.Objects;

import org.json.JSONObject;

public class LoginResult {
    public boolean success;
    public JSONObject userCredentials;

    public LoginResult(boolean success, JSONObject userCredentials) {
        this.success = success;
        this.userCredentials = userCredentials;
    }
}
