package ru.netology.data;

public class Registration {
    private final String login;
    private final String password;
    private final String status;

    public Registration(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }
}