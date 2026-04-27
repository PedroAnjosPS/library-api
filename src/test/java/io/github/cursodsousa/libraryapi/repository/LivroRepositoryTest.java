package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("67342-14350");
        livro.setPreco(BigDecimal.valueOf(230));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("O Universo numa Casca de Noz");
        livro.setDataPublicacao(LocalDate.of(2001, 10, 6));

        Autor autor = autorRepository.findById(UUID.fromString("4472071b-03d2-469b-8f29-2901aa04dd13")).orElse(null);
        livro.setAutor(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("96310-12043");
        livro.setPreco(BigDecimal.valueOf(50));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Battle Royale");
        livro.setDataPublicacao(LocalDate.of(1995, 5, 26));

        Autor autor = new Autor();
        autor.setNome("Denzel Washington");
        autor.setNacionalidade("Estadunidense");
        autor.setDataNascimento(LocalDate.of(1954, 12, 28));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("96310-12042");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("O Colecionador");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Don Corleone");
        autor.setNacionalidade("Italiano");
        autor.setDataNascimento(LocalDate.of(1958, 5, 21));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro() {
        UUID idLivro = UUID.fromString("c0675579-b357-4d5e-b485-ca40392ebe68");
        var livroParaAtualizar = repository.findById(idLivro).orElse(null);

        UUID idAutor = UUID.fromString("3119561d-97c7-4a39-bbd9-fae310805660");
        Autor joelson = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(joelson);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar() {
        UUID id = UUID.fromString("c0675579-b357-4d5e-b485-ca40392ebe68");
        repository.deleteById(id);
    }

    @Test
    void deletarCascade() {
        UUID id = UUID.fromString("819178d2-f56f-48f1-9238-970f5ab82927");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest() {
        UUID id = UUID.fromString("1e404b47-8e72-4d2c-afd8-8f2c64784cb0");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Título do livro: " + livro.getTitulo());
        System.out.println("Autor do livro: " + livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest() {
        List<Livro> lista = repository.findByTitulo("O Colecionador");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorISBNTest() {
        List<Livro> lista = repository.findByIsbn("98291-87135");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloAndPrecoTest() {
        String titulo = "Ciquenta Tons Mais Escuros";
        var preco = BigDecimal.valueOf(64.00);

        List<Livro> lista = repository.findByTituloAndPreco(titulo, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloOrISBNTest() {
        String titulo = "Ciquenta Tons Mais Escuros";
        String isbn = "97815-35139";

        List<Livro> lista = repository.findByTituloOrIsbnOrderByTitulo(titulo, isbn);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL() {
        var resultado = repository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros() {
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarTitulosDiferentesLivros() {
        var resultado = repository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeLivrosAutoresItalianos() {
        var resultado = repository.listarGenerosAutoresItalianos();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGenerosQueryParam() {
        var resultado = repository.findByGenero(GeneroLivro.FICCAO, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroPositionalParamTest() {
        var resultado = repository.findByGeneroPositionalParameters(GeneroLivro.FICCAO, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest() {
        repository.deleteByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    void atualizarDataPublicacaoTest() {
        LocalDate dataAtualizada = LocalDate.of(2020, 5,12);
        UUID id = UUID.fromString("b634b7cb-a47e-4e1a-96e1-c07b6466ae0b");

        repository.updateDataPublicacao(dataAtualizada, id);
    }
}