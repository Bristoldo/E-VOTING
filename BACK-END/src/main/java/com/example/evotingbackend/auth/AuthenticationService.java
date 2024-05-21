package com.example.evotingbackend.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.evotingbackend.config.JwtService;
import com.example.evotingbackend.models.Admin;
import com.example.evotingbackend.models.Etudiant;
import com.example.evotingbackend.models.Filiere;
import com.example.evotingbackend.models.Role;
import com.example.evotingbackend.models.User;
import com.example.evotingbackend.repository.AdminRepository;
import com.example.evotingbackend.repository.EtudiantRepository;
import com.example.evotingbackend.repository.UserRepository;
import com.example.evotingbackend.token.Token;
import com.example.evotingbackend.token.TokenRepository;
import com.example.evotingbackend.token.TokenType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

        private final UserRepository userRepository;

        private final EtudiantRepository etudiantRepository;

        private final AdminRepository adminRepository;

        private final TokenRepository tokenRepository;

        private final PasswordEncoder passwordEncoder;

        private final JwtService jwtService;

        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {

                var etudiant = Etudiant.etudiantBuilder()
                                .build();

                etudiant.setFirstname(request.getFirstname());
                etudiant.setLastname(request.getLastname());
                etudiant.setEmail(request.getEmail());
                etudiant.setPassword(passwordEncoder.encode(request.getPassword()));
                etudiant.setRole(Role.USER);
                etudiant.setNiveau(1);
                etudiant.setFiliere(Filiere.INFORMATIQUE);
                

                User savedUser = etudiantRepository.save(etudiant);
                var jwtToken = jwtService.generateToken(etudiant);

                revokeAllUserTokens(savedUser);
                saveUserToken(savedUser, jwtToken);


                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .firstname(etudiant.getFirstname())
                                .lastname(etudiant.getLastname())
                                .role(etudiant.getRole())
                                .niveau(1)
                                .filiere(Filiere.INFORMATIQUE)
                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));
                var user = userRepository.findByEmail(request.getEmail())
                                .orElseThrow();

                var etudiant = etudiantRepository.findById(user.getId()).get();

                var jwtToken = jwtService.generateToken(user);

                revokeAllUserTokens(user);
                saveUserToken(user, jwtToken);
 
                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .firstname(etudiant.getFirstname())
                                .lastname(etudiant.getLastname())
                                .role(etudiant.getRole())
                                .niveau(etudiant.getNiveau())
                                .filiere(etudiant.getFiliere())
                                .build();
        }


        private void revokeAllUserTokens(User user){
                var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
                if(validUserTokens.isEmpty())
                return;
                validUserTokens.forEach(t -> {
                        t.setExpired(true);
                        t.setRevoked(true);
                });

                tokenRepository.saveAll(validUserTokens);
        }


        public ResponseEntity<?> logout(AuthenticationRequest request) {
                return null;
        }


        
        private void saveUserToken(User user, String jwToken) {

                var token = Token.builder()
                                .user(user)
                                .token(jwToken)
                                .tokenType(TokenType.BEARER)
                                .expired(false)
                                .revoked(false)
                                .build();
                tokenRepository.save(token);
                return;
        }

}
