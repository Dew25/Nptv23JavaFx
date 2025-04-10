package ee.ivkhkdev.nptv23javafx.model.repository;

import ee.ivkhkdev.nptv23javafx.model.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByBook_IdAndAppUser_IdAndReturnDate(Long bookId, Long appUserId, LocalDate returnDate);

//    List<History> findByBook_IdAndAppUser_Id(Long bookId, Long appUserId);
}
