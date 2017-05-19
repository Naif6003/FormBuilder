package formbuilder.model.questionform.dao;

import java.util.List;

import formbuilder.model.core.FormMapping;
import formbuilder.model.core.User;
import formbuilder.model.pdfform.Pdf;
import formbuilder.model.pdfform.PdfField;
import formbuilder.model.questionform.Answer;
import formbuilder.model.questionform.ChoiceAnswer;
import formbuilder.model.questionform.ChoiceQuestion;
import formbuilder.model.questionform.Form;
import formbuilder.model.questionform.Question;
import formbuilder.model.questionform.TextAnswer;
import formbuilder.model.questionform.TextQuestion;

public interface FormDao {

	Form getForm(int id);

	Question getQuestion(int qId);

	List<Form> getForms();

	Form saveForm(Form form);

	void deleteForm(int id);

	Question saveQuestion(Question question);

	Question getQuestion(int questionNumber, int pageNumber);

	TextQuestion getTextQuestion(int qId);

	ChoiceQuestion getChoiceQuestion(int qId);

	List<Answer> getAnswers(User user);

	List<TextAnswer> getUserAnswers(int userId, String answerType);

	List<ChoiceAnswer> getChoiceAnswers(int userId, String answerType);

	Answer getAnswer(User user, Question question);

	Answer saveAnswer(Answer answer);

	void savePdfField(PdfField pdffield);

	void savePdf(Pdf pdf);

	List<PdfField> getFields(Integer formId);

	List<ChoiceAnswer> getChoiceAnswer();

	void saveFormMap(FormMapping formMap);

	FormMapping getUserFormMapping(Integer id);


}
