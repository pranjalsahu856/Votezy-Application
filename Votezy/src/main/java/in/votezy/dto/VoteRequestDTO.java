package in.votezy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteRequestDTO {
	@NotNull(message ="Voter Id is Required")
	private Long voterId;
	@NotNull(message ="Candidate Id is Required")
	private Long candidateId;
}
