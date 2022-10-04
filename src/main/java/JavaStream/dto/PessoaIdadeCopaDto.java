package JavaStream.dto;

import JavaStream.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PessoaIdadeCopaDto {
    private Pessoa pessoa;
    private int idade;
}
