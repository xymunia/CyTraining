package v1.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import v1.team.dtos.UtilisateurDto;
import v1.team.exceptions.ExceptionRessourceAbsente;
import v1.team.model.Utilisateur;
import v1.team.mapper.UtilisateurMapper;

import org.springframework.stereotype.Service;
import v1.team.repositories.UtilisateurRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    //private List<Utilisateur> utilisateurList;

    @Autowired
    UtilisateurRepository uRepo;


    public UtilisateurServiceImpl(UtilisateurRepository uRepo) {
        this.uRepo = uRepo;
    }

    //TODO : ajust controllers dto to table join

    @Override
    public UtilisateurDto createUtilisateur(UtilisateurDto uDto) {
        Utilisateur u = UtilisateurMapper.mapToUtilisateur(uDto);
        Utilisateur savedUtilisateur = uRepo.save(u);
        return UtilisateurMapper.mapToUtilisateurDto(savedUtilisateur);
    }

    @Override
    public UtilisateurDto getUtilisateurById(int uId) {
        Utilisateur u = uRepo.findById(uId)
                .orElseThrow(()
                        -> new ExceptionRessourceAbsente("Utilisateur data is not associated with given id "+uId));

        return UtilisateurMapper.mapToUtilisateurDto(u);
    }

    @Override
    public List<UtilisateurDto> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = uRepo.findAll();
        //First map all the corresponding users DTO, then convert to a list
        return utilisateurs.stream().map((u) -> UtilisateurMapper.mapToUtilisateurDto(u))
                .collect(Collectors.toList());
    }

    @Override
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

    @Override
    public void deleteUtilisateur(int uId) {
        Utilisateur u = uRepo.findById(uId).orElseThrow(
                () -> new ExceptionRessourceAbsente("Aucun utilisateur associé à l'id "+uId)
        );
        //
        uRepo.deleteById(uId);
    }



    //TODO : ajust userService

}
