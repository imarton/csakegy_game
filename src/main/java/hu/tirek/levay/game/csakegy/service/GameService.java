package hu.tirek.levay.game.csakegy.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Scope("singleton")
@Slf4j
public class GameService implements InitializingBean, Serializable {

    /**
     * A kitalálandó szavak 'adatbázisa'
     */
    @Getter
    private List<String> baseWords;

    //-----------------------------------------------------------------------
    /**
     * A kitalálást segítő, a játékosok által beküldött szavak
     */
    private Map<String,String> userWords = new ConcurrentHashMap<>();

    /**
     * A kitalálást segítő szó eltárolása
     * @param userId játékos azonosító
     * @param value szó
     * @return a játékos által előzőleg beküldött szó, vagy null ha nem volt ilyen
     */
    public String put(String userId, String value) {
        return userWords.put(userId,value);
    }

    /**
     * Eddig adott válaszok száma
     * @return válaszok száma
     */
    public int getAnswerCount(){
        return userWords.size();
    }

    /**
     * Az eddig beküldött szavak listája
     * @return
     */
    public Collection<String> getWordList() {
        return userWords.values();
    }

    /**
     * Az adott játékos küldött-e már be szót
     * @param userId játékos azonosító
     * @return true, ha már küldött be
     */
    public boolean alreadyPlayed(String userId) {
        log.trace("called: alreadyPlayed('{}')", userId);
        if(userId == null|| userId.isBlank())
            return false;
        return userWords.get(userId)!=null;
    }

    //-----------------------------------------------------------------------
    @Getter
    private String selectedWord;

    @Autowired
    @Setter(AccessLevel.PROTECTED) //for UnitTest
    private Environment environment;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("GameService inicializálása..");
        //Szó adatbázis inicializálása
        String tmp = environment.getProperty("game.words");
        if(tmp != null)
            this.baseWords = List.of(tmp.split(" "));
        else
            this.baseWords = new ArrayList<>();
        log.info("{} db szó betöltve.", baseWords.size());
    }

    public void startNewGame() {
        log.info("Új játék indítása...");
        Random rand = new Random();
        int i = rand.nextInt(baseWords.size());
        this.selectedWord = baseWords.get(i);
        log.info("Választott szó({}): {}",i, selectedWord);
        this.gameStarted = true;
    }

    @Getter
    private boolean gameStarted = false;

    @Getter @Setter
    private String teamName = "Csapat";

}
