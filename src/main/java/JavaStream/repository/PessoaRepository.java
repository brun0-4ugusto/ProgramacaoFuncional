package JavaStream.repository;

import JavaStream.model.Pessoa;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PessoaRepository implements Closeable {

    private final Connection connection;

    @SneakyThrows
    public void criarTabelaPessoa() {

        final var SQL = "CREATE TABLE Pessoa (" +
            "nome VARCHAR(255) NOT NULL," +
            "cidadeNascimento VARCHAR(255) NOT NULL," +
            "dataNascimento VARCHAR(255) NOT NULL," +
            "signo VARCHAR(30) NOT NULL" +
        ");";

        Statement statement = connection.createStatement();
        statement.execute(SQL);
    }

    @SneakyThrows
    public void inserirPessoas(List<Pessoa> pessoas) {

        for (var pessoa : pessoas) {
            adicionarPessoa(pessoa);
        }
    }


    @SneakyThrows
    public List<Pessoa> findAll() {

        var resultSet = getQueryResults("SELECT * FROM Pessoa");

        ArrayList<Pessoa> pessoas = new ArrayList<>();
        while (resultSet.next()) {
            adicionarResultadoQueryEmPessoas(resultSet, pessoas);
        }

        return pessoas;
    }

    @SneakyThrows
    private void adicionarPessoa(Pessoa pessoa) {

        var statement = createPessoaInsertStatement(pessoa);
        executePreparedStatement(statement);
    }

    @SneakyThrows
    private PreparedStatement createPessoaInsertStatement(Pessoa pessoa) {
        final var SQL = "INSERT INTO Pessoa (" +
                "nome, cidadeNascimento, dataNascimento, signo" +
                ") VALUES (?, ?, ?, ?)";

        var statement = connection.prepareStatement(SQL);
        setParametrosPessoa(pessoa, statement);

        return statement;
    }

    @SneakyThrows
    private void setParametrosPessoa(Pessoa pessoa, PreparedStatement statement) {
        statement.setString(1, pessoa.getNome());
        statement.setString(2, pessoa.getCidadeNascimento());
        statement.setString(3, pessoa.getDataNascimento().format(DateTimeFormatter.BASIC_ISO_DATE));
        statement.setString(4, pessoa.getSigno());
    }

    @SneakyThrows
    private void adicionarResultadoQueryEmPessoas(ResultSet resultSet, ArrayList<Pessoa> pessoas) {
        var nome = resultSet.getString("nome");
        var cidade = resultSet.getString("cidadeNascimento");
        var dataNascimento = resultSet.getDate("dataNascimento").toLocalDate();

        var pessoa = new Pessoa(nome, cidade, dataNascimento);
        pessoas.add(pessoa);
    }

    @SneakyThrows
    private ResultSet getQueryResults(String sql) {
        var statement = connection.createStatement();

        return statement.executeQuery(sql);
    }

    @SneakyThrows
    private void executePreparedStatement(PreparedStatement statement) {
        statement.executeUpdate();
    }

    @Override
    @SneakyThrows
    public void close() {
        if (connection != null) {
            connection.close();
        }
    }
}
