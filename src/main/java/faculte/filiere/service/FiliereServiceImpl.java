package faculte.filiere.service;

import faculte.filiere.dto.FiliereWithEtudiantsDto;
import faculte.filiere.dto.RequestFiliereDto;
import faculte.filiere.dto.ResponseEtudiantDto;
import faculte.filiere.dto.ResponseFiliereDto;
import faculte.filiere.entities.Filiere;
import faculte.filiere.mappers.FiliereMapper;
import faculte.filiere.repository.FiliereRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FiliereServiceImpl implements FiliereService{
    private FiliereRepository filiereRepository;
    private FiliereMapper filiereMapper;
    private  RestTemplate restTemplate;

    public FiliereServiceImpl(FiliereRepository filiereRepository, FiliereMapper filiereMapper,RestTemplate restTemplate) {
        this.filiereRepository = filiereRepository;
        this.filiereMapper = filiereMapper;
        this.restTemplate = restTemplate;
    }
    private List<ResponseEtudiantDto> getEtudiantsByFiliereId(Integer filiereId) {
        try {
            String url = "http://localhost:8083/v1/etudiants/filieres/" + filiereId ;
            ResponseEtudiantDto[] etudiantsArray = restTemplate.getForObject(url, ResponseEtudiantDto[].class);
            return etudiantsArray != null ? Arrays.asList(etudiantsArray) : List.of();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching students for filiere id: " + filiereId);
        }
    }
    public FiliereWithEtudiantsDto getFiliereWithEtudiants(Integer id) {
        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filiere not found with id: " + id));

        ResponseFiliereDto filiereDto = filiereMapper.Entity_to_DTO(filiere);
        List<ResponseEtudiantDto> etudiants = getEtudiantsByFiliereId(id);

        return new FiliereWithEtudiantsDto(filiereDto, etudiants);
    }
    @Override
    public ResponseFiliereDto Add_Filiere(RequestFiliereDto request) {
        Filiere filiere = filiereMapper.DTO_to_Entity(request);
        Filiere savedFiliere = filiereRepository.save(filiere);
        return filiereMapper.Entity_to_DTO(savedFiliere);
    }

    @Override
    public List<ResponseFiliereDto> GetAllFilieres() {
        List<Filiere> filieres = filiereRepository.findAll();
        List<ResponseFiliereDto> filiereDTos= new ArrayList<>();

        for (Filiere c: filieres){
            filiereDTos.add(filiereMapper.Entity_to_DTO(c));
        }
        return filiereDTos;

    }

    @Override
    public ResponseFiliereDto GetFiliereByID(Integer id) {
        Filiere filiere = filiereRepository.findById(id).orElseThrow();
        return filiereMapper.Entity_to_DTO(filiere);
    }

    @Override
    public ResponseFiliereDto Update_Filiere(Integer id, RequestFiliereDto request) {
        Filiere new_filiere = filiereMapper.DTO_to_Entity(request);
        Filiere filiere = filiereRepository.findById(id).orElseThrow();
        if(new_filiere.getCode() != null){
            filiere.setCode(new_filiere.getCode());
        }
        if(new_filiere.getLibelle() != null){
            filiere.setLibelle(new_filiere.getLibelle());
        }
        Filiere saved_filiere = filiereRepository.save(filiere);
        return filiereMapper.Entity_to_DTO(saved_filiere);
    }

    @Override
    public void DeleteFiliereByID(Integer id) {
        filiereRepository.deleteById(id);

    }
}
