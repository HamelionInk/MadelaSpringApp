package com.nikitin.mailapp.controllers;

import com.nikitin.mailapp.dto.PersonDTO;
import com.nikitin.mailapp.model.Person;
import com.nikitin.mailapp.repository.PersonRepository;
import com.nikitin.mailapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class DataController {

    private PersonService personService;

    @Autowired
    public DataController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public String showPage(Model model) {
        model.addAttribute("persons", personService.showAllPerson());

        return "data/DataPage";
    }

    @GetMapping("/DataControl/add")
    public String showAddPage(@ModelAttribute("personAdd") Person person) {
        return "data/DataAdd";
    }

    @PostMapping("/add")
    public String addPerson(@ModelAttribute("personAdd") @Valid PersonDTO personDTO,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "data/DataAdd";
        }

        personService.createOrUpdatePerson(personDTO);
        return "redirect:/";
    }

    @GetMapping("/DataControl/update/{id}")
    public String showUpdatePage(@PathVariable("id") Long id,
                                 Model model) {
        model.addAttribute("personUpdate", personService.showPerson(id));
        return "data/DataUpdate";
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@ModelAttribute("personUpdate") @Valid PersonDTO personDTO,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "data/DataUpdate";
        }

        personService.createOrUpdatePerson(personDTO);
        return "redirect:/";
    }

    @PostMapping("/DataControl/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return "redirect:/";
    }
}
