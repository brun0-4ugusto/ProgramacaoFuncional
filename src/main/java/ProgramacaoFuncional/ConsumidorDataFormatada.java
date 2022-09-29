package ProgramacaoFuncional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class ConsumidorDataFormatada implements Consumer<LocalDateTime> {


    // Convencional -> Consumer para data formatada (dd/MM/yyyy HH:mm:ss)
    @Override
    public void accept(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formatedData = localDateTime.format(formatter);
        System.out.println("Data formatada: " + formatedData);
    }

    // Classe Anônima -> Consumer para data formatada (dd/MM/yyyy HH:mm:ss)
    public void getImplementacaoClasseAnonima(LocalDateTime localDateTime) {
        Consumer<LocalDateTime> localDateTimeConsumer = new Consumer<LocalDateTime>() {
            @Override
            public void accept(LocalDateTime localDateTime) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String dateFormated = formatter.format(localDateTime);
                System.out.println("Data formatada: " + dateFormated);
            }
        };
            localDateTimeConsumer.accept(localDateTime);
    }

    //Com Lambda -> Consumer para data formatada (dd/MM/yyyy HH:mm:ss)
    public void getImplementacaoLambda(LocalDateTime localDateTime){
        Consumer<LocalDateTime> localDateTimeConsumer2 = localDateTime1 -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dateFormated = formatter.format(localDateTime1);
            System.out.println("Data formatada: " + dateFormated);
        };

        localDateTimeConsumer2.accept(localDateTime);
    }

}


