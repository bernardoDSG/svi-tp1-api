package svi.model;

public enum TipodeMeia {
    ESTUDANTE(1,"Estudante"),
    IDOSO(2,"Idoso"),
    PCD(3,"Pessoas com Deficiência"),
    CRIANCA(4,"Criança");

    private final int id;
    private final String nome;

    private TipodeMeia(int id,String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static TipodeMeia fromNome (String nome) {
        int i = 0;
        TipodeMeia [] vetorTiposDeMeia = TipodeMeia.values();
        boolean achouTipoDeMeia = false;
        TipodeMeia tipoDeMeiaAchado = null;
        while (achouTipoDeMeia == false && i < vetorTiposDeMeia.length) {
            if(vetorTiposDeMeia[i].getNome().equals(nome) == true) {
                achouTipoDeMeia = true;
                tipoDeMeiaAchado = vetorTiposDeMeia[i];
            }
            i++;
        }
        if(tipoDeMeiaAchado != null) {
            return tipoDeMeiaAchado;
        }
        else {
            throw new IllegalArgumentException("nenhum tipo de meia foi encontrado para o nome fornecido.");
        }
    }

    
}
