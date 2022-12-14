package JavaStream.model;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Signos {
    AQUÁRIO(MonthDay.of(1, 21), MonthDay.of(2, 18)),
    PEIXES(MonthDay.of(2, 19), MonthDay.of(3, 20)),
    ÁRIES(MonthDay.of(3, 21), MonthDay.of(4, 21)),
    TOURO(MonthDay.of(4, 22), MonthDay.of(5, 20)),
    GÊMEOS(MonthDay.of(5, 21), MonthDay.of(6, 20)),
    CÂNCER(MonthDay.of(6, 21), MonthDay.of(7, 22)),
    LEÃO(MonthDay.of(7, 23), MonthDay.of(8, 22)),
    VIRGEM(MonthDay.of(8, 23), MonthDay.of(9, 22)),
    LIBRA(MonthDay.of(9, 23), MonthDay.of(10, 22)),
    ESCORPIÃO(MonthDay.of(10, 23), MonthDay.of(11, 21)),
    SAGITÁRIO(MonthDay.of(11, 22), MonthDay.of(12, 21)),
    CAPRICÓRNIO(MonthDay.of(12, 22), MonthDay.of(1, 20));

    private final MonthDay dataInicioSigno;
    private final MonthDay dataFimSigno;

    Signos(MonthDay dataInicioSigno , MonthDay dataFimSigno) {
        this.dataInicioSigno = dataInicioSigno;
        this.dataFimSigno = dataFimSigno;
    }

    public static List<MonthDay> getDataInicioSignos() {

        return Arrays.stream(values())
                .map(s -> s.dataInicioSigno)
                .toList();
    }

    public static List<MonthDay> getDataFimSignos() {

        return Arrays.stream(values())
                .map(s -> s.dataFimSigno)
                .toList();
    }

    public static Signos getSigno(MonthDay startDate, MonthDay endDate){

        return Arrays.stream(values())
                .filter(s -> s.dataInicioSigno == startDate && s.dataFimSigno == endDate)
                .findFirst()
                .orElseThrow();
    }

    private static String[] getAllSignos(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
