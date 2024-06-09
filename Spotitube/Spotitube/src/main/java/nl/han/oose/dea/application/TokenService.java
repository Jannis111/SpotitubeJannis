package nl.han.oose.dea.application;

import nl.han.oose.dea.Login;

import java.util.List;

public class TokenService {
    private int token;
    public int generateToken(List<Login> items) {
    int token = items.stream().mapToInt(item -> item.getToken()).max().orElse(0);
return token + 1;
    }

    public int haalTokenOp(Login login, List<Login> items) {
        token = login.getToken();
        if (token == 0) {
            token = generateToken(items);

        }
        return token;
    }


}
