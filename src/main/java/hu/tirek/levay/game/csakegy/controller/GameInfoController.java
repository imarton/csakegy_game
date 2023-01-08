package hu.tirek.levay.game.csakegy.controller;

import hu.tirek.levay.game.csakegy.model.GameInfoCmd;
import hu.tirek.levay.game.csakegy.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;

@Slf4j
@Controller
public class GameInfoController implements Serializable {

    @Autowired
    private GameService gs;

    @GetMapping("/gameinfo")
    public String gameInfo(Model model) throws Exception {
        log.trace("gameinfo GET request");
        setModelAttrs(model, new GameInfoCmd());
//        throw new Exception("Hiba történt");

        return "gameinfo";
    }


    @PostMapping("gameinfo")
    public String gameInfoSubmit(@ModelAttribute GameInfoCmd gameInfoCmd, Model model) {
        log.trace("gameinfo POST request");
        log.debug("gameInfoCmd: {}", gameInfoCmd);

        //action
        if("1".equals(gameInfoCmd.getStartNewGame())) {
            gs.startNewGame();
        }

        //update model
        setModelAttrs(model, gameInfoCmd);


        return "gameinfo";
    }

    private void setModelAttrs(Model model, GameInfoCmd cmd) {
        model.addAttribute("teamName", gs.getTeamName());
        model.addAttribute("answerCount", gs.getAnswerCount());
        model.addAttribute("wordList", gs.getWordList());
        model.addAttribute("wordDB", gs.getBaseWords());
        model.addAttribute("gameStarted", gs.isGameStarted());
        model.addAttribute("dbSize", gs.getBaseWords().size());

        model.addAttribute("gameInfoCmd", cmd);
    }


}
