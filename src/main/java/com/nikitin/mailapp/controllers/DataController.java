package com.nikitin.mailapp.controllers;

import com.nikitin.mailapp.model.Person;
import com.nikitin.mailapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//todo - change personRepository on PersonService
@Controller
public class DataController {

    private final PersonRepository personRepository;

    @Autowired
    public DataController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String showPage(Model model) {
        model.addAttribute("persons", personRepository.findAll());

        return "data/DataPage";
    }

    @GetMapping("/DataControl/add")
    public String showAddPage(@ModelAttribute("personAdd") Person person) {
        return "data/DataAdd";
    }

    @PostMapping("/add")
    public String addPerson(@ModelAttribute("personAdd") @Valid Person person,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "data/DataAdd";
        }

        personRepository.save(person);
        return "redirect:/";
    }

    @GetMapping("/DataControl/update/{id}")
    public String showUpdatePage(@PathVariable("id") Long id,
                                 Model model) {
        model.addAttribute("personUpdate", personRepository.findById(id).orElseThrow());
        return "data/DataUpdate";
    }

    @PutMapping("/update/{id}")
    public String updatePerson(@ModelAttribute("personUpdate") @Valid Person person,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "data/DataUpdate";
        }

        personRepository.save(person);
        return "redirect:/";
    }

    @DeleteMapping("/DataControl/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        personRepository.deleteById(id);
        return "redirect:/";
    }
}
