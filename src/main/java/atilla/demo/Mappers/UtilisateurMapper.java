package atilla.demo.Mappers;


import atilla.demo.classes.Admin;
import atilla.demo.classes.AdminSup;
import atilla.demo.classes.Utilisateur;
import atilla.demo.dto.AdminDto;
import atilla.demo.dto.AdminSupDto;
import atilla.demo.dto.UtilisateurDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {FiliereMapper.class})
public interface UtilisateurMapper {

    @BeanMapping(builder = @Builder( disableBuilder = true ))
    @Mapping(source="nom", target = "nom")
    @Mapping(source="prenom", target = "prenom")
    @Mapping(source = "mail",target = "email")
    //@Mapping(source="filiere", target = "filiere")
    @SubclassMapping(source = Admin.class, target = AdminDto.class)
    @SubclassMapping(source = AdminSup.class, target = AdminSupDto.class)
    UtilisateurDto toDTo (Utilisateur utilisateur);

    @Mapping(source="nom", target = "nom")
    @Mapping(source="prenom", target = "prenom")
    //@Mapping(source="filiere", target = "filiere")
    @Mapping(source = "email",target = "mail")
    //@Mapping(target = "nbApprouve", source = "Admin.nbApprouve")
    @Mapping(source="filiere", target = "filiere")
    @SubclassMapping(source = AdminDto.class, target = Admin.class)
    @SubclassMapping(source = AdminSupDto.class, target = AdminSup.class)
    @BeanMapping(builder = @Builder( disableBuilder = true ))

    //@Mapping(target = "dtype", constant = "UTILISATEUR")
    Utilisateur toClasse (UtilisateurDto utilisateurDto);

    @Mapping(source="nbApprouve", target = "nbApprouve")
    @Mapping(source = "email",target = "mail")

    //@Mapping(target = "dtype", constant = "true")
    Admin toClasseA(AdminDto adminDto);


    @Mapping(source="nbApprouve", target = "nbApprouve")
    @Mapping(source = "mail",target = "email")
    AdminDto toDtoA(Admin admin);

    AdminSup toDtoS(AdminSupDto adminSupDto);
    AdminSupDto toClasseSup(AdminSup adminSup);

    @Mapping(target = "nbApprouve", source = "nbApprouve", ignore = true)
    UtilisateurDto toDto( Admin admin);

    AdminDto toDtoA(Utilisateur utilisateur);



}
