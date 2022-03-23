package entity;

public class Previous_job_record {
	private Long prev_job_record_id;
	private int duration;
	private Applicant applicant;
	private Company company;
	private Position position;
	
	public Previous_job_record() {
		
	}

	public Long getPrev_job_record_id() {
		return prev_job_record_id;
	}

	public void setPrev_job_record_id(Long prev_job_record_id) {
		this.prev_job_record_id = prev_job_record_id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
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

	@Override
	public String toString() {
		return "Previous_job_record [prev_job_record_id=" + prev_job_record_id + ", duration=" + duration + "]";
	}
	
}
