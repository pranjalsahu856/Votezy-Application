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

import in.votezy.dto.VoteRequestDTO;
import in.votezy.dto.VoteResponseDTO;
import in.votezy.entity.Vote;
import in.votezy.exception.GlobalExceptionHandler;
import in.votezy.repository.VoteRepository;
import in.votezy.service.VotingService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class VotingController {

    private final GlobalExceptionHandler globalExceptionHandler;

    private final VoteRepository voteRepository;

	private VotingService votingService;

	@Autowired
	public VotingController(VotingService votingService, VoteRepository voteRepository, GlobalExceptionHandler globalExceptionHandler) {
		this.votingService = votingService;
		this.voteRepository = voteRepository;
		this.globalExceptionHandler = globalExceptionHandler;
	}
	
	@PostMapping("/cast")
	public ResponseEntity<VoteResponseDTO> castVote(@Valid @RequestBody VoteRequestDTO voteRequest){
		Vote vote = votingService.castVote(voteRequest.getVoterId(), voteRequest.getCandidateId());
		VoteResponseDTO voteResponse = new VoteResponseDTO("Vote Casted Successfully",true,vote.getVoterId(),vote.getCandidateId());
		return new ResponseEntity<>(voteResponse,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Vote>> getAllVotes(){
		List<Vote> votes = votingService.getAllVotes();
		return new ResponseEntity<List<Vote>>(votes,HttpStatus.OK);
	}
}
