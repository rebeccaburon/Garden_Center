package app.dao;

import java.util.List;

public interface IDAO<T> {

    List<T> getAll();

    T getById(Long id);


    List<T> getByType(String type);

    T add(T t);

    List<T> plantsWithMaxHeight(int maxHeight);

    List<String> getPlantNames();

    List<T> plantsSortedByName();
}
