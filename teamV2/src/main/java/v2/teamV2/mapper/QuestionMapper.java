package v2.teamV2.mapper;

import v2.teamV2.dto.QuestionDto;
import v2.teamV2.model.Question;

//Réel mapping
//@Component
public class QuestionMapper {

    //TODO : ajust controllers dto to table join

    public static QuestionDto mapToQuestionDto(Question q) {
        return new QuestionDto(
                q.getId(),
                q.getQuestion(),
                q.getCorrection(),
                q.getReponses(),
                q.getIndBonneRep(),
                q.getIndice(),
                q.getApprouvee(),
                q.getCreateur()
        );
    }

    public static Question mapToQuestion(QuestionDto qDto) {
        return new Question(
                qDto.getId(),
                qDto.getQuestion(),
                qDto.getCorrection(),
                qDto.getReponses(),
                qDto.getIndBonneRep(),
                qDto.getIndice(),
                qDto.getApprouvee(),
                qDto.getCreateur()
        );
    }

}
