package JavaStream;

import JavaStream.config.Config;
import JavaStream.model.Geracao;
import JavaStream.model.Pessoa;
import JavaStream.model.Signos;
import JavaStream.repository.PessoaRepository;
import JavaStream.service.Service;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Main {

    private final static Gson jsonSerializer = Config.getConfiguredJsonSerializer();

    public static void main(String[] args) throws Exception {

        PessoaRepository repository = inicializarRepository();
        Service service = new Service(repository);

        System.out.format("Pessoas do signo Touro maiores de 15 anos: %s%n", jsonSerializer.toJson(service.retornaSignoIdade(Signos.TOURO, 15)));

        System.out.format("Idade média das pessoas: %.2f%n", service.retornaIdadeMedia());
        System.out.format("Dados para a proxima copa: %s%n", jsonSerializer.toJson(service.retornaIdadeProximaCopa()));
        System.out.format("Pessoa mais nova: %s%n", jsonSerializer.toJson(service.retornaPessoaMaisVelha().first));
        System.out.format("Pessoa mais velha: %s%n", jsonSerializer.toJson(service.retornaPessoaMaisVelha().second));

        var quantidadeMenoresEMaiores = service.retornaQuantidadeDePessoasMenorEMaiorDeIdade();

        List<Pessoa> pessoasMenores = quantidadeMenoresEMaiores.first.first;
        long quantidadeMenores = quantidadeMenoresEMaiores.first.second;
        List<Pessoa> pessoasMaiores = quantidadeMenoresEMaiores.second.first;
        long quantidadeMaiores = quantidadeMenoresEMaiores.second.second;

        System.out.format("Lista pessoas menores de idade: %s%n", jsonSerializer.toJson(pessoasMenores));
        System.out.format("Quantidade de menores de idade: %d%n", quantidadeMenores);
        System.out.format("Lista pessoas maiores de idade: %s%n", jsonSerializer.toJson(pessoasMaiores));
        System.out.format("Quantidade de maiores de idade: %d%n", quantidadeMaiores);

        System.out.format("Pessoas da geracao z: %s%n", jsonSerializer.toJson(service.retornaPessoasDaGeracao(Geracao.Z)));

    }

    private static PessoaRepository inicializarRepository() throws Exception {
        PessoaRepository repository = new PessoaRepository(Config.getH2Connection());
        repository.criarTabelaPessoa();
        repository.inserirPessoas(List.of(
            new Pessoa("Artur Barreto", "Fortaleza-CE", LocalDate.of(1987, Month.MARCH, 25)),
            new Pessoa("Ana Luisa Fogarin", "Mococa-SP", LocalDate.of(2000, Month.MAY, 12)),
            new Pessoa("Bruno", "São Paulo-SP", LocalDate.of(1998, Month.JANUARY, 15)),
            new Pessoa("Ingrid", "São Paulo-SP", LocalDate.of(1990, Month.SEPTEMBER, 17)),
            new Pessoa("Victor", "São Paulo-SP", LocalDate.of(2011, Month.AUGUST, 1)),
            new Pessoa("Pedro Henrique", "São Paulo-SP", LocalDate.of(1998, Month.JUNE, 30))
        ));

        repository.findAll();
        return repository;
    }
}
