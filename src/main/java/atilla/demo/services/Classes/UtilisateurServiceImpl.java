package atilla.demo.services.Classes;

import atilla.demo.Mappers.UtilisateurMapperImpl;
import atilla.demo.Repositories.UtilisateurRepository;
import atilla.demo.classes.Admin;
import atilla.demo.classes.Filiere;
import atilla.demo.classes.Utilisateur;
import atilla.demo.dto.AdminDto;
import atilla.demo.dto.AdminSupDto;
import atilla.demo.dto.UtilisateurDto;
import atilla.demo.services.Interfaces.UtilisateurService;
import jakarta.annotation.PostConstruct;
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
    @Autowired
    private UtilisateurMapperImpl utilisateurMapper;
    @Autowired
    private FiliereServiceImpl filiereService;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, UtilisateurMapperImpl utilisateurMapper, FiliereServiceImpl filiereService) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
        this.filiereService = filiereService;
    }

    @Override
    public UtilisateurDto inscrire(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = this.utilisateurMapper.toClasse(utilisateurDto);
        Utilisateur utilisateurSaved= this.utilisateurRepository.save(utilisateur);
        return this.utilisateurMapper.toDTo(utilisateurSaved);
    }

    @Override
    public UtilisateurDto inscrire1(UtilisateurDto utilisateurDto) {
        Filiere filiere = utilisateurDto.getFiliere();
        Utilisateur utilisateur = this.utilisateurMapper.toClasse(utilisateurDto);
        Filiere filiereBDD = this.filiereService.getOrCreate(filiere);
        utilisateur.setFiliere(filiereBDD);
        Utilisateur utilisateurBDD = utilisateurRepository.save(utilisateur);
        return this.utilisateurMapper.toDTo(utilisateurBDD);

    }


    @Override
    public AdminDto inscrire2(AdminDto adminDto) {

        Filiere filiere = filiereService.getOrCreate(adminDto.getFiliere());
        Admin admin;

        admin = utilisateurMapper.toClasseA(adminDto);

        admin.setFiliere(filiere);

        Admin savedAdmin = utilisateurRepository.save(admin);

        return utilisateurMapper.toDtoA(savedAdmin);
    }

    @Override
    public AdminSupDto inscrire3(AdminSupDto adminSupDto) {
        return null;
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

    public Stream<AdminDto> afficherAdmins() {
        List<Admin> admins = this.utilisateurRepository.findallAdmins();

        Stream<AdminDto> adminsDto = admins.stream().map(utilisateurMapper::toDtoA);
        return adminsDto ;

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
        return null;
    }

    @Override
    public void deleteUtilsateur(int id) {
        this.utilisateurRepository.deleteById(id);

    }

    @Override
    public void modifierUtilisateur(int id, UtilisateurDto utilisateurDto) {

        UtilisateurDto utilisateurDtoBdd = rechercherId(id); // Cette méthode lance une exception si l'utilisateur n'est pas trouvé
        Utilisateur utilisateurBDD = this.utilisateurMapper.toClasse(utilisateurDtoBdd);


        utilisateurBDD.setMail(utilisateurDto.getEmail());
        // utilisateurBDD.setMdp(utilisateurDto.getMdp());
        utilisateurBDD.setNom(utilisateurDto.getNom());
        utilisateurBDD.setPrenom(utilisateurDto.getPrenom());
        utilisateurBDD.setNbQuestionsPropose(utilisateurDto.getNbQuestionsPropose());
        utilisateurBDD.setNbQuestionsValide(utilisateurDto.getNbQuestionsValide());


        if (utilisateurDto.getFiliere() != null) {
            utilisateurBDD.setFiliere(utilisateurDto.getFiliere());
        }

        // Sauvegarder les modifications
        this.utilisateurRepository.save(utilisateurBDD);

    }

    /*@PostConstruct
    public void init() {
        // Ajout de données de test
        Utilisateur user = new Utilisateur();
        user.setNom("User1");
        utilisateurRepository.save(user);

        Admin admin = new Admin();
        admin.setNom("Admin1");
        admin.setNbApprouve(7);
        utilisateurRepository.save(admin);
    }*/
}

