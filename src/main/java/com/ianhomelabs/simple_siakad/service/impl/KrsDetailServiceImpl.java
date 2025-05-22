package com.ianhomelabs.simple_siakad.service.impl;

import com.ianhomelabs.simple_siakad.dto.request.KrsDetailRequestDto;
import com.ianhomelabs.simple_siakad.dto.response.KrsDetailResponseDto;
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
        Matakuliah matakuliah = matakuliahService.getById(krsDetail.getMatakuliah().getId());
        Krs krs = krsService.getById(krsDetail.getKrs().getId());

        krsDetail.setMatakuliah(matakuliah);
        krsDetail.setKrs(krs);

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

        if (!existingKrsDetail.getKrs().getId().equals(krsDetail.getKrs().getId())) {
            // Validate if krs are valid
            krsService.getById(krsDetail.getKrs().getId());
            existingKrsDetail.getKrs().setId(krsDetail.getKrs().getId());
        }
        if (!existingKrsDetail.getMatakuliah().getId().equals(krsDetail.getMatakuliah().getId())) {
            // Validate if matakuliah are valid
            matakuliahService.getById(krsDetail.getMatakuliah().getId());
            existingKrsDetail.getMatakuliah().setId(krsDetail.getMatakuliah().getId());
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

    @Override
    public KrsDetailResponseDto mapToDto(KrsDetail krsDetail) {
        return KrsDetailResponseDto.builder()
                .id(krsDetail.getId())
                .krsId(krsDetail.getKrs().getId())
                .krsSemester(krsDetail.getKrs().getSemester())
                .matakuliahId(krsDetail.getMatakuliah().getId())
                .matakuliahName(krsDetail.getMatakuliah().getNama())
                .nilai(krsDetail.getNilai())
                .mahasiswaName(krsDetail.getKrs().getMahasiswa().getNama())
                .dosenName(krsDetail.getMatakuliah().getDosen().getNama())
                .build();
    }

    @Override
    public KrsDetail mapToEntity(KrsDetailRequestDto krsDetailRequestDto) {
        Matakuliah matakuliah = new Matakuliah();
        matakuliah.setId(krsDetailRequestDto.getMatakuliahId());

        return KrsDetail.builder()
                .krs(Krs.builder().id(krsDetailRequestDto.getKrsId()).build())
                .matakuliah(matakuliah)
                .nilai(krsDetailRequestDto.getNilai())
                .build();
    }
}
