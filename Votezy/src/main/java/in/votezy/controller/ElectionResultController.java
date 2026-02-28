package in.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.votezy.dto.ElectionResultRequestDTO;
import in.votezy.dto.ElectionResultResponseDTO;
import in.votezy.entity.ElectionResult;
import in.votezy.service.ElectionResultService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/election-results")
@CrossOrigin
public class ElectionResultController {

	private ElectionResultService electionResultService;

	@Autowired
	public ElectionResultController(ElectionResultService electionResultService) {
		this.electionResultService = electionResultService;
	}
	
	@PostMapping("/declare")
	public ResponseEntity<ElectionResultResponseDTO> declareElectionResult(@RequestBody @Valid ElectionResultRequestDTO elctionResultDTO){
		ElectionResult electionResult = electionResultService.declareResult(elctionResultDTO.getElectionName());
		ElectionResultResponseDTO responseDTO = new ElectionResultResponseDTO();
		responseDTO.setElectionName(electionResult.getElectionName());
		responseDTO.setTotalVotes(electionResult.getTotalVotes());
		responseDTO.setWinnerId(electionResult.getWinnerId());
		responseDTO.setWinnerVotes(electionResult.getWinner().getVoteCount());
		return ResponseEntity.ok(responseDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ElectionResult>> getAllResults(){
		List<ElectionResult> results = electionResultService.getAllElectionResults();
		return ResponseEntity.ok(results);
	}
	
}
