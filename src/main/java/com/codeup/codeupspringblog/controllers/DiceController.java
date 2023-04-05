package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class DiceController {
    @GetMapping("/roll-dice")
    public String rollDice(@RequestParam(name = "guess", required = false) Integer guess, Model model) {
        if (guess != null && guess >= 1 && guess <= 6) {
            Random random = new Random();
            int roll = random.nextInt(6) + 1;
            model.addAttribute("guess", guess);
            model.addAttribute("roll", roll);
            if (guess == roll) {
                model.addAttribute("correct", true);
            } else {
                model.addAttribute("incorrect", true);
            }
        }
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String rollDiceWithGuess(@PathVariable int guess) {
        return "redirect:/roll-dice?guess=" + guess;
    }
}
