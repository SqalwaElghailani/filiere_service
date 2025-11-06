
package faculte.filiere.web;

import faculte.filiere.dto.FiliereWithEtudiantsDto;
import faculte.filiere.dto.RequestFiliereDto;
import faculte.filiere.dto.ResponseFiliereDto;
import faculte.filiere.service.FiliereServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des Filieres",
                description = "cette offre tous les méthodes pour gérer les filiere",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8082"
        )
)
@RestController
@RequestMapping("/v1/filieres")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiRestFull {

    private final FiliereServiceImpl filiereServiceImpl;

    public ApiRestFull(FiliereServiceImpl filiereServiceImpl) {
        this.filiereServiceImpl = filiereServiceImpl;
    }
    @Operation(
            summary = " Ajouter un filiere",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestFiliereDto.class )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien enregiter",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDto.class )
                            )
                    ),

                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseFiliereDto> add(@RequestBody RequestFiliereDto requestFiliereDto) {
        ResponseFiliereDto response = filiereServiceImpl.Add_Filiere(requestFiliereDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = " récuperer liste des filiere",

            responses = {
                    @ApiResponse(responseCode = "200", description = "bien enregiter",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseFiliereDto.class ))
                            )
                    ),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_USER')")

    public ResponseEntity<List<ResponseFiliereDto>> getAll() {
        List<ResponseFiliereDto> filieres = filiereServiceImpl.GetAllFilieres();
        return ResponseEntity.ok(filieres);
    }

    @Operation(
            summary = " récupérer filiere par Id",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien récuperer",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDto.class )
                            )
                    ),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_USER')")

    public ResponseEntity<ResponseFiliereDto> getById(@PathVariable Integer id) {
        ResponseFiliereDto response = filiereServiceImpl.GetFiliereByID(id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = " Modifier un filiere",
            parameters = @Parameter(name = "id", required = true),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestFiliereDto.class )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien modifier",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDto.class )
                            )
                    ),

                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ResponseFiliereDto> update(
            @PathVariable Integer id,
            @RequestBody RequestFiliereDto requestFiliereDto) {
        ResponseFiliereDto response = filiereServiceImpl.Update_Filiere(id, requestFiliereDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = " supprimer filiere par Id",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien supprimer"),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        filiereServiceImpl.DeleteFiliereByID(id);
        return ResponseEntity.ok().build();
    }
    @Operation(
            summary = "tout les filiere avec les etudiant",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "bien récuperer",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FiliereWithEtudiantsDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "4xx",description = "erreur client"),
                    @ApiResponse(responseCode = "5xx",description = "erreur serveur"),
            }
    )
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_USER')")
    @GetMapping("/{id}/with-etudiants")
    public ResponseEntity<FiliereWithEtudiantsDto> getFiliereWithEtudiants(@PathVariable Integer id) {
        FiliereWithEtudiantsDto response = filiereServiceImpl.getFiliereWithEtudiants(id);
        return ResponseEntity.ok(response);
    }



}