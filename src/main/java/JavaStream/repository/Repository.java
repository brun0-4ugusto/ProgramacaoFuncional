package JavaStream.repository;

import JavaStream.Pessoa;
import lombok.SneakyThrows;

import java.rmi.server.RemoteObjectInvocationHandler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private final Connection connection;

    public Repository(Connection connection) {
        this.connection = connection;
    }

    public void criarTabelaPessoa() throws Exception {

        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE Pessoa (" +
                "nome VARCHAR(255) NOT NULL," +
                "cidadeNascimento VARCHAR(255) NOT NULL," +
                "dataNascimento VARCHAR(255) NOT NULL," +
                "signo VARCHAR(30) NOT NULL" +
                ");");

//        System.out.println("Tabela Pessoa criada com sucesso!");
    }

    @SneakyThrows
    public void inserirPessoas(List<Pessoa> pessoas) {

        String sql = "INSERT INTO Pessoa (" +
                "nome, cidadeNascimento, dataNascimento, signo" +
                ") VALUES (?, ?, ?, ?)";

        for (var pessoa : pessoas) {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getCidadeNascimento());
            statement.setString(3, pessoa.getDataNascimento().format(DateTimeFormatter.BASIC_ISO_DATE));
            statement.setString(4, pessoa.getSigno());
            statement.executeUpdate();
        }
    }

    @SneakyThrows
    public List<Pessoa> findAll() {

        var sql = "SELECT * FROM Pessoa";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<Pessoa> pessoas = new ArrayList<>();
        while (resultSet.next()) {
            String nome = resultSet.getString("nome");
            String cidade = resultSet.getString("cidadeNascimento");
            LocalDate dataNascimento = resultSet.getDate("dataNascimento").toLocalDate();

            var pessoa = new Pessoa(nome, cidade, dataNascimento);
            pessoas.add(pessoa);
        }

        return pessoas;
    }

}
