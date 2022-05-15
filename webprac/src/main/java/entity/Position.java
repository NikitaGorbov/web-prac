package entity;

import java.util.Set;

public class Position {
	private Long pos_id;
	private String position_name;
	private Set<Cv> cvs;
	private Set<Vacancy> vacancies;
	private Set<Previous_job_record> previous_job_records;

	public Position() {

	}

	@Override
	public String toString() {
		return "Position [pos_id=" + pos_id + ", position_name=" + position_name + "]";
	}

	public Long getPos_id() {
		return pos_id;
	}

	public void setPos_id(Long pos_id) {
		this.pos_id = pos_id;
	}

	public String getPosition_name() {
		return position_name;
	}

	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}

	public Set<Cv> getCvs() {
		return cvs;
	}

	public void setCvs(Set<Cv> cvs) {
		this.cvs = cvs;
	}

	public Set<Vacancy> getVacancies() {
		return vacancies;
	}

	public void setVacancies(Set<Vacancy> vacancies) {
		this.vacancies = vacancies;
	}

	public Set<Previous_job_record> getPrevious_job_records() {
		return previous_job_records;
	}

	public void setPrevious_job_records(Set<Previous_job_record> previous_job_records) {
		this.previous_job_records = previous_job_records;
	}
}
