package metube.services;

import metube.domain.models.service.TubeServiceModel;

import java.util.List;
import java.util.Optional;

public interface TubeService {
    boolean uploadTube(TubeServiceModel tubeServiceModel);

    boolean updateTube(TubeServiceModel tubeServiceModel);

    TubeServiceModel findById(String id);

    List<TubeServiceModel> getPendingTubes();

    List<TubeServiceModel> findAllApproved();
}
