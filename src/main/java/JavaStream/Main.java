package JavaStream;

import JavaStream.config.Config;
import JavaStream.repository.Repository;
import JavaStream.service.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Repository repository = inicializarRepository();
        Service service = new Service(repository);

        System.out.println(service.retornaIdadeMedia());
        System.out.println(service.retornaIdadeProximaCopa());
        System.out.println(service.retornaPessoaMaisVelha());
        System.out.println(service.retornaQuantidadeDePessoasMenorDeIdade());
        System.out.println(service.retornaQuantidadeDePessoasMaiorDeIdade());
        System.out.println(service.retornaPessoasDaGeracao(Geracao.BOOMER));

    }

    private static Repository inicializarRepository() throws Exception {
        Repository repository = new Repository(Config.getH2Connection());
        repository.criarTabelaPessoa();
        repository.inserirPessoas(List.of(
            new Pessoa("Artur Barreto", "Fortaleza-CE", LocalDate.of(1987, Month.MARCH, 25)),
            new Pessoa("Ana Luisa Fogarin", "Mococa-SP", LocalDate.of(2000, Month.MAY, 12)),
            new Pessoa("Bruno", "São Paulo-SP", LocalDate.of(1998, Month.JANUARY, 15)),
            new Pessoa("Ingrid", "São Paulo-SP", LocalDate.of(1990, Month.SEPTEMBER, 17)),
            new Pessoa("Victor", "São Paulo-SP", LocalDate.of(1996, Month.AUGUST, 1)),
            new Pessoa("Pedro Henrique", "São Paulo-SP", LocalDate.of(1998, Month.JUNE, 30))
        ));

        repository.findAll();
        return repository;
    }
}
