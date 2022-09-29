package ProgramacaoFuncional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SupplierSignos implements Supplier<List<String>> {

    private final List<String> signos = Arrays.asList(
        "Áries: 21 de março a 20 de abril",
        "Touro: 21 de abril a 20 de maio",
        "Gêmeos: 21 de maio a 20 de junho",
        "Câncer: 21 de junho a 22 de julho",
        "Leão: 23 de julho a 22 de agosto",
        "Virgem: 23 de agosto a 22 de setembro",
        "Libra: 23 de setembro a 22 de outubro",
        "Escorpião: 23 de outubro a 21 de novembro",
        "Sagitário: 22 de novembro a 21 de dezembro",
        "Capricórnio: 22 de dezembro a 20 de janeiro",
        "Aquário: 21 de janeiro a 19 de fevereiro",
        "Peixes: 19 de fevereiro a 20 de março"
    );

    // Convencional -> Supplier para informar os signos com as datas
    @Override
    public List<String> get() {
        return Collections.unmodifiableList(signos);
    }

    // Com Classe Anônima ->Supplier para informar os signos com as datas
    public Supplier<List<String>> getImplementacaoClasseAnonima() {
        return new Supplier<List<String>>() {
            @Override
            public List<String> get() {
                return Collections.unmodifiableList(signos);
            }
        };
    }

    // Com Lambda -> Supplier para informar os signos com as datas
    public Supplier<List<String>> getImplementacaoLambda() {
        return () -> Collections.unmodifiableList(signos);
    }
}
