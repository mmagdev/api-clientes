package br.com.cotiinformatica.api_clientes.repositories;


import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.factories.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepository {

    @Autowired
    private ConnectionFactory connectionFactory;

    /*
    Método para inserir um cliente no banco de dados
     */

    public void inserir(Cliente cliente) throws Exception {

        //Escrever o comando SQL que será executado no banco de dados

        var sql = """
                INSERT INTO clientes(nome, email, telefone)
                VALUES(?, ?, ?)
                
                """;

        //Abrindo conexão com o banco de dados
        var connection = connectionFactory.getConnection();
        var statement = connection.prepareStatement(sql);
        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getEmail());
        statement.setString(3, cliente.getTelefone());

        connection.close(); //Fechar a conexão
    }

    /*Método para retornar uma lista
    com todos os clientes obtidos do banco de dados
     */
    public List<Cliente> obterPorNome() throws Exception {

    }

}


