package entity;

public class Cv {
	private Long cv_id;
	private int work_exp;
	private Applicant applicant;
	private Position objective;
	private int desired_salary;

	public Cv() {

	}

	public Long getCv_id() {
		return cv_id;
	}

	public void setCv_id(Long cv_id) {
		this.cv_id = cv_id;
	}

	public int getWork_exp() {
		return work_exp;
	}

	public void setWork_exp(int work_exp) {
		this.work_exp = work_exp;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

//	public Position getPosition() {
//		return position;
//	}
//
//	public void setPosition(Position position) {
//		this.position = position;
//	}

	public Position getObjective() {
		return objective;
	}

	public void setObjective(Position objective) {
		this.objective = objective;
	}

	public int getDesired_salary() {
		return desired_salary;
	}

	public void setDesired_salary(int desired_salary) {
		this.desired_salary = desired_salary;
	}

	@Override
	public String toString() {
		return "Cv [cv_id=" + cv_id + ", work_exp=" + work_exp
				+ "]";
	}

}
