package com.austinhague.gamestorecatalog.service;

import com.austinhague.gamestorecatalog.model.Console;
import com.austinhague.gamestorecatalog.model.Game;
import com.austinhague.gamestorecatalog.model.TShirt;
import com.austinhague.gamestorecatalog.repository.ConsoleRepository;
import com.austinhague.gamestorecatalog.repository.GameRepository;
import com.austinhague.gamestorecatalog.repository.TShirtRepository;
import com.austinhague.gamestorecatalog.viewModel.ConsoleViewModel;
import com.austinhague.gamestorecatalog.viewModel.GameViewModel;
import com.austinhague.gamestorecatalog.viewModel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GameStoreServiceLayer {

    private final BigDecimal PROCESSING_FEE = new BigDecimal("15.49");
    private final BigDecimal MAX_INVOICE_TOTAL = new BigDecimal("999.99");
    private final String GAME_ITEM_TYPE = "Game";
    private final String CONSOLE_ITEM_TYPE = "Console";
    private final String TSHIRT_ITEM_TYPE = "T-Shirt";

    GameRepository gameRepo;
    ConsoleRepository consoleRepo;
    TShirtRepository tShirtRepo;

    @Autowired
    public GameStoreServiceLayer(GameRepository gameRepo, ConsoleRepository consoleRepo, TShirtRepository tShirtRepo){
        this.gameRepo = gameRepo;
        this.consoleRepo = consoleRepo;
        this.tShirtRepo = tShirtRepo;
    }

    //Game service layer...
    public GameViewModel createGame(GameViewModel gameViewModel) {

        // Validate incoming Game Data in the view model.
        // All validations were done using JSR303
        if (gameViewModel==null) throw new IllegalArgumentException("No Game is passed! Game object is null!");

        Game game = new Game();
        game.setTitle(gameViewModel.getTitle());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setQuantity(gameViewModel.getQuantity());
        game.setStudio(gameViewModel.getStudio());

        gameViewModel.setId(gameRepo.save(game).getId());
        return gameViewModel;
    }

    public GameViewModel getGame(long id) {
        Optional<Game> game = gameRepo.findById(id);
        if (game == null)
            return null;
        else
            return buildGameViewModel(game.get());
    }

    public void updateGame(GameViewModel gameViewModel) {

        //Validate incoming Game Data in the view model
        if (gameViewModel==null)
            throw new IllegalArgumentException("No Game data is passed! Game object is null!");

        //make sure the game exists. and if not, throw exception...
        if (this.getGame(gameViewModel.getId())==null)
            throw new IllegalArgumentException("No such game to update.");

        Game game = new Game();
        game.setId(gameViewModel.getId());
        game.setTitle(gameViewModel.getTitle());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setQuantity(gameViewModel.getQuantity());
        game.setStudio(gameViewModel.getStudio());

        gameRepo.save(game);
    }

    public void deleteGame(long id) {
        gameRepo.deleteById(id);
    }

    public List<GameViewModel> getGameByEsrb(String esrb) {
        List<Game> gameList = gameRepo.findAllByEsrbRating(esrb);
        List<GameViewModel> gvmList = new ArrayList<>();
        if (gameList == null)
            return null;
        else
            gameList.stream().forEach(g -> gvmList.add(buildGameViewModel(g)));
        return gvmList;
    }

    public List<GameViewModel> getGameByTitle(String title) {
        List<Game> gameList = gameRepo.findAllByTitle(title);
        List<GameViewModel> gvmList = new ArrayList<>();
        List<GameViewModel> exceptionList = null;

        if (gameList == null) {
            return exceptionList;
        } else {
            gameList.stream().forEach(g -> gvmList.add(buildGameViewModel(g)));
        }
        return gvmList;
    }

    public List<GameViewModel> getGameByStudio(String studio) {
        List<Game> gameList = gameRepo.findAllByStudio(studio);
        List<GameViewModel> gvmList = new ArrayList<>();

        if (gameList == null)
            return null;
        else
            gameList.stream().forEach(g -> gvmList.add(buildGameViewModel(g)));
        return gvmList;
    }

    public List<GameViewModel> getAllGames() {
        List<Game> gameList = gameRepo.findAll();
        List<GameViewModel> gvmList = new ArrayList<>();

        if (gameList == null)
            return null;
        else
            gameList.stream().forEach(g -> gvmList.add(buildGameViewModel(g)));
        return gvmList;
    }

    //CONSOLE SERVICE LAYER METHODS...
    public ConsoleViewModel createConsole(ConsoleViewModel consoleViewModel) {

        // Remember viewModel data was validated using JSR 303
        // Validate incoming Console Data in the view model
        if (consoleViewModel==null) throw new IllegalArgumentException("No Console is passed! Game object is null!");

        Console console = new Console();
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());

        return buildConsoleViewModel(consoleRepo.save(console));
    }

    public ConsoleViewModel getConsoleById(long id) {
        Optional<Console> console = consoleRepo.findById(id);
        if (console == null)
            return null;
        else
            return buildConsoleViewModel(console.get());
    }

    public void updateConsole(ConsoleViewModel consoleViewModel) {

        //Validate incoming Console Data in the view model
        if (consoleViewModel==null)
            throw new IllegalArgumentException("No console data is passed! Console object is null!");

        //make sure the Console exists. and if not, throw exception...
        if (this.getConsoleById(consoleViewModel.getId()) == null)
            throw new IllegalArgumentException("No such console to update.");

        Console console = new Console();
        console.setId(consoleViewModel.getId());
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());

        consoleRepo.save(console);
    }

    public void deleteConsole(long id) {
        consoleRepo.deleteById(id);
    }

    public List<ConsoleViewModel> getConsoleByManufacturer(String manufacturer) {
        List<Console> consoleList = consoleRepo.findAllByManufacturer(manufacturer);
        List<ConsoleViewModel> cvmList = new ArrayList<>();

        if (consoleList == null)
            return null;
        else
            consoleList.stream().forEach(c -> cvmList.add(buildConsoleViewModel(c)));
        return cvmList;
    }

    public List<ConsoleViewModel> getAllConsoles() {
        List<Console> consoleList = consoleRepo.findAll();
        List<ConsoleViewModel> cvmList = new ArrayList<>();

        if (consoleList == null)
            return null;
        else
            consoleList.stream().forEach(c -> cvmList.add(buildConsoleViewModel(c)));
        return cvmList;
    }

    //TSHIRT SERVICE LAYER

    public TShirtViewModel createTShirt(TShirtViewModel tShirtViewModel) {

        // Remember model view has already been validated through JSR 303
        // Validate incoming TShirt Data in the view model
        if (tShirtViewModel==null) throw new IllegalArgumentException("No TShirt is passed! TShirt object is null!");

        TShirt tShirt = new TShirt();
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());

        tShirt = tShirtRepo.save(tShirt);

        return buildTShirtViewModel(tShirt);
    }

    public TShirtViewModel getTShirt(long id) {
        Optional<TShirt> tShirt = tShirtRepo.findById(id);
        if (tShirt == null)
            return null;
        else
            return buildTShirtViewModel(tShirt.get());
    }

    public void updateTShirt(TShirtViewModel tShirtViewModel) {

        // Remember model view has already been validated through JSR 303
        // Validate incoming TShirt Data in the view model
        if (tShirtViewModel==null) throw new IllegalArgumentException("No TShirt is passed! TShirt object is null!");

        //make sure the Console exists. and if not, throw exception...
        if (this.getTShirt(tShirtViewModel.getId())==null)
            throw new IllegalArgumentException("No such TShirt to update.");

        TShirt tShirt = new TShirt();
        tShirt.setId(tShirtViewModel.getId());
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());

        tShirtRepo.save(tShirt);
    }

    public void deleteTShirt(long id) {

        tShirtRepo.deleteById(id);
    }

    public List<TShirtViewModel> getTShirtByColor(String color) {
        List<TShirt> tShirtList = tShirtRepo.findAllByColor(color);
        List<TShirtViewModel> tvmList = new ArrayList<>();

        if (tShirtList == null)
            return null;
        else
            tShirtList.stream().forEach(t -> tvmList.add(buildTShirtViewModel(t)));
        return tvmList;
    }

    public List<TShirtViewModel> getTShirtBySize(String size) {
        List<TShirt> tShirtList = tShirtRepo.findAllBySize(size);
        List<TShirtViewModel> tvmList = new ArrayList<>();

        if (tShirtList == null)
            return null;
        else
            tShirtList.stream().forEach(t -> tvmList.add(buildTShirtViewModel(t)));
        return tvmList;
    }

    public List<TShirtViewModel> getAllTShirts() {
        List<TShirt> tShirtList = tShirtRepo.findAll();
        List<TShirtViewModel> tvmList = new ArrayList<>();

        if (tShirtList == null)
            return null;
        else
            tShirtList.stream().forEach(t -> tvmList.add(buildTShirtViewModel(t)));
        return tvmList;
    }

    //Helper Methods...

    public ConsoleViewModel buildConsoleViewModel(Console console) {
        ConsoleViewModel consoleViewModel = new ConsoleViewModel();
        consoleViewModel.setId(console.getId());
        consoleViewModel.setModel(console.getModel());
        consoleViewModel.setManufacturer(console.getManufacturer());
        consoleViewModel.setMemoryAmount(console.getMemoryAmount());
        consoleViewModel.setProcessor(console.getProcessor());
        consoleViewModel.setPrice(console.getPrice());
        consoleViewModel.setQuantity(console.getQuantity());

        return consoleViewModel;
    }

    public GameViewModel buildGameViewModel(Game game) {

        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setId(game.getId());
        gameViewModel.setTitle(game.getTitle());
        gameViewModel.setEsrbRating(game.getEsrbRating());
        gameViewModel.setDescription(game.getDescription());
        gameViewModel.setPrice(game.getPrice());
        gameViewModel.setStudio(game.getStudio());
        gameViewModel.setQuantity(game.getQuantity());

        return gameViewModel;
    }

    public TShirtViewModel buildTShirtViewModel(TShirt tShirt) {
        TShirtViewModel tShirtViewModel = new TShirtViewModel();
        tShirtViewModel.setId(tShirt.getId());
        tShirtViewModel.setSize(tShirt.getSize());
        tShirtViewModel.setColor(tShirt.getColor());
        tShirtViewModel.setDescription(tShirt.getDescription());
        tShirtViewModel.setPrice(tShirt.getPrice());
        tShirtViewModel.setQuantity(tShirt.getQuantity());

        return tShirtViewModel;
    }
}