package in.votezy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ElectionResultRequestDTO {

	@NotBlank(message="ElectionName is Required")
	private String electionName;
}
