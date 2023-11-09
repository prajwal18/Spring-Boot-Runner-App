package io.prajwal.runnerapp.controller;

import io.prajwal.runnerapp.dto.EventDTO;
import io.prajwal.runnerapp.model.UserEntity;
import io.prajwal.runnerapp.service.EventService;
import io.prajwal.runnerapp.service.UserService;
import io.prajwal.runnerapp.util.Formatter;
import io.prajwal.runnerapp.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;


    @GetMapping("/clubs/{clubId}/events/new")
    public String createEventForm(Model model, @PathVariable("clubId") long clubId){
        EventDTO event = new EventDTO();
        model.addAttribute("event", event);
        model.addAttribute("clubId", clubId);
        return "events-create";
    }
    @PostMapping("/clubs/{clubId}/events/new")
    public String saveEvent(@PathVariable("clubId") long clubId, Model model, @Valid @ModelAttribute("event") EventDTO event, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("event", event);
            model.addAttribute("clubId", clubId);
            return "events-create";
        }
        eventService.saveEvent(clubId, event);
        return "redirect:/clubs/" + clubId;
    }


    @GetMapping("/events")
    public String getAllEventsPage(Model model){
        String query = "";
        List<EventDTO> events = eventService.getAllEvents();
        String username = SecurityUtil.getSessionUser();
        UserEntity user = new UserEntity();
        if(username != null){
            user = userService.findByUsername(username);
        }
        model.addAttribute("user", user);
        model.addAttribute("events", events);
        model.addAttribute("query", query);
        return "events-list";
    }

    @GetMapping("/events/search")
    public String filterEventsByName(Model model, @RequestParam(value = "query") String query){
        List<EventDTO> events = eventService.filterEventsByName(query);
        String username = SecurityUtil.getSessionUser();
        UserEntity user = new UserEntity();
        if(username != null){
            user = userService.findByUsername(username);
        }
        model.addAttribute("user", user);
        model.addAttribute("events", events);
        model.addAttribute("query", query);
        return "events-list";
    }
    @GetMapping("/events/{eventId}")
    public String getEventPage(Model model, @PathVariable("eventId") long eventId){
        EventDTO event = eventService.findEventById(eventId);
        Formatter formatter = new Formatter();
        String username = SecurityUtil.getSessionUser();
        UserEntity user = new UserEntity();
        if(username != null){
            user = userService.findByUsername(username);
        }
        model.addAttribute("user", user);
        model.addAttribute("event", event);
        model.addAttribute("formatter", formatter);
        return "events-details";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventPage(@PathVariable("eventId") long eventId, Model model){
        EventDTO event = eventService.findEventById(eventId);
        model.addAttribute("event", event);
        model.addAttribute("eventId", eventId);
        return "events-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") long eventId, @Valid @ModelAttribute(value = "event") EventDTO event, BindingResult result, Model model){
        event.setId(eventId);
        if(result.hasErrors()){
            return "events-edit";
        }
        EventDTO eventToUpdate = eventService.findEventById(eventId);
        event.setClub(eventToUpdate.getClub());
        eventService.updateEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") long eventId){
        eventService.deleteEventById(eventId);
        return "redirect:/events";
    }
}
