package JavaStream.repository;

import JavaStream.model.Pessoa;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PessoaRepository implements Closeable {

    private final Connection connection;

    @SneakyThrows
    public void criarTabelaPessoa() {

        final String SQL = "CREATE TABLE Pessoa (" +
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

        for (Pessoa pessoa : pessoas) {
            adicionarPessoa(pessoa);
        }
    }


    @SneakyThrows
    public List<Pessoa> findAll() {

        ResultSet resultSet = getQueryResults("SELECT * FROM Pessoa");

        ArrayList<Pessoa> pessoas = new ArrayList<>();
        while (resultSet.next()) {
            adicionarResultadoQueryEmPessoas(resultSet, pessoas);
        }

        return pessoas;
    }

    @SneakyThrows
    private void adicionarPessoa(Pessoa pessoa) {

        PreparedStatement statement = createPessoaInsertStatement(pessoa);
        executePreparedStatement(statement);
    }

    @SneakyThrows
    private PreparedStatement createPessoaInsertStatement(Pessoa pessoa) {
        final String SQL = "INSERT INTO Pessoa (" +
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
        String nome = resultSet.getString("nome");
        String cidade = resultSet.getString("cidadeNascimento");
        LocalDate dataNascimento = resultSet.getDate("dataNascimento").toLocalDate();

        var pessoa = new Pessoa(nome, cidade, dataNascimento);
        pessoas.add(pessoa);
    }

    @SneakyThrows
    private ResultSet getQueryResults(String sql) {
        Statement statement = connection.createStatement();

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
