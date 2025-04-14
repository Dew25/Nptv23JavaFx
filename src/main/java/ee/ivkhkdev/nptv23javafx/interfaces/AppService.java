package ee.ivkhkdev.nptv23javafx.interfaces;

import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public interface AppService<T> {
    Optional<T> add(T t);
    List<T> getList();
    ObservableList<T> getObservableList();
}
