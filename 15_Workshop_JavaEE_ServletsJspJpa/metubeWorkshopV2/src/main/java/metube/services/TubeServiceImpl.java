package metube.services;

import metube.domain.entities.Tube;
import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.service.UserServiceModel;
import metube.repositories.TubeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper, UserService userService) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public boolean uploadTube(TubeServiceModel tubeServiceModel) {
        UserServiceModel uploader = this.userService.findUserByName(tubeServiceModel.getUploader().getUsername());
        tubeServiceModel.setUploader(uploader);

        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);

        try {
            this.tubeRepository.save(tube);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateTube(TubeServiceModel tubeServiceModel) {
        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);

        try {
            this.tubeRepository.update(tube);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public TubeServiceModel findById(String id) {
        Tube tube = this.tubeRepository.findById(id).orElse(null);
        if(tube==null){
            return null;
        }
        return this.modelMapper.map(tube,TubeServiceModel.class);
    }

    @Override
    public List<TubeServiceModel> getPendingTubes() {
        List<Tube> pendingAll = this.tubeRepository.findAllPending();
        List<TubeServiceModel> res=new ArrayList<>();
        for (Tube tube : pendingAll) {
            TubeServiceModel tubeServiceModel = this.modelMapper.map(tube, TubeServiceModel.class);
            res.add(tubeServiceModel);
        }
        return res;
    }
    @Override
    public List<TubeServiceModel> findAllApproved() {
        List<Tube> pendingAll = this.tubeRepository.findAllApproved();
        List<TubeServiceModel> res=new ArrayList<>();
        for (Tube tube : pendingAll) {
            TubeServiceModel tubeServiceModel = this.modelMapper.map(tube, TubeServiceModel.class);
            res.add(tubeServiceModel);
        }
        return res;
    }
}
