package atilla.demo.Mappers;


import atilla.demo.classes.Utilisateur;
import atilla.demo.dto.UtilisateurDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    @Mapping(source="nom", target = "nom")
    @Mapping(source="prenom", target = "prenom")
    @Mapping(source = "mail",target = "email")
    @Mapping(source="filiere", target = "filiere")
    UtilisateurDto toDTo (Utilisateur utilisateur);

    @Mapping(source="nom", target = "nom")
    @Mapping(source="prenom", target = "prenom")
    @Mapping(source="filiere", target = "filiere")
    @Mapping(source = "email",target = "mail")
    Utilisateur toClasse (UtilisateurDto utilisateurDto);

}
