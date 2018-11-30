package com.iluwatar.converter;

/**
 * @author hzyejinfu
 * @date 2018/11/30 16:23
 */
public class CommonConverter<T, U> extends Converter<T, U>  {
    public CommonConverter(T dto,U entity) {
        super(t -> {PropertyUtils.copyProperties(t,entity);return entity;},
                u -> {PropertyUtils.copyProperties(u,dto);return dto;});
    }
}
