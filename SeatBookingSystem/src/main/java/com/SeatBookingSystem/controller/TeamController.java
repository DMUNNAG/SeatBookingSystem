package com.SeatBookingSystem.controller;

import com.SeatBookingSystem.entity.Team;
import com.SeatBookingSystem.exception.TeamNotFoundException;
import com.SeatBookingSystem.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@PostMapping
	public ResponseEntity<Team> addTeam(@RequestBody Team team) {
		Team addedTeam = teamService.addTeam(team);
		return new ResponseEntity<>(addedTeam, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Team> updateTeamById(@PathVariable int id, @RequestBody Team team)
			throws TeamNotFoundException {
		Team updatedTeam = teamService.updateTeamById(id, team);
		return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Team> findTeamById(@PathVariable int id) throws TeamNotFoundException {
		Team team = teamService.findTeamById(id);
		return new ResponseEntity<>(team, HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteTeamById(@PathVariable int id) throws TeamNotFoundException {
		String message = teamService.deleteTeamById(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Team>> findAllTeams() {
		List<Team> teams = teamService.findAllTeams();
		return new ResponseEntity<>(teams, HttpStatus.OK);
	}
}
