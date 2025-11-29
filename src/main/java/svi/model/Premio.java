package svi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Premio {
    OSCAR(1,"Oscar"),
    PALMADEOURO(2,"Palma de Ouro(Festival de Cannes)"),
    LEAODEOURO(3,"Leão de Ouro(Festival de Veneza)"),
    URSODEOURO(4,"Urso de Ouro(Festival de Berlim)"),
    BAFTA(5,"British Academy Film Awards"),
    GLOBODEOURO(6,"Globo de Ouro");

    private final int id;
    private final String nome;

    private Premio(int id,String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    @JsonValue
    public String getNome() {
        return nome;
    }
    @JsonCreator
    public static Premio fromNome (String nome) {
        int i = 0;
        Premio [] vetorPremios = Premio.values();
        boolean achouEnum = false;
        Premio premioAchado = null;
        while (achouEnum == false && i < vetorPremios.length) {
            if(vetorPremios[i].getNome().equals(nome) == true) {
                premioAchado = vetorPremios[i];
                achouEnum = true;
            }
            i++;       
        }
        if(premioAchado != null) {
            return premioAchado;
        }
        else{
            throw new IllegalArgumentException("nenhum enum foi encontrado para o nome informado.");
        }
    }
   
    
}
