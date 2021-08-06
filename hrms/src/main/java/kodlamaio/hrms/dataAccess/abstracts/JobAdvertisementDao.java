package kodlamaio.hrms.dataAccess.abstracts;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementWithCompanyDto;


public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement,Integer> {

	@Query("Update JobAdvertisement j set j.isactive = false where j.id=:jobAdvertisementId  ")
	JobAdvertisement setIsPassive(int jobAdvertisementId);
	
	@Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertisementWithCompanyDto( j.id, e.companyName,  j.caption, j.description, j.openPositionCount,"
			+ " j.declarationDate,j.deadline)" 
			+ " From Employer e Inner Join e.jobadvertisements j where j.isactive=true ")
	List<JobAdvertisementWithCompanyDto> getAllIsActive();
	
	@Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertisementWithCompanyDto( j.id, e.companyName,  j.caption, j.description, j.openPositionCount,"
			+ " j.declarationDate,j.deadline)" 
			+ " From Employer e Inner Join e.jobadvertisements j where e.companyId=:companyId and j.isactive=true ")
	List<JobAdvertisementWithCompanyDto> getByCompanyAndIsActive(int companyId);
	
	@Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertisementWithCompanyDto( j.id, e.companyName,  j.caption, j.description, j.openPositionCount,"
			+ " j.declarationDate,j.deadline)" 
			+ " From Employer e Inner Join e.jobadvertisements j where j.isactive=true and j.declarationDate=:declarationDate")
	List<JobAdvertisementWithCompanyDto> getByDeclarationDateAndIsActive(LocalDate declarationDate);
	
	@Query("Select new kodlamaio.hrms.entities.dtos.JobAdvertisementWithCompanyDto( j.id, e.companyName,  j.caption, j.description, j.openPositionCount,"
			+ " j.declarationDate,j.deadline)" 
			+ " From Employer e Inner Join e.jobadvertisements j ")
	List<JobAdvertisementWithCompanyDto> getJobAdvertisementWithCompanyDetails();
}
