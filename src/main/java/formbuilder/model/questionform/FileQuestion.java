package formbuilder.model.questionform;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FILE")
public class FileQuestion extends Question {

	private static final long serialVersionUID = 1L;

	public FileQuestion() {
		super.setDescription("New File Question");
	}

	@Override
	public String getType() {
		return "FILE";
	}

	@Override
	public Question clone() {
		FileQuestion newQuestion = new FileQuestion();

		newQuestion.description = description;
		newQuestion.questionNumber = questionNumber + 1;
		newQuestion.form = form;
		newQuestion.pageNumber = pageNumber;
		newQuestion.tagAttribute.required = tagAttribute.required;
		newQuestion.tagAttribute.type = tagAttribute.type;
		newQuestion.tagAttribute.inputType = tagAttribute.inputType;
		newQuestion.tagAttribute.size = tagAttribute.size;
		newQuestion.tagAttribute.placeholder = tagAttribute.placeholder;
		newQuestion.tagAttribute.rows = tagAttribute.rows;
		newQuestion.tagAttribute.min = tagAttribute.min;
		newQuestion.tagAttribute.max = tagAttribute.max;
		newQuestion.tagAttribute.step = tagAttribute.step;

		return newQuestion;
	}

}
