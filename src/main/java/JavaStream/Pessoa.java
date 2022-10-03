package JavaStream;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Period;
import java.time.Year;
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
        MonthDay mesDiaNascimento = MonthDay.of(dataNascimento.getMonth(), dataNascimento.getDayOfMonth());

        List<MonthDay> datasInicio = Signos.getDataInicioSignos();
        List<MonthDay> datasFim = Signos.getDataFimSignos();

        for (MonthDay dataInicio : datasInicio) {
            if (isWithinRange(mesDiaNascimento, dataInicio, datasFim.get(datasInicio.indexOf(dataInicio)))) {
                return Signos.getSigno(dataInicio, datasFim.get(datasInicio.indexOf(dataInicio))).name();
            }
        }
        return "Signo não existente!";
    }

    public String getSigno2(LocalDate dataNascimento) {
        MonthDay mesDiaNascimento = MonthDay.of(dataNascimento.getMonth(), dataNascimento.getDayOfMonth());

        List<MonthDay> datasInicio = Signos.getDataInicioSignos();
        List<MonthDay> datasFim = Signos.getDataFimSignos();

        for (MonthDay dataInicio : datasInicio) {
            if (isWithinRange(mesDiaNascimento, dataInicio, datasFim.get(datasInicio.indexOf(dataInicio)))) {
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

    private static boolean isWithinRange(MonthDay dataNascimento, MonthDay startDate, MonthDay endDate) {
        return !(dataNascimento.isBefore(startDate) || dataNascimento.isAfter(endDate));
    }

    private static boolean isWithinRange(Year dataNascimento, Year startDate, Year endDate) {
        return !(dataNascimento.isBefore(startDate) || dataNascimento.isAfter(endDate));
    }

    private static boolean isWithinRange(LocalDate dataNascimento, LocalDate startDate, LocalDate endDate) {
        return !(dataNascimento.isBefore(startDate) || dataNascimento.isAfter(endDate));
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
