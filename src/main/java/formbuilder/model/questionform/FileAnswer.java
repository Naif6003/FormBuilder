package formbuilder.model.questionform;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
@DiscriminatorValue("FILE")
public class FileAnswer extends Answer {

	private static final long serialVersionUID = 1L;

	@ElementCollection
	@CollectionTable(name = "answer_files", joinColumns = @JoinColumn(name = "answer_id"))
	private List<String> files;

	public FileAnswer() {
		files = new ArrayList<String>();
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}



}
