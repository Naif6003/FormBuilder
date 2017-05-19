package formbuilder.model.questionform.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
import formbuilder.model.questionform.dao.FormDao;

@Repository
@Transactional
public class FormDaoImpl implements FormDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Form getForm(int id) {
		return entityManager.find(Form.class, id);
	}

	@Override
	public List<Form> getForms() {
		return entityManager.createQuery("from Form order by id", Form.class).getResultList();
	}

	@Override
	public Form saveForm(Form form) {
		return entityManager.merge(form);
	}

	@Override
	public Question saveQuestion(Question question) {
		return entityManager.merge(question);
	}

	@Override
	public void deleteForm(int id) {
		entityManager.remove(getForm(id));
	}

	@Override
	public Question getQuestion(int qId) {
		return entityManager.find(Question.class, qId);
	}

	@Override
	public Question getQuestion(int questionNumber, int pageNumber) {
		List<Question> questions = entityManager
				.createQuery("from Question where questionNumber = :questionNumber and pageNumber = :pageNumber",
						Question.class)
				.setParameter("questionNumber", questionNumber).setParameter("pageNumber", pageNumber).getResultList();
		return questions.size() == 0 ? null : questions.get(0);
	}

	@Override
	public TextQuestion getTextQuestion(int qId) {
		return entityManager.find(TextQuestion.class, qId);
	}

	@Override
	public ChoiceQuestion getChoiceQuestion(int qId) {
		return entityManager.find(ChoiceQuestion.class, qId);
	}

	@Override
	public List<Answer> getAnswers(User user) {
		List<Answer> answers = entityManager
				.createQuery(
						"from Answer where user = :user order by question.pageNumber, question.questionNumber",
						Answer.class)
				.setParameter("user", user)
				.getResultList();
		return (answers == null) ? new ArrayList<Answer>() : answers;
	}

	@Override
	public List<TextAnswer> getUserAnswers(int userId, String answerType) {
		return entityManager.createQuery(
				"from TextAnswer where user_id = :user_id and answer_type = :answer_type order by question.pageNumber, question.questionNumber",
				TextAnswer.class).setParameter("user_id", userId).setParameter("answer_type", answerType)
				.getResultList();

	}

	@Override
	public List<ChoiceAnswer> getChoiceAnswers(int userId, String answerType) {
		return entityManager
				.createQuery(
						"from ChoiceAnswer where user_id = :user_id and answer_type = :answer_type order by question.pageNumber, question.questionNumber",
						ChoiceAnswer.class)
				.setParameter("user_id", userId).setParameter("answer_type", answerType).getResultList();

	}

	@Override
	public Answer getAnswer(User user, Question question) {
		List<Answer> answers = entityManager
				.createQuery("from Answer where user = :user and question = :question", Answer.class)
				.setParameter("user", user).setParameter("question", question).getResultList();
		return answers.size() == 0 ? null : answers.get(0);
	}

	@Override
	public Answer saveAnswer(Answer answer) {
		return entityManager.merge(answer);
	}

	@Override
	public void savePdfField(PdfField pdffield) {
		entityManager.merge(pdffield);
	}

	@Override
	public void savePdf(Pdf pdf) {
		entityManager.merge(pdf);
	}

	@Override
	public List<PdfField> getFields(Integer formId) {
		return entityManager.createQuery("from PdfField p where formid = :formid", PdfField.class)
				.setParameter("formid", formId).getResultList();
	}

	@Override
	public List<ChoiceAnswer> getChoiceAnswer() {
		return entityManager.createQuery("from ChoiceAnswer order by id", ChoiceAnswer.class).getResultList();
	}

	@Override
	public void saveFormMap(FormMapping formMap) {
		entityManager.merge(formMap);
		
	}

	@Override
	public FormMapping getUserFormMapping(Integer id) {
		return entityManager.createQuery("from FormMapping where userId = :userId", FormMapping.class)
				.setParameter("userId", id).getSingleResult();
		}

}
