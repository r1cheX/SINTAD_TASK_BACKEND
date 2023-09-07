package com.sintad.backend.controllers;

import com.sintad.backend.dataTransferObjects.request.LoginRequest;
import com.sintad.backend.dataTransferObjects.request.RegisterRequest;
import com.sintad.backend.dataTransferObjects.response.JWTResponse;
import com.sintad.backend.dataTransferObjects.response.MessageResponse;

import com.sintad.backend.utils.TypeRole;
import com.sintad.backend.models.Rol;

import com.sintad.backend.models.Usuario;
import com.sintad.backend.repositories.IRolRepository;
import com.sintad.backend.repositories.IUsuarioRepository;
import com.sintad.backend.services.UserService;

import com.sintad.backend.security.jwt.JwtUtils; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api-sintad/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUsuarioRepository userRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("login")
    public ResponseEntity<JWTResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserService userDetails = (UserService) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(authentication);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JWTResponse(
                jwt,
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(registerRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("fail", "El email ya se encuentra registrado"));
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(registerRequest.getEmail());
        usuario.setUsername(registerRequest.getUsername());
        usuario.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Rol roles = rolRepository.findByNombre(TypeRole.ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado"));

        usuario.setRoles(Collections.singleton(roles));
        userRepository.save(usuario);

        return ResponseEntity.ok(new MessageResponse("success","Usuario registrado exitosamente"));
    }
}
