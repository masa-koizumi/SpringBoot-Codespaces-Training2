package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Loan;

import java.util.Optional;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    // 資産IDで貸出検索（1資産1貸出）
    Optional<Loan> findByAssetId(Long assetId);

    // ユーザごとの貸出一覧
    List<Loan> findByUserId(Long userId);
}
