package entity;

import java.util.Set;

public class Education {
	private Long ed_id;
	private String name;
	private Set<Applicant> applicants;

	public Education() {

	}

	public Long getEd_id() {
		return ed_id;
	}

	public void setEd_id(Long ed_id) {
		this.ed_id = ed_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Education [ed_id=" + ed_id + ", name=" + name + "]";
	}

	public Set<Applicant> getApplicants() {
		return applicants;
	}

	public void setApplicants(Set<Applicant> applicants) {
		this.applicants = applicants;
	}

}
