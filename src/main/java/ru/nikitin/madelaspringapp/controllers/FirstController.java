package ru.nikitin.madelaspringapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nikitin.madelaspringapp.dao.PersonDAO;
import ru.nikitin.madelaspringapp.model.Person;

@Controller
public class FirstController {

    @GetMapping("/DataControl")
    public String firstPage(Model model) {
        model.addAttribute("persons", PersonDAO.getPersons());
        return "first/firstPage";
    }
}
