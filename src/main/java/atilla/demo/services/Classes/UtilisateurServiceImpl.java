package atilla.demo.services.Classes;

import atilla.demo.Mappers.UtilisateurMapperImpl;
import atilla.demo.Repositories.UtilisateurRepository;
import atilla.demo.classes.Utilisateur;
import atilla.demo.dto.UtilisateurDto;
import atilla.demo.services.Interfaces.UtilisateurService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    private UtilisateurMapperImpl utilisateurMapper;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, UtilisateurMapperImpl utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    @Override
    public UtilisateurDto inscrire(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = this.utilisateurMapper.toClasse(utilisateurDto);
        Utilisateur utilisateurSaved= this.utilisateurRepository.save(utilisateur);
        return this.utilisateurMapper.toDTo(utilisateurSaved);
    }



    @Override
    public UtilisateurDto rechercherId(int id) {
        Optional<Utilisateur> optionalUtilisateur = this.utilisateurRepository.findById(id);
        Utilisateur utilisateur = optionalUtilisateur.orElseThrow(
                ()-> new EntityNotFoundException("ce id ne se trouve pas dans la BDD"));

        return this.utilisateurMapper.toDTo(utilisateur);
    }

    @Override
    public Stream<UtilisateurDto> afficherAll() {
        List<Utilisateur> utilisateurs = this.utilisateurRepository.findAll();
        Stream<UtilisateurDto> utilisateursDTO = utilisateurs.stream().map(utilisateurMapper::toDTo);
        return utilisateursDTO;
    }

    @Override
    public UtilisateurDto rechercherMail(String mail) {

        Optional<Utilisateur> optionalUtilisateur= this.utilisateurRepository.findByMail(mail);
        Utilisateur utilisateur = optionalUtilisateur.orElseThrow(
                ()-> new EntityNotFoundException("ce mail ne se trouve pas dans la BDD"));

        return this.utilisateurMapper.toDTo(utilisateur);

    }

    @Override
    public UtilisateurDto mettreàJour(int id, UtilisateurDto utilisateurDto) {

            return null ;
    }

    @Override
    public void deleteUtilsateur(int id) {

        this.utilisateurRepository.deleteById(id);

    }
}
