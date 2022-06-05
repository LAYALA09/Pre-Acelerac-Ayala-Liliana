package com.alkemy.disney.disney.Mapper;

import com.alkemy.disney.disney.dto.GenderDTO;
import com.alkemy.disney.disney.entity.GenderEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Component
public class GenderMapper {

        public GenderEntity genderDTO2Entity (GenderDTO dto){
            GenderEntity genderEntity = new GenderEntity();
            genderEntity.setName(dto.getName());
            genderEntity.setImageUrl(dto.getImageUrl());
            return genderEntity;

    }
    public GenderDTO genderEntity2DTO (GenderEntity entity){
        GenderDTO dto = new GenderDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImageUrl(entity.getImageUrl());
        return dto;

    }
    public List<GenderDTO> genderEntityList2DTOList(List<GenderEntity>entities){
            List<GenderDTO>dtos=new ArrayList<>();
            for (GenderEntity entity : entities){
                dtos.add(this.genderEntity2DTO(entity));

            }
            return dtos;
    }
}