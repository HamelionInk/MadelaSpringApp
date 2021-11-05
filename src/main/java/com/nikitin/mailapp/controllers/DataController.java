package com.nikitin.mailapp.controllers;

import com.nikitin.mailapp.dao.PersonDAO;
import com.nikitin.mailapp.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DataController {

    @GetMapping("/")
    public String showPage(Model model) {
        model.addAttribute("persons", PersonDAO.getPersons());

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

        PersonDAO.addPersons(person);
        return "redirect:/";
    }

    @GetMapping("/DataControl/update/{id}")
    public String showUpdatePage(@PathVariable("id") Long id,
                                 Model model) {
        model.addAttribute("personUpdate", PersonDAO.getPerson(id));
        return "data/DataUpdate";
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@ModelAttribute("personUpdate") @Valid Person person,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "data/DataUpdate";
        }

        PersonDAO.updatePerson(person);
        return "redirect:/";
    }


    @PostMapping("/DataControl/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        PersonDAO.deletePerson(id);
        return "redirect:/";
    }
}
