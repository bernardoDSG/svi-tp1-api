package svi.model;

public enum Genero {
    ACAO(1,"Ação"),
    COMEDIA(2,"Comédia"),
    DRAMA(3,"Drama"),
    FICCAO_CIENTIFICA(4,"Ficção Científica"),
    TERROR(5,"Terror"),
    SUSPENSE(6,"Suspense"),
    AVENTURA(7,"Aventura"),
    ROMANCE(8,"Romance"),
    ANIMACAO(9,"Animação"),
    DOCUMENTARIO(10,"Documentário"),
    FANTASIA(11,"Fantasia");

    private final int id;
    private final String nome;

    private Genero(int id,String nome ) {
        this.id = id;
        this.nome = nome; 
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

     public static Genero fromNome(String nome) {
        for (Genero g : values()) {
            if (g.getNome().equalsIgnoreCase(nome.trim())) {
                return g;
            }
        }
        throw new IllegalArgumentException("Nenhum gênero encontrado para o nome informado: " + nome);
    }

}
