package ProgramacaoFuncional;

import java.time.LocalDate;
import java.util.function.Predicate;


public class PredicateInformarGeracaoZ implements Predicate<LocalDate> {

    // Convencional -> Predicate para informar se a pessoa é da geração Z (nasceu entre 1995 e 2010)
    @Override
    public boolean test(LocalDate dataNascimento) {
        return dataNascimento.getYear() >= 1995 && dataNascimento.getYear() <= 2010;
    }

    // Com Classe Anônima -> Predicate para informar se a pessoa é da geração Z (nasceu entre 1995 e 2010)
    public Predicate<LocalDate> getImplementacaoClasseAnonima() {

        var predicado = new Predicate<LocalDate>() {
            @Override
            public boolean test(LocalDate dataNascimento) {
                return dataNascimento.getYear() >= 1995 && dataNascimento.getYear() <= 2010;
            }
        };

        return predicado;
    }

    // Lambda -> Predicate para informar se a pessoa é da geração Z (nasceu entre 1995 e 2010)
    public Predicate<LocalDate> getImplementacaoLambda() {
        return dataNascimento -> dataNascimento.getYear() >= 1995 && dataNascimento.getYear() <= 2010;
    }
}
