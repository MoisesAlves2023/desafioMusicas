package br.com.moises.desafioMusica.repository;

import br.com.moises.desafioMusica.model.Artista;
import br.com.moises.desafioMusica.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeArtistaContainingIgnoreCase(String nomeArtista);

    @Query("SELECT m FROM Musica m JOIN m.artista a WHERE a.id = :artistaId")
    List<Musica> findMusicasByArtistaId(Long artistaId);
}
