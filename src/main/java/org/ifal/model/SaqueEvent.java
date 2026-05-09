package org.ifal.model;

public class SaqueEvent {
    private String client;
    private double value;
    private double restBalance;

    public SaqueEvent(String client, double value, double restBalance) {
        this.client = client;
        this.value = value;
        this.restBalance = restBalance;
    }

    public String getClient() {
        return client;
    }

    public double getValue() {
        return value;
    }

    public double restBalance() {
        return restBalance;
    }

}
