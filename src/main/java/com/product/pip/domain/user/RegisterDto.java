package com.product.pip.domain.user;

public record RegisterDto(String login, String password, UserRole role) {
}
