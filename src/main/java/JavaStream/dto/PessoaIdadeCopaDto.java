package JavaStream.dto;

import JavaStream.model.Pessoa;

public record PessoaIdadeCopaDto(
    Pessoa pessoa,
    int idade
) {
}
