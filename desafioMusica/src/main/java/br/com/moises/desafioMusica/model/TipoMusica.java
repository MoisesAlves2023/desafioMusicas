package br.com.moises.desafioMusica.model;

public enum TipoMusica {
    SERTANEJO("Sertanejo"),
    FUNK("Funk"),
    ROCK( "Rock"),
    POP("Pop"),
    HIPHOP("HipHop"),
    ELETRONICA("Eletronica");

    private String tipoMusica;

    TipoMusica(String tipoMusica){
        this.tipoMusica = tipoMusica;
    }


    public static TipoMusica tipoMusica(String text) {
        for (TipoMusica categoria : TipoMusica.values()) {
            if (categoria.tipoMusica.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}
