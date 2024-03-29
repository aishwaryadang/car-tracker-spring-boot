package dang.aishwarya.repository;

import dang.aishwarya.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public interface VehicleRepository extends CrudRepository<Vehicle, String> {
}
