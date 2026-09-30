package com.bancocentral.api.model;

public class ContaBancaria {
    private String nomeTitular;
    private double saldo;
    private int numeroConta;
    private String senha;

    public ContaBancaria( int numeroConta, String senha, String nomeTitular, double saldoInicial) {
        this.nomeTitular = nomeTitular;
        this.numeroConta = numeroConta;
        this.senha = senha;
        if (saldoInicial >= 0) {
            this.saldo = saldoInicial;
        }
    }

    public String getNomeTitular() {
        return this.nomeTitular;
    }
    public double getSaldo() { return this.saldo; }
    public String getSenha() {
        return this.senha;
    }
    public int getNumeroConta() {
        return this.numeroConta;
    }

    public boolean depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean transferir(ContaBancaria contaBancaria, double valor) {
        if (valor <= 0) {
            return false;
        }

        if (this.saldo >= valor) {
            this.saldo -= valor;
            contaBancaria.depositarDeTransferencia(valor);
            return true;
        } else {
            return false;
        }
    }

    public void depositarDeTransferencia(double valor) {
        this.saldo += valor;
    }

    public void apresentar() {
        System.out.println("--- Dados da Conta ---");
        System.out.println("Titular: " + this.nomeTitular);
        System.out.println("Saldo atual: R$" + this.saldo);
    }
}
