package Utils;

import java.util.List;

public interface CarDao {
     void handleCarRequest(Car car);
     public void AjouterCar(Car car);
     public void UpdateCar(Car car);
     public void DeleteCar(Car car);
     public List<Car> getAllCars();
}
