package svi.dto;



import svi.converter.ConverterPremioString;
import svi.model.Ator;


public record AtorDTOResponse(
    Long id,
    String nome,
    String premios
) {

    public static AtorDTOResponse valueOf(Ator ator) {
        ConverterPremioString converter = new ConverterPremioString();

        return new AtorDTOResponse(ator.getId()
        , ator.getNome()
        , converter.convertToDatabaseColumn(ator.getPremios()));
    }
}
