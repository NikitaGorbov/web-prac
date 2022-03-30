package entity;

public class Vacancy {
	private long vac_id;
	private String requirements;
	private Company company;
	private Position position;
	private int salary;
	private int exp_required;
	
	public Vacancy() {
		
	}

	@Override
	public String toString() {
		return "Vacancy [vac_id=" + vac_id + ", requirements=" + requirements + ", company=" + company + ", position="
				+ position + "]";
	}

	public long getVac_id() {
		return vac_id;
	}

	public void setVac_id(long vac_id) {
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

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getExp_required() {
		return exp_required;
	}

	public void setExp_required(int exp_required) {
		this.exp_required = exp_required;
	}
}
