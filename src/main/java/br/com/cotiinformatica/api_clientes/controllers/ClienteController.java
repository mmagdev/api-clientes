package br.com.cotiinformatica.api_clientes.controllers;

//A camada controller é responsável por fazer a comunicação
//entre o front e o back end através da anotação @RestController.

/*
O request mapping, por outro lado, é uma anotação do Spring Boot
para configurar o enderço do endpoint de uma API
 */


import br.com.cotiinformatica.api_clientes.dtos.ClienteRequest;
import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    /*
    Método para criar um serviço
    de cadastro de clientes
    */

    @PostMapping
    public String post(@RequestBody ClienteRequest request){
        try {

            var cliente = new Cliente(); //criando um objeto da entidade

            cliente.setNome(request.nome()); //Capturando o nome do cliente
            cliente.setEmail(request.email()); //Capturando o email do cliente
            cliente.setTelefone(request.telefone()); //Capturando o telefone do cliente

            clienteRepository.inserir(cliente); //Salvando no banco de dados

            return "Cliente cadastrado com sucesso!";



        }
        catch (Exception e){
            return "Falha ao inserir cliente: " + e.getMessage();
        }

    }




}
