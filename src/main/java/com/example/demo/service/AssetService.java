package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.entity.Asset;
import com.example.demo.entity.Loan;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.LoanRepository;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepo;

    @Autowired
    private LoanRepository loanRepo;

    public List<Asset> findAll() {

        List<Asset> assets = assetRepo.findAll();

        for (Asset a : assets) {
            loanRepo.findAll().stream()
                    .filter(l -> l.getAsset().getId().equals(a.getId()))
                    .findFirst()
                    .ifPresent(l -> a.setLoanUserName(l.getUser().getName()));
        }

        return assets;
    }

    public void save(String name) {
        Asset a = new Asset();
        a.setName(name);
        a.setStatus("AVAILABLE");
        assetRepo.save(a);
    }
}
