package az.innakhchivan.mapper;

import az.innakhchivan.entity.TokenEntity;
import az.innakhchivan.entity.UserEntity;
import az.innakhchivan.enums.TokenType;

public class TokenMapper {
    public static TokenEntity buildTokenEntity(String accessToken,  UserEntity user) {
        return TokenEntity.builder()
                .user(user)
                .loggedOut(false)
                .accessToken(accessToken)
                .tokenType(TokenType.BEARER)
                .build();
    }
}