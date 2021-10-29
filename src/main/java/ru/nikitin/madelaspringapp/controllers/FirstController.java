package ru.nikitin.madelaspringapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikitin.madelaspringapp.dao.PersonDAO;
import ru.nikitin.madelaspringapp.model.Person;

@Controller
public class FirstController {

    @GetMapping("/DataControl")
    public String showPage(Model model, @ModelAttribute("idPerson") Person person) {
        model.addAttribute("persons", PersonDAO.getPersons());
        return "first/firstPage";
    }

    @GetMapping("/DataControl/add")
    public String newPage(@ModelAttribute("person") Person person) {
        return "first/addPage";
    }

    @PostMapping("/add")
    public String addPage(@ModelAttribute("person") Person person) {
        PersonDAO.addPersons(person);
        return "redirect:/DataControl";
    }

    @GetMapping("/DataControl/update/{id}")
    public String updatePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", PersonDAO.getPerson(id));
        return "first/updatePage";
    }

    @PostMapping("/DataControl/update/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("person") Person person) {
        PersonDAO.updatePerson(id, person);
        return "redirect:/DataControl";
    }

    @PostMapping("/DataControl/delete/{id}")
    public String deletePage(@PathVariable("id") int id) {
        PersonDAO.deletePerson(id);
        return "redirect:/DataControl";
    }
}
