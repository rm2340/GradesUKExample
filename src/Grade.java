
public class Grade {
	public Grade(int percentage) {
		super();
		this.percentage = percentage;
	}
	private int percentage;
	private String letter;
	private String definition;
	
	
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	public int getPercentage() {
		return percentage;
	}
	
	public String getLetter() {
		return letter;
	}

	public String getDegreeClass() {
		if (percentage < 40)
			return "Fail";
		else if (percentage < 50)
			return "Third";
		else if (percentage < 60)
			return "Second Lower";
		else if (percentage < 70)
			return "Second Upper";
		else
			return "First";
	}
	
	public String getDefinition() {
		return definition;
	}
}
