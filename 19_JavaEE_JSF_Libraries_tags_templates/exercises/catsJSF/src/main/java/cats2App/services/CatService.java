package cats2App.services;

import cats2App.domain.models.serviceModels.CatServiceModel;

import java.util.List;

public interface CatService {

    boolean registerCat(CatServiceModel catServiceModel);

    List<CatServiceModel> findAll();

    List<CatServiceModel> getSortedCats(String criteria);
}
