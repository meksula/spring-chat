package com.meksula.chat.repository;

import com.meksula.chat.domain.user.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Author
 * Karol Meksuła
 * 20-07-2018
 * */

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
    Optional<ChatUser> findByUsername(String username);

    Optional<ChatUser> findByUserId(long userId);
}
