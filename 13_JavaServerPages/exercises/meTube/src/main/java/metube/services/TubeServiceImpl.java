package metube.services;

import metube.domain.entities.Tube;
import metube.domain.models.service.TubeServiceModel;
import metube.repositories.TubeRepository;
import metube.util.ModelMapper;
import metube.util.ValidationUtil;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel) {
        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);
        this.tubeRepository.save(tube);
    }
    @Override
    public TubeServiceModel findTubeByName(String name){
        Tube tube = this.tubeRepository.findByName(name).orElse(null);
        TubeServiceModel tubeServiceModel=null;
        if(tube!=null){
                tubeServiceModel = this.modelMapper.map(tube, TubeServiceModel.class);
        }
        return tubeServiceModel;
    }
    @Override
    public List<TubeServiceModel> findAllTubes(){
        return this.tubeRepository.findAll()
                .stream()
                .map(tube->this.modelMapper.map(tube,TubeServiceModel.class))
                .collect(Collectors.toList());
    }
}
