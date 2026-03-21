package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Asset;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    // 利用可能な資産のみ取得（貸出画面で使用）
    List<Asset> findByStatus(String status);
}
