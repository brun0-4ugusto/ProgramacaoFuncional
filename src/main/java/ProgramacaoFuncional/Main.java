package ProgramacaoFuncional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n########################\n");
        System.out.println("Comparador data de nascimento");
        var comparatorDataNascimento = new ComparatorDataDeNascimento();

        var datasNascimentoDesordenada = comparatorDataNascimento.getList();
        datasNascimentoDesordenada.sort(comparatorDataNascimento.getImplementacaoClasseAnonima());
        System.out.println("Implementação classe anônima: " + datasNascimentoDesordenada);

        datasNascimentoDesordenada = comparatorDataNascimento.getList();
        datasNascimentoDesordenada.sort(comparatorDataNascimento.getImplementacaoLambda());
        System.out.println("Implementação lambda: " + datasNascimentoDesordenada);

        System.out.println("\n########################\n");

        System.out.println("Consumidor data formatada");
        var consumidorDataFormatada = new ConsumidorDataFormatada();

        System.out.println("Implementação convencional: ");
        consumidorDataFormatada.accept(LocalDateTime.now());
        System.out.println("Implementação classe anônima: ");
        consumidorDataFormatada.getImplementacaoClasseAnonima(LocalDateTime.now());
        System.out.println("Implementação lambda: ");
        consumidorDataFormatada.getImplementacaoLambda(LocalDateTime.now());

        System.out.println("\n########################\n");

        System.out.println("Predicate descobrir geração");
        var informacaoGeracaoZ = new PredicateInformarGeracaoZ();

        System.out.println("Implementação convencional: ");
        System.out.println("25/03/87 é geracao z? " + informacaoGeracaoZ.test(LocalDate.of(1987, Month.MARCH, 25)));
        System.out.println("Implementação classe anonima: ");
        System.out.println("25/03/87 é geracao z? " + informacaoGeracaoZ.getImplementacaoClasseAnonima().test(LocalDate.of(1987, Month.MARCH, 25)));
        System.out.println("Implementação lambda: ");
        System.out.println("25/03/87 é geracao z? " + informacaoGeracaoZ.getImplementacaoLambda().test(LocalDate.of(1987, Month.MARCH, 25)));

        System.out.println("\n########################\n");

        System.out.println("Supplier Signos");
        var supplierSignos = new SupplierSignos();

        System.out.println("Implementação convencional: ");
        System.out.println(supplierSignos.get());
        System.out.println("Implementação classe anonima: ");
        System.out.println(supplierSignos.getImplementacaoClasseAnonima().get());
        System.out.println("Implementação lambda: ");
        System.out.println(supplierSignos.getImplementacaoLambda().get());
    }
}

