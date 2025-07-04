package com.product.pip.api;


import com.product.pip.domain.user.AuthDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")

public class AuthrizationController {

    ///Forma de valida se senha e ou email-name existe no banco de dodos
    ///
    /// Não é uma boa prática salvar a senha do banco como String, então irei criptografar e salvar no banco de dados




    @PostMapping("/login")
    public ResponseEntity login(@ResponseBody @Valid AuthDto data){

    }
}
