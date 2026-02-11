package br.com.cotiinformatica.api_clientes.dtos;

/*
Registro para definir os dados que a API
receberá para cadastrar um cliente
 */

public record ClienteRequest(
        String nome,    //Nome do cliente
        String email,   //Email do cliente
        String telefone //Telefone do cliente
) {

}


