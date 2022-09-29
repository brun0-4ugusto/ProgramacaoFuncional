package ProgramacaoFuncional;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorDataDeNascimento {

    private final List<LocalDate> list = Arrays.asList(
        LocalDate.of(2000, Month.MAY, 12),
        LocalDate.of(1998, Month.JANUARY, 1),
        LocalDate.of(1998, Month.JUNE, 30),
        LocalDate.of(1987, Month.MARCH, 25)
    );

    // Com Classe Anônima -> Ordernar datas de nascimento com o comparator
    public Comparator<LocalDate> getImplementacaoClasseAnonima() {

        return new Comparator<LocalDate>() {
            @Override
            public int compare(LocalDate data1, LocalDate data2) {
                return data1.compareTo(data2);
            }
        };
    }

    // Com Lambda -> Ordernar datas de nascimento com o comparator
    public Comparator<LocalDate> getImplementacaoLambda() {
        return (data1, data2) -> data1.compareTo(data2);
    }

    public List<LocalDate> getList() {
        Collections.shuffle(list);
        return new ArrayList<>(list);
    }
}
