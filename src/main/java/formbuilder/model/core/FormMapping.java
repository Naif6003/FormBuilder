package formbuilder.model.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
	@Table(name = "form_mapping")
	public class FormMapping {

		@Id
	@GeneratedValue
		private int id;


	@Column(name = "userId")
	private String userId;

	@Column(name = "pdfname")
		private String pdfName;

	@Column(name = "appForm")
		private String appForm;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public String getAppForm() {
		return appForm;
	}

	public void setAppForm(String appForm) {
		this.appForm = appForm;
	}

	}

