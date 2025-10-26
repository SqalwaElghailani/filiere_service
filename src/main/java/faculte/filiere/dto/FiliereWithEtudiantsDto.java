package faculte.filiere.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiliereWithEtudiantsDto {
    private ResponseFiliereDto filiere;
    private List<ResponseEtudiantDto> etudiants;
    private Integer nombreEtudiants;

    public FiliereWithEtudiantsDto(ResponseFiliereDto filiereDto, List<ResponseEtudiantDto> etudiants) {
        this.filiere = filiereDto;
        this.etudiants = etudiants;
    }
}
