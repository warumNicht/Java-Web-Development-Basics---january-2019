package cats2App.repositories;

import cats2App.domain.entities.Cat;

import java.util.List;

public interface CatsRepository extends GenericRepository<Cat,String> {
    List<Cat> getSorted(String criteria);
}
