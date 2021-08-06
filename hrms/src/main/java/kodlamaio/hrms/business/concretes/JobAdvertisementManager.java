package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementWithCompanyDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdvertisementDao jobAdvertisementDao;
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
	}
	
	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("Job Advertisement is added.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>( this.jobAdvertisementDao.findAll(),"Job advertisements are listed");
	}


	@Override
	public DataResult<List<JobAdvertisementWithCompanyDto>> getJobAdvertisementWithCompanyDetails() {
		return new SuccessDataResult<List<JobAdvertisementWithCompanyDto>>(this.jobAdvertisementDao.getJobAdvertisementWithCompanyDetails(),
				"Listed");
	}

	@Override
	public Result setIsPassive(int jobAdvertisementId) {
		this.jobAdvertisementDao.setIsPassive(jobAdvertisementId);
		return new SuccessResult("set passive");
	}

	@Override
	public DataResult<List<JobAdvertisementWithCompanyDto>> getAllIsActive() {
		return new SuccessDataResult<List<JobAdvertisementWithCompanyDto>>(this.jobAdvertisementDao.getAllIsActive(),
				"Listed");
	}

	@Override
	public DataResult<List<JobAdvertisementWithCompanyDto>> getByCompanyAndIsActive(int companyId) {
		return new SuccessDataResult<List<JobAdvertisementWithCompanyDto>>(this.jobAdvertisementDao.getByCompanyAndIsActive(companyId),
				"Listed");
	}

	@Override
	public DataResult<List<JobAdvertisementWithCompanyDto>> getByDeclarationDateAndIsActive(LocalDate declarationDate) {
		return new SuccessDataResult<List<JobAdvertisementWithCompanyDto>>(this.jobAdvertisementDao.getByDeclarationDateAndIsActive(declarationDate),
				"Listed");
	}


}
