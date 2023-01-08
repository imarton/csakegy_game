package hu.tirek.levay.game.csakegy.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameInfoCmd implements Serializable {

    private String showDb;

    private String startNewGame;

}
