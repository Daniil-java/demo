package ru.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spring.demo.Trash.Person;
import ru.spring.demo.Trash.PersonController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class HelloWorldController {

    @Autowired
    private PersonController personController;

    public HelloWorldController(PersonController personController) {
        this.personController = personController;
    }

    @GetMapping
    public List<String> getHelloWorld() {
        return List.of("Hello", "World");
    }

    @GetMapping("/api/{login}/{password}")
    @ResponseBody
    public String getLogin
            (@PathVariable String login, @PathVariable String password) throws SQLException {
        AuthService.connection();
        AuthService.setNewUser(login, password, "");
        return AuthService.getUser(login);
    }

    @GetMapping("/index")
    @ResponseBody
    public List<Person> showPersons(){
        return personController.index();
    }

    @PostMapping("/api")
    String search (@RequestBody Person person){
        personController.setPerson(person);
        return "index";
    }

    @DeleteMapping("/api")
    String delete(@RequestBody Person person) {
        personController.delete(person);
        return "DELETE";
    }

    @PutMapping("/api")
    String put(@RequestBody Person person) {
        personController.put(person);
        return "PUTIN";
    }
}
