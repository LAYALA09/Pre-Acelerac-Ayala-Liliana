package com.alkemy.disney.disney.Service;

import com.alkemy.disney.disney.dto.GenderDTO;

import java.util.List;

public interface GenderService {
    GenderDTO save(GenderDTO dto);
    List<GenderDTO> getAllGenders();
}
