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
        PersonDAO.addPerson(person);
        return "redirect:/DataControl";
    }

    @GetMapping("/DataControl/update")
    public String updatePage() {
        return "first/updatePage";
    }

    @PostMapping("/DataControl/delete/{id}")
    public String deletePage(@PathVariable("id") int id) {
        PersonDAO.deletePerson(id);
        return "redirect:/DataControl";
    }
}
