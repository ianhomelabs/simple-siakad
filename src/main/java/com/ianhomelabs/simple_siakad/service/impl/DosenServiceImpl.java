package com.ianhomelabs.simple_siakad.service.impl;

import com.ianhomelabs.simple_siakad.exception.BadRequestException;
import com.ianhomelabs.simple_siakad.exception.DataNotFoundException;
import com.ianhomelabs.simple_siakad.model.Dosen;
import com.ianhomelabs.simple_siakad.repository.DosenRepository;
import com.ianhomelabs.simple_siakad.service.DosenService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DosenServiceImpl implements DosenService {

    private final DosenRepository dosenRepository;

    public DosenServiceImpl(DosenRepository dosenRepository) {
        this.dosenRepository = dosenRepository;
    }

    @Override
    public Dosen create(Dosen dosen) {
        // Check duplicate dosen name
        Optional<Dosen> existingDosenByNama = dosenRepository.findByNama(dosen.getNama());
        if (existingDosenByNama.isPresent()) {
            throw new BadRequestException("Nama dosen sudah digunakan");
        }

        dosen.setPassword(BCrypt.hashpw(dosen.getPassword(), BCrypt.gensalt()));
        dosenRepository.save(dosen);
        return dosen;
    }

    @Override
    public List<Dosen> getAll() {
        return dosenRepository.findAll();
    }

    @Override
    public Dosen getById(UUID id) {
        return dosenRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Dosen tidak ditemukan"));
    }

    @Override
    public Dosen update(UUID id, Dosen dosen) {
        // Get existing dosen
        Dosen existingDosen = getById(id);

        // Set changed value
        if (!existingDosen.getNama().equals(dosen.getNama())) {
            existingDosen.setNama(dosen.getNama());
        }
        if (!existingDosen.getEmail().equals(dosen.getEmail())) {
            existingDosen.setEmail(dosen.getEmail());
        }
        if (!existingDosen.getNip().equals(dosen.getNip())) {
            existingDosen.setNip(dosen.getNip());
        }
        if (!existingDosen.getPassword().equals(dosen.getPassword())) {
            existingDosen.setPassword(BCrypt.hashpw(dosen.getPassword(), BCrypt.gensalt()));
        }

        dosenRepository.save(existingDosen);
        return existingDosen;
    }

    @Override
    public Dosen delete(UUID id) {
        // Get existing dosen
        Dosen existingDosen = getById(id);

        dosenRepository.delete(existingDosen);
        return existingDosen;
    }
}
