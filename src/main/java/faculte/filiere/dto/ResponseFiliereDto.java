package faculte.filiere.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFiliereDto {
    private Integer idFiliere;
    private String code;
    private String libelle;
}
