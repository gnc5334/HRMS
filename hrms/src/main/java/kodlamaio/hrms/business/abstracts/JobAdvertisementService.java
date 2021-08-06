package kodlamaio.hrms.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementWithCompanyDto;

public interface JobAdvertisementService {
	
	Result add(JobAdvertisement jobAdvertisement);
	Result setIsPassive(int jobAdvertisementId);
	DataResult<List<JobAdvertisement>> getAll();
	DataResult<List<JobAdvertisementWithCompanyDto>> getAllIsActive();
	DataResult<List<JobAdvertisementWithCompanyDto>> getByCompanyAndIsActive(int companyId);
	DataResult<List<JobAdvertisementWithCompanyDto>> getByDeclarationDateAndIsActive(LocalDate declarationDate);
	
	DataResult<List<JobAdvertisementWithCompanyDto>> getJobAdvertisementWithCompanyDetails();
	

}
