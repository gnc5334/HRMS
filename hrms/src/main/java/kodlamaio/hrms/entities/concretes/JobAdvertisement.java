package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="jobadvertisements")
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private int id;
	
	 @Column(name="jobtitle_id")
     private int jobTitleId;
	 
	 @Column(name="jobtype")
	 private int jobType;
	 
	 @Column(name="caption")
	 private String caption;
	 
	 @Column(name="description")
	 private String description; 
	 
	 @Column(name="experience_level")
	private String experienceLevel;
	
	 @Column(name="salary_scale_min")
		private int salaryScaleMin;
	 
	 @Column(name="salary_scale_max")
		private int salaryScaleMax;
	 
	 @Column(name="open_position_count")
	  private int openPositionCount;
	 
	 @Column(name="declaration_date")
		private LocalDate declarationDate;
	 
	 @Column(name="deadline")
	 private LocalDate deadline;
	 
	 @Column(name="isactive")
	 private Boolean isactive;
	 
	 @ManyToOne()
	 @JoinColumn(name="company_id")
     private Employer employer;
	 
	 @ManyToOne()
	 @JoinColumn(name="city_id")
     private City city;

}
