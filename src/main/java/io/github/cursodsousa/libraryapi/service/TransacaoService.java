package io.github.cursodsousa.libraryapi.service;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import io.github.cursodsousa.libraryapi.repository.AutorRepository;
import io.github.cursodsousa.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    /// livro (titulo, ... , nome_arquivo) --> id.png
    @Transactional
    public void salvarLivroComFoto() {
        // salva o livro
        // repository.save(livro);

        // pega o id do livro = livro.getId();
        // var id = livro.getId();

        // salvar a foto do livro --> bucket na nuvem
        // bucketService.salvar(livro.getFoto(), id + ".png");

        // atualizar o nome do arquivo que foi salvo
        // livro.setNomeArquivoFoto(id + ".png");
    }

    @Transactional
    public void atualizacaoSemAtualizar() {
        var livro = livroRepository
                    .findById(UUID.fromString("6e458a51-dd2a-480e-8d0d-70179e08401a"))
                    .orElse(null);
        livro.setDataPublicacao(LocalDate.of(2025, 6, 19));
    }

    @Transactional
    public void executar() {
        // salva o autor
        Autor autor = new Autor();
        autor.setNome("Teste Francisco");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2001, 6, 19));

        autorRepository.saveAndFlush(autor);

        // salva o livro
        Livro livro = new Livro();
        livro.setIsbn("51368-2789");
        livro.setPreco(BigDecimal.valueOf(84));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Teste Livro do Francisco");
        livro.setDataPublicacao(LocalDate.of(2023, 6, 19));

        livro.setAutor(autor);

        livroRepository.saveAndFlush(livro);

        if (autor.getNome().equals("Teste Francisco")) {
            throw new RuntimeException("Rollback!!");
        }
    }
}
