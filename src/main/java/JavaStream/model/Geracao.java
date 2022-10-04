package JavaStream.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Geracao {
    BOOMER(Year.of(1945), Year.of(1964)),
    X(Year.of(1965), Year.of(1981)),
    Y(Year.of(1982), Year.of(1994)),
    Z(Year.of(1995), Year.of(2010)),
    ALPHA(Year.of(2011), Year.of(2025));

    private final Year dataInicio;
    private final Year dataFim;


    Geracao (Year dataComeco, Year dataFim) {
        this.dataInicio = dataComeco;
        this.dataFim = dataFim;
    }
    
    public static List<Year> getDataInicioGeracao() {

        return Arrays.stream(values())
                .map(g -> g.dataInicio)
                .toList();
    }

    public static List<Year> getDataFimGeracao() {

        return Arrays.stream(values())
                .map(g -> g.dataFim)
                .toList();
    }

    public static Geracao getGeracao(Year anoInicio, Year anoFim){

        return Arrays.stream(values())
                .filter(g -> g.dataInicio == anoInicio && g.dataFim == anoFim)
                .findFirst()
                .orElse(null);
    }

}


