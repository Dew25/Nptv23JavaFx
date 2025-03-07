package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.model.entity.History;
import ee.ivkhkdev.nptv23javafx.model.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HistoryService implements ee.ivkhkdev.nptv23javafx.interfaces.HistoryService {
private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public Optional<History> add(History history) {
        return Optional.of(historyRepository.save(history));
    }

    @Override
    public List<History> getList() {
        return historyRepository.findAll();
    }
}
