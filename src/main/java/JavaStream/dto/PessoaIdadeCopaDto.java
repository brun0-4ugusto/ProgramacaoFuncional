package JavaStream.dto;

import JavaStream.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PessoaIdadeCopaDto {
    private Pessoa pessoa;
    private int idade;
}

