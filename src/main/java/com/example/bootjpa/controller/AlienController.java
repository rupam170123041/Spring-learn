package com.example.bootjpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.bootjpa.dao.AlienRepo;
import com.example.bootjpa.model.Alien;

@RestController
public class AlienController {

	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		
		return "home.jsp";
	}
	@PostMapping(path="/alien",consumes= {"application/json"})
	public String addAlien(@RequestBody Alien alien)
	{
		repo.save(alien);
		return "home.jsp";
	}
	
	@GetMapping("/aliens")
	
	public List<Alien> getAlien()
	{
		return repo.findAll();
	}
	
	@DeleteMapping("/alien/{aid}")
	public void deleteAlien(@PathVariable int aid)
	{
		Alien a = repo.getOne(aid);
		repo.delete(a);
		
	}
	
	@PutMapping("/alien")
	public String updateAlien(@RequestBody Alien alien)
	{
		repo.save(alien);
		return "updated";
	}
	
	@RequestMapping("/aliens/{aid}")
	@ResponseBody
	public Optional<Alien> getAlienById(@PathVariable("aid") int aid)
	{
		return repo.findById(aid);
	}
	
}
