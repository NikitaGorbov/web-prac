package entity;

import java.util.Set;

public class Company {
	private Long comp_id;
	private String comp_name;
	private String description;
	private String location;
	private Set<Vacancy> vacancies;
	private Set<Previous_job_record> previous_job_records;
	
	public Company() {
		
	}

	public Long getComp_id() {
		return comp_id;
	}

	public void setComp_id(Long id) {
		this.comp_id = id;
	}

	@Override
	public String toString() {
		return "Company [id=" + comp_id + ", comp_name=" + comp_name + ", description=" + description + ", location="
				+ location + "]";
	}

	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
