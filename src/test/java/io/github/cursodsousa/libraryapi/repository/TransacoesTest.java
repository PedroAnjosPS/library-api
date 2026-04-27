package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    /**
     * Commit --> Confirmar as alterações
     * Rollback --> Desfazer as alterações
     */
    @Test
    void transacaoSimples() {
        // salvar um livro
        // salvar o autor
        // alugar o livro
        // enviar um email pro locatário
        // notificar que o livro saiu da livraria

        transacaoService.executar();
    }

    @Test
    void transacaoEstadoManaged() {
        transacaoService.atualizacaoSemAtualizar();
    }
}
