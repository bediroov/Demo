//package az.innakhchivan.service;
//
//import az.innakhchivan.dto.request.LoginRequestDto;
//import az.innakhchivan.dto.request.RegisterRequestDto;
//import az.innakhchivan.dto.response.AuthResponseDto;
//import az.innakhchivan.dto.response.LoginResponseDto;
//import az.innakhchivan.dto.response.RegisterResponseDto;
//import az.innakhchivan.entity.TokenEntity;
//import az.innakhchivan.entity.UserEntity;
//import az.innakhchivan.exception.CustomValidationException;
//import az.innakhchivan.exception.UserAlreadyExistsException;
//import az.innakhchivan.repository.TokenRepository;
//import az.innakhchivan.repository.UserRepository;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//import static az.innakhchivan.enums.ErrorMessage.*;
//import static az.innakhchivan.mapper.TokenMapper.buildTokenEntity;
//import static org.springframework.http.HttpHeaders.AUTHORIZATION;
//import static org.springframework.http.HttpStatus.OK;
//import static org.springframework.http.HttpStatus.UNAUTHORIZED;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final TokenRepository tokenRepository;
//    private final AuthenticationManager authenticationManager;
//
//
//    public RegisterResponseDto register(RegisterRequestDto request) throws UserAlreadyExistsException {
//        var user1 = userRepository.findByEmail(request.getEmail());
//        if (user1.isPresent())
//            throw new UserAlreadyExistsException("User already exists with Email: " + request.getEmail());
//
//        if (!isValidPassword(request.getPassword())) {
//            throw new CustomValidationException("Password must contain at least one uppercase letter, " +
//                    "one lowercase letter, one number, and one special character (@#$%^&+=.)");
//        }
//        UserEntity user = new UserEntity();
//
//        user.setEmail(request.getEmail());
//        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setAuthorities(request.getAuthorities());
//
//        user = userRepository.save(user);
//
//        return RegisterResponseDto.builder()
//                .id(user.getId())
//                .email(user.getEmail())
//                .username(user.getUsername())
//                .authorities(user.getAuthorities())
//                .build();
//    }
//
//    public LoginResponseDto login(LoginRequestDto request) {
//
//        var authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUsername(),
//                        request.getPassword()
//                )
//        );
//
//        if (authentication.isAuthenticated()) {
//            var user = (UserEntity) authentication.getPrincipal();
//            String accessToken = jwtService.generateAccessToken(user);
//            String refreshToken = jwtService.generateRefreshToken(user);
//            saveUserToken(accessToken, refreshToken, user);
//
//            return new LoginResponseDto(accessToken, refreshToken, "User login was successful");
//        }
//
//        throw new UsernameNotFoundException(USER_NOT_FOUND.format(request.getUsername()));
//    }
//
//    public ResponseEntity<LoginResponseDto> refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response) {
//        String authHeader = request.getHeader(AUTHORIZATION);
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return ResponseEntity.status(UNAUTHORIZED).build();
//        }
//
//        String token = authHeader.substring(7);
//        String username = jwtService.extractUsername(token);
//
//        UserEntity user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND.getMessage()));
//
//        // Refresh tokenin vaxtini yoxlayiriq
//        if (jwtService.isValidRefreshToken(token, user)) {
//            String accessToken = jwtService.generateAccessToken(user);
//            String refreshToken = jwtService.generateRefreshToken(user);
//
//            //Kohne tokenleri legv edirik
//            revokeAllTokenByUser(user);
//            saveUserToken(accessToken, refreshToken, user);
//
//            return ResponseEntity.status(OK).body(new LoginResponseDto(
//                    accessToken, refreshToken, "New token generated"));
//        } else {
//            // Refresh tokenin vaxti bitibse user tezeden login olunmalidir
//            return ResponseEntity.status(UNAUTHORIZED).body(new LoginResponseDto
//                    (null, null, EXPIRED_TOKEN.getMessage()));
//        }
//    }
//
//
////    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
////        // Authorization başlığını oxuyuruq
////        String authHeader = request.getHeader(AUTHORIZATION);
////
////        // Başlıq yoxdursa və ya "Bearer " ilə başlamırsa, logout-u yerinə yetirmirik
////        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
////            return ResponseEntity.badRequest().body("Invalid logout request");
////        }
////
////        // Tokeni başlıqdan çıxarırıq
////        String token = authHeader.substring(7);
////
////        // Tokeni veritabanında tapıb deaktivləşdiririk
////        TokenEntity tokenEntity = tokenRepository.findByAccessToken(token)
////                .orElseThrow(() -> new EntityNotFoundException("Token not found"));
////
////        // Tokeni deaktivləşdiririk
////        tokenEntity.setLoggedOut(true);
////        tokenRepository.save(tokenEntity);
////
////        // SecurityContextHolder-i təmizləyirik
////        SecurityContextHolder.clearContext();
////
////        // Logout uğurlu olduğu barədə cavab qaytarırıq
////        return ResponseEntity.ok("Logout successful");
////    }
//
//
//    public AuthResponseDto getUserById(Long id) {
//        UserEntity user = userRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_BY_ID.format(id)));
//
//        return AuthResponseDto.builder().id(user.getId()).build();
//    }
//
//
//    private void revokeAllTokenByUser(UserEntity user) {
//        List<TokenEntity> validTokens = tokenRepository.findAllAccessTokensByUser(user.getId());
//        if (validTokens.isEmpty()) return;
//        validTokens.forEach(t -> t.setLoggedOut(true));
//
//        tokenRepository.saveAll(validTokens);
//    }
//
//    private void saveUserToken(String accessToken, String refreshToken, UserEntity user) {
//        var token = buildTokenEntity(accessToken, refreshToken, user);
//        tokenRepository.save(token);
//    }
//
//    private boolean isValidPassword(String password) {
//        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=.]).{5,}$";
//        return password != null && password.matches(passwordPattern);
//    }
//}