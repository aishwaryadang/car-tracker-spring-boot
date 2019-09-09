package dang.aishwarya.repository;

import dang.aishwarya.entity.Readings;
import org.springframework.data.repository.CrudRepository;

public interface ReadingRepository extends CrudRepository<Readings, String> {

}
