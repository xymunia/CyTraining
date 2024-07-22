package v2.teamV2.service;

import org.springframework.beans.factory.annotation.Autowired;
import v2.teamV2.dto.UtilisateurDto;
import v2.teamV2.exception.ExceptionRessourceAbsente;
import v2.teamV2.model.Utilisateur;
import v2.teamV2.mapper.UtilisateurMapper;

import org.springframework.stereotype.Service;
import v2.teamV2.repository.UtilisateurRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UtilisateurService {

    //private List<Utilisateur> utilisateurList;

    @Autowired
    UtilisateurRepository uRepo;


    public UtilisateurService(UtilisateurRepository uRepo) {
        this.uRepo = uRepo;
    }

    //TODO : ajust controllers dto to table join


    public UtilisateurDto createUtilisateur(UtilisateurDto uDto) {
        Utilisateur u = UtilisateurMapper.mapToUtilisateur(uDto);
        Utilisateur savedUtilisateur = uRepo.save(u);
        return UtilisateurMapper.mapToUtilisateurDto(savedUtilisateur);
    }


    public UtilisateurDto getUtilisateurById(int uId) {
        Utilisateur u = uRepo.findById(uId)
                .orElseThrow(()
                        -> new ExceptionRessourceAbsente("Utilisateur data is not associated with given id "+uId));

        return UtilisateurMapper.mapToUtilisateurDto(u);
    }


    public List<UtilisateurDto> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = uRepo.findAll();
        //First map all the corresponding users DTO, then convert to a list
        return utilisateurs.stream().map((u) -> UtilisateurMapper.mapToUtilisateurDto(u))
                .collect(Collectors.toList());
    }


    public UtilisateurDto updateUtilisateur(int uId, UtilisateurDto updatedUtilisateur) {
        Utilisateur u = uRepo.findById(uId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucun utilisateur associé à l'id "+uId)
        );
        //Change data with getters and setters
        u.setNom(updatedUtilisateur.getNom());
        u.setEmail(updatedUtilisateur.getEmail());
        u.setMdp(updatedUtilisateur.getMdp());
        //TODO : le nb de questions certifiées
//        u.setQuestionsCertifiees(updatedUtilisateur.getQuestionsCertifiees());
        //
        Utilisateur updatedUtilisateurObj = uRepo.save(u);

        return UtilisateurMapper.mapToUtilisateurDto(updatedUtilisateurObj);
    }


    public void deleteUtilisateur(int uId) {
        Utilisateur u = uRepo.findById(uId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucun utilisateur associé à l'id "+uId)
        );
        //
        uRepo.deleteById(uId);
    }



    //TODO : ajust userService

}