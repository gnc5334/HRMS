package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementWithCompanyDto {
	
	private int id;
	private String companyName;
	private String caption;
	private String description;
	//private String cityName;
	private int openPositionCount;
	private LocalDate declarationDate;
	private LocalDate deadline;
}
