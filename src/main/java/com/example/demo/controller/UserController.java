package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.LoanRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private LoanRepository loanRepo;

    /**
     * ユーザ一覧
     */
    @GetMapping("/users")
    public String list(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "users";
    }

    /**
     * ユーザ登録
     */
    @PostMapping("/users")
    public String create(@RequestParam String name,
                         RedirectAttributes ra) {

        if (name == null || name.isBlank()) {
            ra.addFlashAttribute("error", "ユーザ名を入力してください");
            return "redirect:/users";
        }

        User u = new User();
        u.setName(name);

        userRepo.save(u);

        ra.addFlashAttribute("message", "ユーザを登録しました");

        return "redirect:/users";
    }

    /**
     * ユーザ削除（貸出中チェックあり）
     */
    @PostMapping("/users/delete")
    public String delete(@RequestParam Long id,
                         RedirectAttributes ra) {

        // 貸出中チェック（重要）
        if (!loanRepo.findByUserId(id).isEmpty()) {
            ra.addFlashAttribute("error", "貸出中のユーザは削除できません");
            return "redirect:/users";
        }

        userRepo.deleteById(id);

        ra.addFlashAttribute("message", "ユーザを削除しました");

        return "redirect:/users";
    }
}
