package com.basic.auth.web.mappers;
import org.mapstruct.Mapper;

import java.util.List;

public interface Mappable<E, D> {

    D toDto(
            E entity
    );

    List<D> toDto(
            List<E> entity
    );

    E toEntity(
            D dto
    );

}