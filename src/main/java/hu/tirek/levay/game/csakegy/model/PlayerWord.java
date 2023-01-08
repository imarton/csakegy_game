package hu.tirek.levay.game.csakegy.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PlayerWord {
    /**
     * Játékos neve
     */
    @NotBlank
    @Size(min=2, max=30)
    private String name;

    /**
     * Játékos által beküldendő szó
     */
    @NotBlank
    @Size(min=2, max=60)
    private String word;
}
