package ee.ivkhkdev.nptv23javafx.model.repository;

import ee.ivkhkdev.nptv23javafx.model.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
}
