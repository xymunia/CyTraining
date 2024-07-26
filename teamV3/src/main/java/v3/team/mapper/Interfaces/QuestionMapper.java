package v3.team.mapper.Interfaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import v3.team.dtos.QuestionDto;
import v3.team.model.Question;

@Mapper(componentModel = "spring", uses = UtilisateurMapper.class)
public interface QuestionMapper {

    @Mapping(source="question", target = "question")
    @Mapping(source="correction", target = "correction")
    @Mapping(source = "indBonneRep",target = "indBonneRep")
    @Mapping(source = "indice",target = "indice")
    @Mapping(source = "certifiee",target = "certifiee")
    QuestionDto toDto(Question question);

    @Mapping(source="question", target = "question")
    @Mapping(source="correction", target = "correction")
    @Mapping(source = "indBonneRep",target = "indBonneRep")
    @Mapping(source = "indice",target = "indice")
    @Mapping(source = "certifiee",target = "certifiee")
    Question toClasse (QuestionDto utilisateurDto);
}
