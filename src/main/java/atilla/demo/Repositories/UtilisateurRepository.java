package atilla.demo.Repositories;

import atilla.demo.classes.Admin;
import atilla.demo.classes.AdminSup;
import atilla.demo.classes.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByMail (String mail);

    @Query( "SELECT u FROM Utilisateur u WHERE TYPE(u) = Admin")
    List<Admin> findallAdmins();


   /* @Query ( "Select u from Utilisateur u where TYPE(u) = AdminSup")
    AdminSup chercherAdminSup ();*/
}
