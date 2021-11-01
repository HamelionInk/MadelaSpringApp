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

    @GetMapping("/DataControl")
    public String showPage(Model model, @ModelAttribute("idPerson") Person person) {
        model.addAttribute("persons", PersonDAO.getPersons());
        return "data/DataPage";
    }

    @GetMapping("/DataControl/add")
    public String newPage(@ModelAttribute("person") Person person) {
        return "data/DataAdd";
    }

    @PostMapping("/add")
    public String addPage(@ModelAttribute("person") @Valid Person person,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "data/DataAdd";

        PersonDAO.addPersons(person);
        return "redirect:/DataControl";
    }

    @GetMapping("/DataControl/update/{id}")
    public String updatePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", PersonDAO.getPerson(id));
        return "data/DataUpdate";
    }

    @PostMapping("/DataControl/update/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "data/DataUpdate";

        PersonDAO.updatePerson(id, person);
        return "redirect:/DataControl";
    }

    @PostMapping("/DataControl/delete/{id}")
    public String deletePage(@PathVariable("id") int id) {
        PersonDAO.deletePerson(id);
        return "redirect:/DataControl";
    }
}
