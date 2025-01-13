package az.innakhchivan.service;

import az.innakhchivan.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenCleanupService {

    private final TokenRepository tokenRepository;

    @Scheduled(cron = "0 0 0 1 */6 *") //
    public void cleanupExpiredTokens() {
        int deletedTokens = tokenRepository.deleteAllByExpiredTrue();
        log.info("Deleted {} expired tokens from the database.", deletedTokens);
    }
}
