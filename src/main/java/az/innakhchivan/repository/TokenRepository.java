package az.innakhchivan.repository;

import az.innakhchivan.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

    Optional<TokenEntity> findByAccessToken(String accessToken);

   // Optional<TokenEntity> findByRefreshToken(String refreshToken);

    @Query("""
            select t from TokenEntity t
            where t.user.id = :userId and t.loggedOut = false
            """)
    List<TokenEntity> findAllAccessTokensByUser(Long userId);


    @Modifying
    @Transactional
    void deleteAllByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM TokenEntity t WHERE t.expired = true")
    int deleteAllByExpiredTrue();

}