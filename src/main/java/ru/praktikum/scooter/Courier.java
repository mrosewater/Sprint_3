package ru.praktikum.scooter;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {

    public String login;
    public String password;
    public String firstName;

    public Courier() {

    }

    public Courier (String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier getRandom() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, firstName);
    }

    public Courier setLogin(String login) {
        this.login = login;
        return this;
    }

    public Courier setPassword(String password) {
        this.password = password;
        return this;
    }

    public static Courier getLoginOnly() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        return new Courier().setLogin(login);
    }

    public static Courier getPasswordOnly() {
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new Courier().setPassword(password);
    }

    public static Courier getLoginAndPassword() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new Courier().setLogin(login).setPassword(password);
    }

}
