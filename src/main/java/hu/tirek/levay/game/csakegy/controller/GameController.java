package hu.tirek.levay.game.csakegy.controller;

import hu.tirek.levay.game.csakegy.service.GameService;
import hu.tirek.levay.game.csakegy.model.PlayerWord;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Controller
public class GameController implements WebMvcConfigurer {

    @Autowired
    private GameService gs;


    @GetMapping("/coopPlayer")
    public String coopPlayer(Model model) {
        setModelAttrs(model, new PlayerWord());
        return "coopForm";
    }

    @PostMapping("/coopPlayer")
    public String coopSubmit(@Valid PlayerWord playerWord, BindingResult bindingResult, Model model) {
        log.debug("gameInfoCmd: {}", playerWord);
        //validation
        log.debug("bindingResult.hasErrors: {}", bindingResult.hasErrors());
        if (!bindingResult.hasErrors()) {
            //action
            gs.put(playerWord.getName(), playerWord.getWord());
        }


        //update model
        log.debug("model: {}", model);
        setModelAttrs(model,playerWord);

        return "coopForm";
    }

    private void setModelAttrs(Model model, PlayerWord playerWord) {
        model.addAttribute("playerWord", playerWord);
        model.addAttribute("selectedWord", gs.getSelectedWord());
        model.addAttribute("gameStarted", gs.isGameStarted());
        model.addAttribute("alreadyPlayed", gs.alreadyPlayed(playerWord.getName()));

    }
}
