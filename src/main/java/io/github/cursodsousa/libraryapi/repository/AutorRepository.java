package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
