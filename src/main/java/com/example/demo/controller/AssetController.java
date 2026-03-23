package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Asset;
import com.example.demo.repository.AssetRepository;

@Controller
public class AssetController {

    @Autowired
    private AssetRepository assetRepo;

    @GetMapping("/assets")
    public String list(Model model) {
        model.addAttribute("assets", assetRepo.findAll());
        return "assets";
    }

    @GetMapping("/assets/new")
    public String showCreateForm() {
        return "asset-new"; // templates/asset-new.html を指す
    }

    @PostMapping("/assets/new")
    // ★課題：保管場所 (Location) を引数に追加
    public String createAsset(@RequestParam String name, @RequestParam String location) {
        Asset asset = new Asset();
        asset.setName(name);
        asset.setLocation(location); // ★課題：保管場所をセット
        asset.setStatus("AVAILABLE"); // 初期状態は「利用可能」
        assetRepo.save(asset);
        return "redirect:/assets"; // 登録後は一覧へ戻る
    }
    
}
