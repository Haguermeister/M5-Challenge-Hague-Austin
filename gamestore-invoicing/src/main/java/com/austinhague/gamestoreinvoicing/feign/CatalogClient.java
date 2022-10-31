package com.austinhague.gamestoreinvoicing.feign;

import com.austinhague.gamestoreinvoicing.viewModel.ConsoleViewModel;
import com.austinhague.gamestoreinvoicing.viewModel.GameViewModel;
import com.austinhague.gamestoreinvoicing.viewModel.TShirtViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "game-store-catalog")
public interface CatalogClient {
    // /game routes

    @GetMapping("/game/{id}")
    public GameViewModel getGameById(@PathVariable("id") long gameId);

//    @GetMapping("/game")
//    public List<GameViewModel> getAllGames();
//
//    @PostMapping("/game")
//    public GameViewModel createGame(@RequestBody @Valid GameViewModel gameViewModel);
//
    @PutMapping("/game")
    public void updateGame(@RequestBody @Valid GameViewModel gameViewModel);

//    @DeleteMapping("/game/{id}")
//    public void deleteGame(@PathVariable("id") long gameId);
//
//    @GetMapping("/game/title/{title}")
//    public List<GameViewModel> getGamesByTitle(@PathVariable("title") String title);
//
//    @GetMapping("/game/esrbrating/{esrb}")
//    public List<GameViewModel> getGamesByEsrbRating(@PathVariable("esrb") String esrb);
//
//    @GetMapping("/game/studio/{studio}")
//    public List<GameViewModel> getGamesByStudio(@PathVariable("studio") String studio);

    // /console routes

    @GetMapping("/console/{id}")
    public ConsoleViewModel getConsoleById(@PathVariable("id") long consoleId);

//    @GetMapping("/console")
//    public List<ConsoleViewModel> getAllConsoles();
//
//    @PostMapping(value = "/console")
//    public ConsoleViewModel createConsole(@RequestBody @Valid ConsoleViewModel consoleViewModel);
//
    @PutMapping(value = "/console")
    public void updateConsole(@RequestBody @Valid ConsoleViewModel consoleViewModel);

//    @DeleteMapping("/console/{id}")
//    public void deleteConsole(@PathVariable("id") long consoleId);
//
//    @GetMapping("/console/manufacturer/{manufacturer}")
//    public List<ConsoleViewModel> getConsoleByManufacturer(@PathVariable("manufacturer") String manu);

    // /tshirt routes

    @GetMapping("/tshirt/{id}")
    public TShirtViewModel getTShirtByID(@PathVariable("id") long tShirtId);

//    @GetMapping("/tshirt")
//    public List<TShirtViewModel> getAllTShirts();
//
//    @PostMapping("/tshirt")
//    public TShirtViewModel createTShirt(@RequestBody @Valid TShirtViewModel tShirtViewModel);
//
    @PutMapping("/tshirt")
    public void updateTShirt(@RequestBody @Valid TShirtViewModel tShirtViewModel);

//    @DeleteMapping("/tshirt/{id}")
//    public void deleteTShirt(@PathVariable("id") long tShirtId);
//
//    @GetMapping("/tshirt/size/{size}")
//    public List<TShirtViewModel> getTShirtsBySize(@PathVariable("size") String size);
//
//    @GetMapping("/tshirt/color/{color}")
//    public List<TShirtViewModel> getTShirtsByColor(@PathVariable("color") String color);
}
