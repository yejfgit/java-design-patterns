package com.iluwatar.converter;

/**
 * @author hzyejinfu
 * @date 2018/11/30 16:23
 */
public class CommonConverter<T, U> extends Converter<T, U>  {
    /*public CommonConverter(T dto,U entity) {
        super(t -> {PropertyUtils.copyProperties(t,entity);return entity;},
                u -> {PropertyUtils.copyProperties(u,dto);return dto;});
    }*/

    /**
     * @param tClass dto class
     * @param uClass do class
     * */
    public CommonConverter(Class<T> tClass,Class<U> uClass) {
        super(t -> {
                    U entity = createInstance(uClass);
                    PropertyUtils.copyProperties(t,entity);return entity;
                },
                u -> {
                    T dto = createInstance(tClass);
                    PropertyUtils.copyProperties(u,dto);return dto;
                });
    }

    public static <T> T createInstance(Class<T> cls) {
        T obj;
        try {
            obj=cls.newInstance();
        } catch (Exception e) {
            obj=null;
        }
        return obj;
    }
}
