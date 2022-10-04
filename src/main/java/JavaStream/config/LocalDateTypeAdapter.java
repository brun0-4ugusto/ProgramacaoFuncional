package JavaStream.config;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 como citado em <a href="https://github.com/google/gson/issues/1898#issuecomment-849921487">issue github</a>, a partir do java 16 não
 podemos por default via reflection classes do JDK. Com isso temos que implementar um TypeAdapter
 capaz de lidar com a classe LocalDate. Referencia para como fazê-lo na lib do GSON:
 @see: <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/TypeAdapter.html">GSON Type adapter<a/>
 */
public class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {

    private LocalDateTypeAdapter() {  }

    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        if (localDate == null) {
            jsonWriter.nullValue();
        } else {
            var dateAsIso = localDate.format(DateTimeFormatter.ISO_DATE);
            jsonWriter.value(dateAsIso);
        }
    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        } else {
            String dateAsString = jsonReader.nextString();
            return LocalDate.parse(dateAsString);
        }
    }

    public static LocalDateTypeAdapter getInstance() {
        return new LocalDateTypeAdapter();
    }
}
