package faculte.filiere.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEtudiantDto {
    private Integer id;
    private String nom;
    private String prenom;
    private String cne;
}
