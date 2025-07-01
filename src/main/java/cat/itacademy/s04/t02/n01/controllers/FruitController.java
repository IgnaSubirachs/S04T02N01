package cat.itacademy.s04.t02.n01.controllers;

import cat.itacademy.s04.t02.n01.dto.FruitRequest;
import cat.itacademy.s04.t02.n01.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import cat.itacademy.s04.t02.n01.model.FruitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cat.itacademy.s04.t02.n01.services.FruitService;

import java.util.List;

@RestController
@RequestMapping("/fruit")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    @GetMapping("/getAll")
    public ResponseEntity<List<FruitModel>>getFruits() {
        List<FruitModel>fruits = fruitService.getFruits();
        return ResponseEntity.ok(fruits);
    }

    @PostMapping("/add")
    public ResponseEntity<FruitModel> saveFruit(@Valid @RequestBody FruitRequest request) {
        FruitModel created = fruitService.saveFruit(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping(path = "/getOne/{id}")
    public ResponseEntity<FruitModel> getById(@PathVariable Long id) {
        FruitModel fruit = fruitService.getById(id).orElseThrow(()->new ResourceNotFoundException("Fruit with id " + id + " not found"));
        return ResponseEntity.ok(fruit);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<FruitModel> updateFruitById(@PathVariable Long id,@Valid @RequestBody FruitRequest request) {
        FruitModel updated = fruitService.updateById(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        fruitService.deleteFruit(id);
        return ResponseEntity.ok("Fruit with id " + id + " deleted");
    }


}
