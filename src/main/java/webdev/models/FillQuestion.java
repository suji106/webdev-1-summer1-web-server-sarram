package webdev.models;

import javax.persistence.Entity;

@Entity
public class FillQuestion extends Question {
	private String blanks;

	public String getBlanks() {
		return blanks;
	}
	public void setBlanks(String blanks) {
		this.blanks = blanks;
	}
}
