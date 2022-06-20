package com.rentalcar.backend.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Mapper {
    private Mapper() {

    }

    public static <T, R> List<R> toList(List<T> list, Function<T, R> mapper) {
        List<R> _list = new ArrayList<>();

        for (T item : list) {
            _list.add(mapper.apply(item));
        }

        return _list;
    }
}
