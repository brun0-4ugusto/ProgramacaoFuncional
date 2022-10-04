package JavaStream.dto;

import JavaStream.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
<<<<<<< HEAD

@Getter
@AllArgsConstructor
=======
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
>>>>>>> master
public class PessoaIdadeCopaDto {
    private Pessoa pessoa;
    private int idade;
}

