package UsingEmbededAnnotation;

import jakarta.persistence.Embeddable;

@Embeddable
public class Certificate {
	private String Certificate_Name;
	private int duration;

	public String getCrName() {
		return Certificate_Name;
	}

	public void setCrName(String Certificate_Name) {
		this.Certificate_Name = Certificate_Name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
