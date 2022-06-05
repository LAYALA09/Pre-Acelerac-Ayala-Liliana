package com.alkemy.disney.disney.Service.Impl;

import com.alkemy.disney.disney.Mapper.GenderMapper;
import com.alkemy.disney.disney.Repository.GenderRepository;
import com.alkemy.disney.disney.Service.GenderService;
import com.alkemy.disney.disney.dto.GenderDTO;
import com.alkemy.disney.disney.entity.GenderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {
    @Autowired
    private GenderMapper genderMapper;
    @Autowired
    private GenderRepository genderRepository;
    public GenderDTO save(GenderDTO dto){
        GenderEntity entity= genderMapper.genderDTO2Entity(dto);
        GenderEntity entitySaved=genderRepository.save(entity);
        GenderDTO result= genderMapper.genderEntity2DTO(entitySaved);
        return result;
    }


    public List<GenderDTO> getAllGenders() {
        List<GenderEntity>entities= genderRepository.findAll();
        List<GenderDTO> result = genderMapper.genderEntityList2DTOList(entities);
        return result;
    }
}
