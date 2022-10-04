package JavaStream.dto;

import JavaStream.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PessoaIdadeCopaDto {
    private Pessoa pessoa;
    private int idade;
}

