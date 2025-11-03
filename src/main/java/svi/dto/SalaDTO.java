package svi.dto;

import java.util.List;

import svi.model.Poltrona;

public record SalaDTO(String nome, List<Poltrona> listaPoltronas) {

}
