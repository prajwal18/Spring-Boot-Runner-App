package io.prajwal.runnerapp.controller;

import java.util.List;

import io.prajwal.runnerapp.model.UserEntity;
import io.prajwal.runnerapp.service.UserService;
import io.prajwal.runnerapp.util.Formatter;
import io.prajwal.runnerapp.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import io.prajwal.runnerapp.dto.ClubDTO;
import io.prajwal.runnerapp.service.ClubService;


@Controller
@RequestMapping("/clubs")
public class ClubController {
	
	@Autowired
	private ClubService clubService;

	@Autowired
	private UserService userService;
	
	@GetMapping
	public String listClubs(Model model) {
		String query = "";
		List<ClubDTO> clubs = clubService.getAllClubs();
		// Getting the currently logged-in user
		String username = SecurityUtil.getSessionUser();
		UserEntity user = new UserEntity();
		if(username != null){
			user = userService.findByUsername(username);
		}
		// Getting the currently logged-in user
		model.addAttribute("user", user);
		model.addAttribute("query", query);
		model.addAttribute("clubs", clubs);
		return "clubs-list";
	}

	@GetMapping("/search")
	public String filterClubs(@RequestParam(value = "query") String query, Model model){
		List<ClubDTO> clubs = clubService.filterClubsByTitle(query);
		// Getting the currently logged-in user
		String username = SecurityUtil.getSessionUser();
		UserEntity user = new UserEntity();
		if(username != null){
			user = userService.findByUsername(username);
		}
		// Getting the currently logged-in user
		model.addAttribute("user", user);
		model.addAttribute("query", query);
		model.addAttribute("clubs", clubs);
		return "clubs-list";
	}

	@GetMapping("/{clubId}")
	public String getClubById(@PathVariable("clubId") long clubId, Model model){
		ClubDTO club = clubService.findClubById(clubId);
		Formatter formatter = new Formatter();
		// Getting the currently logged-in user
		String username = SecurityUtil.getSessionUser();
		UserEntity user = new UserEntity();
		if(username != null){
			user = userService.findByUsername(username);
		}
		// Getting the currently logged-in user
		model.addAttribute("user", user);
		model.addAttribute("club", club);
		model.addAttribute("formatter", formatter);
		return "clubs-details";
	}

	@GetMapping("/{clubId}/delete")
	public String deleteClubById(@PathVariable("clubId") long clubId){
		clubService.deleteClubById(clubId);
		return "redirect:/clubs";
	}

	@GetMapping("/new")
	public String createClubForm(Model model){
		ClubDTO club = new ClubDTO();
		model.addAttribute("club", club);
		return "clubs-create";
	}

	@PostMapping("/new")
	public String saveClub(@Valid @ModelAttribute("club") ClubDTO club, BindingResult result, Model model){
		if(result.hasErrors()){
			model.addAttribute("club", club);
			return  "clubs-create";
		}
		clubService.saveClub(club);
		return "redirect:/clubs";
	}

	@GetMapping("/{clubId}/edit")
	public String editClubForm(@PathVariable("clubId") long clubId, Model model){
		ClubDTO club = clubService.findClubById(clubId);
		model.addAttribute("club", club);
		return "clubs-edit";
	}

	@PostMapping("/{clubId}/edit")
	public String updateClub(@PathVariable("clubId") long clubId, @Valid @ModelAttribute("club") ClubDTO club, BindingResult result){
		club.setId(clubId);
		if(result.hasErrors()){
			return "clubs-edit";
		}
		clubService.updateClub(club);
		return "redirect:/clubs";
	}



}
