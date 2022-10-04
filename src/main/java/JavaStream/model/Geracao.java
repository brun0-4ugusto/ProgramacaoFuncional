package JavaStream.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

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
        List<Year> datasInicio = new ArrayList<Year>();

        for (Geracao geracao : values()) {
            datasInicio.add(geracao.dataInicio);
        }
        return datasInicio;
    }

    public static List<Year> getDataFimGeracao() {
        List<Year> datasFim = new ArrayList<Year>();

        for (Geracao geracao : values()) {
            datasFim.add(geracao.dataFim);
        }
        return datasFim;
    }

    public static Geracao getGeracao(Year anoInicio, Year anoFim){
        for(Geracao geracao : values()) {
            if(geracao.dataInicio == anoInicio && geracao.dataFim == anoFim) {
                return geracao;
            }
        }
        return null;
    }

}


