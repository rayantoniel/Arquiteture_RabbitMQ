package org.ifal.publisher;

public class CurrentAccount {
    private String client;
    private double balance;

    public CurrentAccount(String client, double balance) {
        this.client = client;
        this.balance = balance;
    }

    public boolean sacar(double value) {

        if (value > balance) {
            System.out.println("Saldo insuficiente.");
            return false;
        }

        balance -= value;

        System.out.println("Saque realizado com sucesso.");
        System.out.println("Novo saldo: R$ " + balance);

        return true;
    }

    public String getClient() {
        return client;
    }

    public double getBalance() {
        return balance;
    }

}
