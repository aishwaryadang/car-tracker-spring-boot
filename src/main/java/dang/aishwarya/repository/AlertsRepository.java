package dang.aishwarya.repository;

import dang.aishwarya.entity.Alerts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlertsRepository extends CrudRepository<Alerts, Integer> {
        public List<Alerts> findByPriority(String priority);
}
