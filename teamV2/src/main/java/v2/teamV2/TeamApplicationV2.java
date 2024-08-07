package v2.teamV2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import v2.teamV2.model.Question;
import v2.teamV2.model.Utilisateur;
import v2.teamV2.repository.QuestionRepository;
import v2.teamV2.repository.UtilisateurRepository;

import java.util.List;

@SpringBootApplication
public class TeamApplicationV2 implements CommandLineRunner {

	@Autowired
	UtilisateurRepository uRepo;

	@Autowired
	QuestionRepository qRepo;

	public static void main(String[] args) {
		SpringApplication.run(TeamApplicationV2.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Utilisateur u1 = new Utilisateur(0, "user1", "u1@cy-tech.fr", "protecc",null);

//        Question q1 = new Question(0, "Quelle est la derivee de x^2 ?", "C'est 2x", List.of(new String[]{"2", "2x", "x"}), 2,
//                "Utilisez la règle des puissances", 0,u1);

		uRepo.save(u1);

//        q1.toString();
//        q1.toString();
//
//        qRepo.save(q1);
	}



}
