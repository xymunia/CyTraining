package v3.team.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import v3.team.model.Question;

//Définir vraiment le repo
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
