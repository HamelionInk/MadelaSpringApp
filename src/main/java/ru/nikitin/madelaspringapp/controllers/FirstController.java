package ru.nikitin.madelaspringapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nikitin.madelaspringapp.dao.PersonDAO;
import ru.nikitin.madelaspringapp.model.Person;

@Controller
public class FirstController {

    @GetMapping("/DataControl")
    public String showPage(Model model) {
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

    @GetMapping
    public String updatePage() {
        return "first/updatePage";
    }

    @GetMapping("/DataControl/delete")
    public String deletePage() {
        return "first/deletePage";
    }
}
