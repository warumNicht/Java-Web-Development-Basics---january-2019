package cats2App.services;

import cats2App.domain.entities.Cat;
import cats2App.domain.models.serviceModels.CatServiceModel;
import cats2App.repositories.CatsRepository;
import org.modelmapper.ModelMapper;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CatServiceImpl implements CatService {
    private final CatsRepository catsRepository;
    private final ModelMapper modelMapper;

    @Inject
    public CatServiceImpl(CatsRepository catsRepository, ModelMapper modelMapper) {
        this.catsRepository = catsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerCat(CatServiceModel catServiceModel) {
        try{
            Cat cat = this.modelMapper.map(catServiceModel, Cat.class);
            this.catsRepository.save(cat);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<CatServiceModel> findAll() {
        List<CatServiceModel> cats = this.catsRepository.findAll().stream()
                .map(c -> this.modelMapper.map(c, CatServiceModel.class))
                .collect(Collectors.toList());
        return cats;
    }

    @Override
    public List<CatServiceModel> getSortedCats(String criteria) {
        return this.catsRepository.getSorted(criteria).stream()
                .map(c->this.modelMapper.map(c,CatServiceModel.class))
                .collect(Collectors.toList());
    }
}
