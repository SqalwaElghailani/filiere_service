package faculte.filiere.mappers;

import faculte.filiere.dto.RequestFiliereDto;
import faculte.filiere.dto.ResponseFiliereDto;
import faculte.filiere.entities.Filiere;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FiliereMapper {
    public Filiere DTO_to_Entity(RequestFiliereDto requestFiliereDto){
        Filiere filiere = new Filiere();
        BeanUtils.copyProperties(requestFiliereDto,filiere);
        return filiere;
    }

    public ResponseFiliereDto Entity_to_DTO(Filiere filiere){
        ResponseFiliereDto responseFiliereDto= new ResponseFiliereDto();
        BeanUtils.copyProperties(filiere,responseFiliereDto);
        return responseFiliereDto;
    }


}
