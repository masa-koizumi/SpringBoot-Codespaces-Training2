package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional; // ★ 必要：Optionalをインポート

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepo;

    public boolean authenticate(String name, String password) {
        // 1. データベースから名前でユーザーを探す
        // 引数の "name" に合わせます
        Optional<User> userOpt = userRepo.findByName(name);

        // 2. ユーザーが見つかった場合
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // DBのパスワードと一致するかチェック
            return user.getPassword().equals(password);
        } 
        
        // 3. ユーザーがDBにいない場合でも admin なら許可する設定
        if ("admin".equals(name)) {
            return true;
        }

        // それ以外は失敗
        return false;
    }

    // 既存のメソッド
    public User login(String name) {
        return userRepo.findByName(name)
                .orElseGet(() -> {
                    User u = new User();
                    u.setName(name);
                    return userRepo.save(u);
                });
    }
}
