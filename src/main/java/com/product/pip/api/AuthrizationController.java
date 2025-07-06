package com.product.pip.api;


import com.product.pip.domain.user.AuthDto;
import com.product.pip.domain.user.LoginResponseDto;
import com.product.pip.domain.user.RegisterDto;
import com.product.pip.domain.user.User;
import com.product.pip.infra.TokenService;
import com.product.pip.repository.AuthRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor

public class AuthrizationController {

    ///Forma de valida se senha e ou email-name existe no banco de dodos
    ///
    /// Não é uma boa prática salvar a senha do banco como String, então irei criptografar e salvar no banco de dados

    private final  AuthenticationManager authenticationManager;

    private  final AuthRepository repository;

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);


        var token = tokenService.genarateToke((User) auth.getPrincipal());



        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data) {
            if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

            String encrypedPassword =  new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.login(), encrypedPassword, data.role());

            this.repository.save(newUser);

            return ResponseEntity.ok().build();
    }
}
