package atilla.demo;

import atilla.demo.Mappers.FiliereMapper;
import atilla.demo.Repositories.FiliereRepository;
import atilla.demo.classes.Filiere;
import atilla.demo.classes.Utilisateur;
import atilla.demo.dto.FiliereDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CytrainingApplication implements CommandLineRunner {
	@Autowired
	FiliereRepository filiereRepository ;
	//FiliereMapper filiereMapper;

	public static void main(String[] args) {SpringApplication.run(CytrainingApplication.class, args);}

	@Override
	public void run(String... args) throws Exception {

		/*Filiere filiere1 = new Filiere(18, "qnjk");
		System.out.println(filiere1);
		filiere1.toString();
		//filiereRepository.save(filiere1);
		Filiere filiere2 = new Filiere(21, "qnjk");*/
		//FiliereDto filiereDto1 = filiereMapper.toDto(filiere1);


		//filiereRepository.save(filiere2);

	}
}
