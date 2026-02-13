package br.com.cotiinformatica.api_clientes.repositories;

import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.factories.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

        statement.execute();

        connection.close(); //Fechar a conexão
    }

    /*
    Método para atualizar um cliente no banco de dados
     */

    public boolean atualizar(Cliente cliente) throws Exception {

        //Escrever o comando SQL que será executado no banco de dados
        var sql = """
                    UPDATE clientes
                    SET
                        nome = ?, email = ?, telefone = ?
                    WHERE
                        id =?
                
                """;

        //Abrindo conexão com o banco de dados
        var connection = connectionFactory.getConnection();

        //Executando o comando SQL para edição
        var statement = connection.prepareStatement(sql);

        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getEmail());
        statement.setString(3, cliente.getTelefone());
        statement.setInt(4, cliente.getId());
        var result = statement.executeUpdate();

        //Fechando a conexão com banco
        connection.close();

        //Retornar verdadeiro caso algum registro tenha sido alterado
        return result > 0;

    }

    /*
    Método para a execlusão lógica (inativação) do registro do cliente
     */

    public boolean excluir(Integer id) throws Exception {

        //Escrever o comando SQL que será executado no banco de dados
        var sql = """
                    UPDATE clientes
                    SET
                        ativo = 0
                    WHERE
                        id = ?
                
                """;

        //Abrindo a conexão com o banco de dados
        var connection = connectionFactory.getConnection();

        //Executando o comando no banco de dados
        var statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        var result = statement.executeUpdate();

        //Fechando a conexão com o banco
        connection.close();

        //Retornar verdadeiro se algum registro foi alterado
        return result > 0;

    }



    /*Método para retornar uma lista
    com todos os clientes obtidos do banco de dados
     */
    public List<Cliente> obterPorNome(String nome) throws Exception {

        var sql = """
                    SELECT id, nome, email, telefone, datacadastro, ativo
                    FROM clientes
                    WHERE ativo = 1 AND nome ILIKE ?
                  """;

        //Abrir a conexão com o banco de dados
        var connection = connectionFactory.getConnection();

        //Executando o comando SQL no banco de dados
        var statement = connection.prepareStatement(sql);
        statement.setString(1,"%" + nome + "%");
        var result = statement.executeQuery();

        var lista = new ArrayList<Cliente>(); //Criando uma lista vazia

        while(result.next()){
            var cliente = new Cliente();

            cliente.setId(result.getInt("id"));
            cliente.setNome(result.getString("nome"));
            cliente.setEmail(result.getString("email"));
            cliente.setTelefone(result.getString("telefone"));
            cliente.setDataCadastro(result.getTimestamp("datacadastro"));
            cliente.setAtivo(result.getInt("ativo"));

            lista.add(cliente); //Adicionando o cliente na lista

        }

        //Fechando a conexão com o banco de dados
        connection.close();

        return lista; //Retornando a lista

    }

}


