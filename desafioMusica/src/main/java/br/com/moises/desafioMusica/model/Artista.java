package br.com.moises.desafioMusica.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String nomeArtista;
    @Enumerated(EnumType.STRING)
    private TipoMusica categoria;
   @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Musica> musicas = new ArrayList<>();

    public Artista(String nomeArtista, TipoMusica categoria) {
        this.nomeArtista = nomeArtista;
        this.categoria = categoria;
    }

    public Artista() {}

    public String getNomeArtista() {
        return nomeArtista;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public TipoMusica getCategoria() {
        return categoria;
    }

    public void setCategoria(TipoMusica categoria) {
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        musicas.forEach(musica -> musica.setArtista(this));
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return
                " Artista='" + nomeArtista + '\'' +
                ",Estilo de Musica=" + categoria;
    }
}

