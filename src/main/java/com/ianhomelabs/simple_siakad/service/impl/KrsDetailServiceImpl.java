package com.ianhomelabs.simple_siakad.service.impl;

import com.ianhomelabs.simple_siakad.exception.DataNotFoundException;
import com.ianhomelabs.simple_siakad.model.Krs;
import com.ianhomelabs.simple_siakad.model.KrsDetail;
import com.ianhomelabs.simple_siakad.model.Matakuliah;
import com.ianhomelabs.simple_siakad.repository.KrsDetailRepository;
import com.ianhomelabs.simple_siakad.service.KrsDetailService;
import com.ianhomelabs.simple_siakad.service.KrsService;
import com.ianhomelabs.simple_siakad.service.MatakuliahService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KrsDetailServiceImpl implements KrsDetailService {

    private final KrsDetailRepository krsDetailRepository;
    private final KrsService krsService;
    private final MatakuliahService matakuliahService;

    public KrsDetailServiceImpl(KrsDetailRepository krsDetailRepository, KrsService krsService, MatakuliahService matakuliahService) {
        this.krsDetailRepository = krsDetailRepository;
        this.krsService = krsService;
        this.matakuliahService = matakuliahService;
    }


    @Override
    public KrsDetail create(KrsDetail krsDetail) {
        // Validate if matakuliah and krs are valid
        matakuliahService.getById(krsDetail.getMatakuliahId());
        krsService.getById(krsDetail.getKrsId());

        krsDetailRepository.save(krsDetail);
        return krsDetail;
    }

    @Override
    public List<KrsDetail> getAll() {
        return krsDetailRepository.findAll();
    }

    @Override
    public KrsDetail getById(UUID id) {
        Optional<KrsDetail> optionalKrs = krsDetailRepository.findById(id);
        if (optionalKrs.isEmpty()) {
            throw new DataNotFoundException("Krs detail tidak ditemukan");
        }

        return optionalKrs.get();
    }

    @Override
    public KrsDetail update(UUID id, KrsDetail krsDetail) {
        // Validate krs detail is exists
        KrsDetail existingKrsDetail = getById(id);

        if (!existingKrsDetail.getKrsId().equals(krsDetail.getKrsId())) {
            // Validate if krs are valid
            krsService.getById(krsDetail.getKrsId());
            existingKrsDetail.setKrsId(krsDetail.getKrsId());
        }
        if (!existingKrsDetail.getMatakuliahId().equals(krsDetail.getMatakuliahId())) {
            // Validate if matakuliah are valid
            matakuliahService.getById(krsDetail.getMatakuliahId());
            existingKrsDetail.setMatakuliahId(krsDetail.getMatakuliahId());
        }
        if (!existingKrsDetail.getNilai().equals(krsDetail.getNilai())) {
            existingKrsDetail.setNilai(krsDetail.getNilai());
        }

        krsDetailRepository.save(existingKrsDetail);
        return existingKrsDetail;
    }

    @Override
    public KrsDetail delete(UUID id) {
        // Validate krs detail is exists
        KrsDetail existingKrsDetail = getById(id);

        krsDetailRepository.delete(existingKrsDetail);
        return existingKrsDetail;
    }
}
