package fdmc.domain.services;

import fdmc.domain.entities.Cat;

import java.util.List;

public interface CatService {
    List<Cat> getAll();

    void addCat(Cat cat);
}
