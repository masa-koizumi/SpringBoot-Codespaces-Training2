package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepo;

    @Autowired
    private AssetRepository assetRepo;

    @Autowired
    private UserRepository userRepo;

    public void loan(Long assetId, Long userId) {

        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new RuntimeException("資産が存在しません"));

        if ("LOANED".equals(asset.getStatus())) {
            throw new RuntimeException("貸出中のため貸出不可");
        }

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("ユーザが存在しません"));

        Loan loan = new Loan();
        loan.setAsset(asset);
        loan.setUser(user);

        asset.setStatus("LOANED");

        loanRepo.save(loan);
        assetRepo.save(asset);
    }

    public void returnAsset(Long loanId) {

        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loanが存在しません"));

        Asset asset = loan.getAsset();
        asset.setStatus("AVAILABLE");

        loanRepo.delete(loan);
        assetRepo.save(asset);
    }
}
