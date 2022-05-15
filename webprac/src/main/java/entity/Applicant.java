package entity;

import java.util.Set;


public class Applicant {
	private Long appl_id;
	private String appl_name;
	private String status;
	private String address;
	private Education education;
	private Set<Cv> cvs;
	private Set<Previous_job_record> previous_job_records;

	public Applicant() {

	}

	public Long getAppl_id() {
		return appl_id;
	}

	public void setAppl_id(Long appl_id) {
		this.appl_id = appl_id;
	}

	public String getAppl_name() {
		return appl_name;
	}

	public void setAppl_name(String appl_name) {
		this.appl_name = appl_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Applicant [appl_id=" + appl_id + ", appl_name=" + appl_name + ", status=" + status
				+ ", address=" + address + "]";
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public Set<Cv> getCvs() {
		return cvs;
	}

	public void setCvs(Set<Cv> cvs) {
		this.cvs = cvs;
	}

	public Set<Previous_job_record> getPrevious_job_records() {
		return previous_job_records;
	}

	public void setPrevious_job_records(Set<Previous_job_record> previous_job_records) {
		this.previous_job_records = previous_job_records;
	}

}
