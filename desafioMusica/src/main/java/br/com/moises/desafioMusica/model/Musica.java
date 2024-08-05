package br.com.moises.desafioMusica.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String nomeMusica;
    @ManyToOne
    private Artista artista;

    public Musica() {}

    public Musica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;

    }

    @Override
    public String toString() {
        return "Música: " + nomeMusica + ", Artista: " + artista.getNomeArtista();
    }
}
