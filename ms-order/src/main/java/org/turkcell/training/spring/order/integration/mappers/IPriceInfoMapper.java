package org.turkcell.training.spring.order.integration.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.turkcell.training.spring.order.integration.models.PriceInfo;
import org.turkcell.training.spring.order.services.models.Meal;
import org.turkcell.training.spring.restaurant.models.MealDto;
import org.turkcell.training.spring.restaurant.models.PriceInfoDto;

import java.util.List;

@Mapper
public interface IPriceInfoMapper {
    IPriceInfoMapper PRICE_MAPPER = Mappers.getMapper(IPriceInfoMapper.class);

    PriceInfo toPriceInfo(PriceInfoDto price);

    PriceInfoDto toPriceInfo(PriceInfo price);

}
