package com.yakauleu.ibank.service.converter;

@FunctionalInterface
public interface Converter<T, R> {

    R convert(T object);
}
