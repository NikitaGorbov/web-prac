package entity;

public class Vacancy {
	private int vac_id;
	private String requirements;
	private Company company;
	private Position position;
	
	public Vacancy() {
		
	}

	@Override
	public String toString() {
		return "Vacancy [vac_id=" + vac_id + ", requirements=" + requirements + ", company=" + company + ", position="
				+ position + "]";
	}

	public int getVac_id() {
		return vac_id;
	}

	public void setVac_id(int vac_id) {
		this.vac_id = vac_id;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
