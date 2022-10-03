package JavaStream.service;

import JavaStream.Geracao;
import JavaStream.Pessoa;
import JavaStream.Signos;
import JavaStream.repository.Repository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class Service {
    private final Repository repository;

    // TODO: Obter a lista de pessoas que são do signo X e tem mais de Y anos.
    public List<Pessoa> retornaSignoIdade(Signos signo, int idade) {

        return new ArrayList<Pessoa>();
    }

    // TODO: Obter a lista e a quantidade de pessoas que são menor e maior de idade (List<Pessoa> menores, int quantidadeMenores, List<Pessoa> maiores, int quantidade maiores)
    public List<Pessoa> retornaQuantidadeDePessoasMaiorDeIdade() {
        /*
        * {
        *   "maiores": [{...}, {...}],
        *   "total": xxx
        * }
        * */

        List<Pessoa> pessoas = repository.findAll();
        return pessoas.stream().filter(pessoa -> pessoa.getIdade() > 18).toList();
    }

    public List<Pessoa> retornaQuantidadeDePessoasMenorDeIdade() {
        List<Pessoa> pessoas = repository.findAll();
        return pessoas.stream().filter(pessoa -> pessoa.getIdade() < 18).toList();
    }

    // TODO: Obter a lista de pessoas que pertencem a geração {}
    public List<Pessoa> retornaPessoasDaGeracao(Geracao geracao) {
        List<Pessoa> pessoas = repository.findAll();

        return pessoas.stream().filter(pessoa -> pessoa.getGeracao().equals(geracao.name())).toList();
    }

    // TODO: Obter a lista de todas as pessoas e informar a idade delas na próxima copa do mundo (2026) (List<Pessoa> pessoas, List<int> idade)
    public List<Pessoa> retornaIdadeProximaCopa() {
        List<Pessoa> pessoas = repository.findAll();

       List<Pessoa> novasPessoas = pessoas.stream()
               .map(pessoa -> new Pessoa(pessoa.getNome(),
                                         pessoa.getCidadeNascimento(),
                                         pessoa.getDataNascimento().minusYears(4)))
               .toList();

        return novasPessoas;
    }

    // TODO: Obter a pessoa mais velha e mais nova (List<pessoa> maisNova, List<pessoa> maisVelha)
    public Pessoa retornaPessoaMaisVelha() {
        List<Pessoa> pessoas = repository.findAll();
        return pessoas.stream().max(Comparator.comparing(Pessoa::getIdade)).get();
    }

    // TODO: Calcular a idade média e total das pessoas (double idadeMedia, int totalPessoas)
    public double retornaIdadeMedia() {
        List<Pessoa> pessoas = repository.findAll();

        double mediaDeTodasAsIdades = pessoas.stream()
                .mapToDouble(Pessoa::getIdade).average().getAsDouble();
//                .reduce(0, (resultadoParcial, pessoa) -> resultadoParcial + pessoa.getIdade(), Integer::sum);

        return mediaDeTodasAsIdades;
    }
}
