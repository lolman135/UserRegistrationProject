package app.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    void save (T value);
    Optional<T> findById(String id);
    List<T> findAll();
}
