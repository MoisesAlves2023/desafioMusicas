package br.com.moises.desafioMusica.principal;

import br.com.moises.desafioMusica.model.Artista;
import br.com.moises.desafioMusica.model.Musica;
import br.com.moises.desafioMusica.model.TipoMusica;
import br.com.moises.desafioMusica.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ArtistaRepository repositorio;

    public Principal(ArtistaRepository repository) {
        this.repositorio = repository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                  1 - Cadastrar artistas
                  2 - Cadastrar músicas                                              
                  3 - Listar artistas cadastrados
                  4 - Buscar músicas por artistas
                  
                  
                  9 - Sair                            
                    """;

            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarArtistas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }


    private void cadastrarArtistas() {
        System.out.println("Digite o nome do artista: ");
        var nomeArtista = scanner.nextLine();
        System.out.println("Digite o tipo de musica do artista(Sertanejo, rock etc: ");
        TipoMusica tipoMusica = TipoMusica.valueOf(scanner.nextLine().toUpperCase());
        Artista artista = new Artista(nomeArtista.toUpperCase(), tipoMusica);
        repositorio.save(artista);
        System.out.println(artista);
    }

    private void cadastrarMusicas() {
        listarArtistas();
        System.out.println("Digite o nome do artista que deseja cadastrar a música: ");
        var nomeArtista = scanner.nextLine().toUpperCase();
        System.out.println("Digite o nome da música que deseja cadastrar do artista: ");
        var nomeMusica = scanner.nextLine().toUpperCase();

        // Encontre o artista pelo nome
        Optional<Artista> artistaOpt = repositorio.findByNomeArtistaContainingIgnoreCase(nomeArtista);

        if (artistaOpt.isPresent()) {
            var artistaEncontrado = artistaOpt.get();

            // Crie uma nova música e associe ao artista encontrado
            Musica novaMusica = new Musica();
            novaMusica.setNomeMusica(nomeMusica); // setando o nome da música
            novaMusica.setArtista(artistaEncontrado); // associando o artista

            // Adicionando a nova música à lista de músicas do artista
            artistaEncontrado.getMusicas().add(novaMusica);

            // Salve a nova música (ou o artista, o que irá atualizar a lista)
            repositorio.save(artistaEncontrado); // Isso também salva a nova música por causa do cascade

            System.out.println("Música cadastrada com sucesso!");
        } else {
            System.out.println("Artista não encontrado. Tente usar uma parte do nome do artista.");
        }
    }

    private void listarArtistas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(System.out::println);
    }

    private void buscarMusicasPorArtista() {
        listarArtistas();
        System.out.println("Digite o nome do artista que deseja buscar as musicas: ");
        var nomeArtista = scanner.nextLine().toUpperCase();
        // Encontre o artista pelo nome
        Optional<Artista> artistaOpt = repositorio.findByNomeArtistaContainingIgnoreCase(nomeArtista);

        if (artistaOpt.isPresent()){
            var artistaEncontrado = artistaOpt.get();
            var idArtista = artistaEncontrado.getId();

            List<Musica> musicas = repositorio.findMusicasByArtistaId(idArtista);

            if (musicas.isEmpty()){
                System.out.println("Nenhuma musica encontrada pra esse artista");
            }else {
                musicas.forEach(System.out::println);
            }
        }

    }
}
