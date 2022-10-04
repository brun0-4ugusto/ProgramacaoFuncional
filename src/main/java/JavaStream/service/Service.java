package JavaStream.service;

import JavaStream.dto.PessoaIdadeCopaDto;
import JavaStream.model.Geracao;
import JavaStream.model.Pessoa;
import JavaStream.model.Signos;
import JavaStream.repository.PessoaRepository;
import JavaStream.type.Tuple;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class Service {
    private final PessoaRepository repository;

    // Obter a lista de pessoas que são do signo X e tem mais de Y anos.
    public List<Pessoa> retornaSignoIdade(Signos signo, int idade) {
        List<Pessoa> pessoas = repository.findAll();

        return pessoas.stream()
            .filter(
                p -> p.getSigno().equalsIgnoreCase(signo.name()) &&
                p.getIdade() > idade
            ).toList();
    }

    // Obter a lista e a quantidade de pessoas que são menor e maior de idade (List<Pessoa> menores, int quantidadeMenores, List<Pessoa> maiores, int quantidade maiores)
    public Tuple<Tuple<List<Pessoa>, Integer>, Tuple<List<Pessoa>, Integer>> retornaQuantidadeDePessoasMenorEMaiorDeIdade() {
        List<Pessoa> pessoas = repository.findAll();

        List<Pessoa> listaMenores = pessoas.stream().filter(p -> p.getIdade() < 18).toList();
        List<Pessoa> listaMaiores = pessoas.stream().filter(p -> p.getIdade() >= 18).toList();

        Tuple<List<Pessoa>, Integer> tupleListaMenores = Tuple.create(listaMenores, listaMenores.size());
        Tuple<List<Pessoa>, Integer> tupleListaMaiores = Tuple.create(listaMaiores, listaMaiores.size());

        return Tuple.create(tupleListaMenores, tupleListaMaiores);
    }

    // Obter a lista de pessoas que pertencem a geração {}
    public List<Pessoa> retornaPessoasDaGeracao(Geracao geracao) {
        List<Pessoa> pessoas = repository.findAll();

        return pessoas.stream().filter(pessoa -> pessoa.getGeracao().equals(geracao.name())).toList();
    }

    // Obter a lista de todas as pessoas e informar a idade delas na próxima copa do mundo (2026) (List<Pessoa> pessoas, List<int> idade)
    public List<PessoaIdadeCopaDto> retornaIdadeProximaCopa() {
        List<Pessoa> pessoas = repository.findAll();

        return pessoas.stream()
                .map(pessoa -> new PessoaIdadeCopaDto(pessoa, pessoa.getIdade() + 4))
                .toList();
    }

    // Obter a pessoa mais velha e mais nova (List<pessoa> maisNova, List<pessoa> maisVelha)
    public Tuple<Pessoa, Pessoa> retornaPessoaMaisVelha() {
        List<Pessoa> pessoas = repository.findAll();

        Pessoa pessoaMaisVelha = pessoas.stream().max(Comparator.comparing(Pessoa::getIdade))
                .orElse(new Pessoa("Matusalem", "Longe", LocalDate.of(900, 1, 1)));

        Pessoa pessoaMaisNova = pessoas.stream().min(Comparator.comparing(Pessoa::getIdade))
                .orElse(new Pessoa("Enzo", "Perto", LocalDate.now()));

        return Tuple.create(pessoaMaisNova, pessoaMaisVelha);
    }

    // Calcular a idade média e total das pessoas (double idadeMedia, int totalPessoas)
    public double retornaIdadeMedia() {
        List<Pessoa> pessoas = repository.findAll();

        return pessoas.stream()
                .mapToDouble(Pessoa::getIdade).average().orElse(0.0);
    }
}
