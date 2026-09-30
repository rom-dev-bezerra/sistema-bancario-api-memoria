package com.bancocentral.api.controller;

import com.bancocentral.api.model.ContaBancaria;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ContaController {

    private List<ContaBancaria> contas = new ArrayList<>(Arrays.asList(
            new ContaBancaria(1000, "R@20", "Rômulo", 20000),
            new ContaBancaria(1001, "K@24", "Karen", 30000),
            new ContaBancaria(1002, "J@20", "Juan", 15000),
            new ContaBancaria(1003, "K@05", "Ketlhem", 25000),
            new ContaBancaria(1004, "A@19", "Amanda", 12000)
    ));

    @GetMapping("/boas-vindas")
    public String mensagemBoasVindas() {
        return "Olá Rômulo! Sua API está funcionando com sucesso!";
    }

    @GetMapping("/contas")
    public List<ContaBancaria> todasAsContas() {

        return this.contas;
    }

    @GetMapping("/contas/{numero}")
    public ContaBancaria buscarPorNumero(@PathVariable int numero) {
        for (ContaBancaria conta : this.contas) {
            if (conta.getNumeroConta() == numero) {
                return conta;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada");
    }

    //criando a rota de depósito
    @PostMapping("/contas/{numero}/depositar")
    public String depositarAPI(@PathVariable int numero, @RequestParam double valor) {

        ContaBancaria conta = null;
        for (ContaBancaria c : contas) {
            if (c.getNumeroConta() == numero) {
                conta = c;
                break;
            }
        }
        //caso não exista a conta, mostrar um erro
        if (conta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta Inexistente");
        }

        boolean sucesso = conta.depositar(valor);

        if (sucesso) {
            return "Depósito de R$ " + valor + " realizado! Novo saldo: R$ " + conta.getSaldo();
        } else {
            return "Erro: Valor de depósito inválido.";
        }
    }

    //criando a rota de saque
    @PostMapping("/contas/{numero}/sacar")
    public String sacarAPI(@PathVariable int numero, @RequestParam double valor) {

        ContaBancaria conta = null;
        for (ContaBancaria c : contas) {
            if (c.getNumeroConta() == numero) {
                conta = c;
                break;
            }
        }

        //caso a conta não exista
        if (conta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta Inexistente");
        }

        boolean sucesso = conta.sacar(valor);
        if (sucesso) {
            return "Saque de R$ " + valor + " realizado! Novo saldo: R$ " + conta.getSaldo();
        } else {
            return "Erro: Valor de saque insuficiente.";
        }

    }

    //criando rota para transferir
    @PostMapping("/contas/{numero}/transferir")
    public String transferirAPI(@PathVariable int numero, @RequestParam int contaDestino, @RequestParam double valor) {

        ContaBancaria origem = null;
        ContaBancaria destino = null;

        for (ContaBancaria c1 : contas) {
            if (c1.getNumeroConta() == numero) {
                origem = c1;
                break;
            }
        }

        for (ContaBancaria c2 : contas) {
            if (c2.getNumeroConta() == contaDestino) {
                destino = c2;
                break;
            }
        }

        if (origem == null || destino == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de origem ou destino não encontrada.");
        }

        boolean sucesso = origem.transferir(destino, valor);
        if (sucesso) {
            return "Transferência de R$ " + valor + " realizada com sucesso de " + origem.getNomeTitular() + " para " + destino.getNomeTitular();
        } else {
            return "Erro: falha ao realizar transferência.";
        }
    }

}
