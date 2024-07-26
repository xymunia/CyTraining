package atilla.demo.services.Interfaces;


import atilla.demo.classes.UE;
import atilla.demo.dto.FiliereDto;
import atilla.demo.dto.UeDto;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;


public interface UeService {


    UeDto ajouterUe(UeDto ueDto);
    Stream<UeDto> afficherAll();
    UeDto rechercherId ( int id );
    UeDto rechercherNom ( String nom);

    void deleteUE (int id);

    void modifierUe(int id , UeDto ueDto);


}
