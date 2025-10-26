package faculte.filiere.service;

import faculte.filiere.dto.RequestFiliereDto;
import faculte.filiere.dto.ResponseFiliereDto;

import java.util.List;

public interface FiliereService {
    public ResponseFiliereDto Add_Filiere(RequestFiliereDto request);
    public List<ResponseFiliereDto> GetAllFilieres();
    public ResponseFiliereDto GetFiliereByID(Integer id);
    public ResponseFiliereDto Update_Filiere(Integer id,RequestFiliereDto request);
    public void DeleteFiliereByID(Integer id);
}
