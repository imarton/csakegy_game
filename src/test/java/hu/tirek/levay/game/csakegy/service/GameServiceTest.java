package hu.tirek.levay.game.csakegy.service;

import hu.tirek.levay.game.csakegy.service.GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class GameServiceTest {

    @Autowired
    private Environment environment;

    @Test
    void afterPropertiesSet() throws Exception {
        GameService gs = new GameService();
        gs.setEnvironment(environment);

        gs.afterPropertiesSet();
        //System.out.println("Szóbázis: "+gs.getBaseWords());
        assertThat(gs.getBaseWords())
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    @DisplayName("false When UserId is null or Blank")
    void alreadyPlayed() throws Exception {
        GameService gs = new GameService();

        assertThat(gs.alreadyPlayed(null))
                .isEqualTo(false);

        assertThat(gs.alreadyPlayed(""))
                .isEqualTo(false);

        assertThat(gs.alreadyPlayed("   "))
                .isEqualTo(false);
    }
}