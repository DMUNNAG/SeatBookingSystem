package com.SeatBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SeatBookingSystem.entity.Building;
import com.SeatBookingSystem.exception.BuildingNotFoundException;
import com.SeatBookingSystem.service.BuildingService;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

	@Autowired
	private BuildingService buildingService;

	@PostMapping("/add")
	public ResponseEntity<Building> addBuilding(@RequestBody Building building) {
		Building newBuilding = buildingService.addBuilding(building);
		return ResponseEntity.status(HttpStatus.CREATED).body(newBuilding);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Building> updateBuilding(@PathVariable("id") int id, @RequestBody Building building) {
		try {
			Building updatedBuilding = buildingService.updateBuildingById(id, building);
			return ResponseEntity.ok(updatedBuilding);
		} catch (BuildingNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Building> findBuildingById(@PathVariable("id") int id) {
		try {
			Building building = buildingService.findBuildingById(id);
			return ResponseEntity.ok(building);
		} catch (BuildingNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBuildingById(@PathVariable("id") int id) {
		try {
			String message = buildingService.deleteBuildingById(id);
			return ResponseEntity.ok(message);
		} catch (BuildingNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/findall")
	public ResponseEntity<List<Building>> findAllBuildings() {
		List<Building> buildings = buildingService.findAllBuildings();
		return ResponseEntity.ok(buildings);
	}
}
