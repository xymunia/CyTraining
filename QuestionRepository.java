package v1.team.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import v1.team.model.Question;

//Définir vraiment le repo
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
