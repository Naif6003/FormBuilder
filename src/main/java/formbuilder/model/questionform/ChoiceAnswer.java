package formbuilder.model.questionform;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CHOICE")
public class ChoiceAnswer extends Answer {

	private static final long serialVersionUID = 1L;

	// private String[] selections;
	//
	// public ChoiceAnswer() {
	//
	// }
	//
	// public String[] getSelections() {
	// return selections;
	// }
	//
	// public void setSelections(String[] selections) {
	// this.selections = selections;
	// }

	private String selections;

	public String getSelections() {
		return selections;
	}

	public void setSelections(String selections) {
		this.selections = selections;
	}

}
