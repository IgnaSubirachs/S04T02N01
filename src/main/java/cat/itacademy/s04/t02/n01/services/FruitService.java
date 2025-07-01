package cat.itacademy.s04.t02.n01.services;

import cat.itacademy.s04.t02.n01.dto.FruitRequest;
import cat.itacademy.s04.t02.n01.exception.ResourceNotFoundException;
import cat.itacademy.s04.t02.n01.model.FruitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cat.itacademy.s04.t02.n01.repository.FruitRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class FruitService {

    @Autowired
    FruitRepository fruitRepository;

    public ArrayList<FruitModel> getFruits() {
        return (ArrayList<FruitModel>) fruitRepository.findAll();
    }

    public FruitModel saveFruit(FruitRequest request) {
        FruitModel fruit = new FruitModel();
        fruit.setName((request.getName()));
        fruit.setKilogramQuantity(request.getKilogramQuantity());
        return fruitRepository.save(fruit);
    }

    public Optional<FruitModel> getById(Long id) {
        return fruitRepository.findById(id);
    }

    public FruitModel updateById(Long id, FruitRequest request) {
        FruitModel fruit = fruitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fruit with id " + id + " not found"));

        fruit.setName(request.getName());
        fruit.setKilogramQuantity(request.getKilogramQuantity());

        return fruitRepository.save(fruit);
    }

    public Boolean deleteFruit(Long id){
        try {
            fruitRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
