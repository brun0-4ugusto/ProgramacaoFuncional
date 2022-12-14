package JavaStream.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.*;
import java.util.List;

// TODO: Criar uma base de dados de pessoas com os seguintes atributos e extrair as informações
@Getter
@NoArgsConstructor
public class Pessoa {
    private String nome;
    private String cidadeNascimento;
    private LocalDate dataNascimento;
    private String signo;
    private int idade;
    private String geracao;

    public Pessoa(String nome, String cidadeNascimento, LocalDate dataNascimento) {
        this.nome = nome;
        this.cidadeNascimento = cidadeNascimento;
        this.dataNascimento = dataNascimento;
        this.signo = getSigno(dataNascimento);
        this.geracao = getGeracao(dataNascimento);
        this.idade = Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public String getSigno(LocalDate dataNascimento) {
        List<MonthDay> datasInicio = Signos.getDataInicioSignos();
        List<MonthDay> datasFim = Signos.getDataFimSignos();

        for (MonthDay dataInicio : datasInicio) {
            if (isWithinRange(dataNascimento, dataInicio, datasFim.get(datasInicio.indexOf(dataInicio)))) {
                return Signos.getSigno(dataInicio, datasFim.get(datasInicio.indexOf(dataInicio))).name();
            }
        }
        return "Signo não existente!";
    }

    public String getGeracao(LocalDate dataNascimento) {
        Year anoNascimento = Year.of(dataNascimento.getYear());

        List<Year> datasInicio = Geracao.getDataInicioGeracao();
        List<Year> datasFim = Geracao.getDataFimGeracao();

        for (Year dataInicio : datasInicio) {
            if (isWithinRange(anoNascimento, dataInicio, datasFim.get(datasInicio.indexOf(dataInicio)))) {
                return Geracao.getGeracao(dataInicio, datasFim.get(datasInicio.indexOf(dataInicio))).name();
            }
        }

        return "Geração não exisiste!";
    }

    private static boolean isWithinRange(Year dataNascimento, Year dataInicio, Year dataFim) {
        return !(dataNascimento.isBefore(dataInicio) || dataNascimento.isAfter(dataFim));
    }

    private static boolean isWithinRange(LocalDate dataNascimento, MonthDay dataInicio, MonthDay dataFim) {
        int anoInicio;
        int anoFim;

        if (dataNascimento.getMonth().equals(Month.JANUARY) && dataInicio.getMonth().equals(Month.DECEMBER) && dataFim.getMonth().equals(Month.JANUARY)) {
            anoInicio = dataNascimento.getYear() - 1;
            anoFim = dataNascimento.getYear();
        } else if (dataNascimento.getMonth().equals(Month.DECEMBER) && dataInicio.getMonth().equals(Month.DECEMBER) && dataFim.getMonth().equals(Month.JANUARY)) {
            anoInicio = dataNascimento.getYear();
            anoFim = dataNascimento.getYear() + 1;
        }
        else {
            anoInicio = dataNascimento.getYear();
            anoFim = dataNascimento.getYear();
        }
        LocalDate inicio = LocalDate.of(anoInicio, dataInicio.getMonth(), dataInicio.getDayOfMonth());
        LocalDate fim = LocalDate.of(anoFim, dataFim.getMonth(), dataFim.getDayOfMonth());

        return !(dataNascimento.isBefore(inicio) || dataNascimento.isAfter(fim));
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome='" + nome + '\''
                + ", cidadeNascimento='" + cidadeNascimento + '\''
                + ", dataNascimento=" + dataNascimento
                + ", signo='" + signo + '\''
                + ", idade=" + idade
                + ", geracao='" + geracao + '\'' + '}';
    }
}
