package br.com.ciandt.application.fellini.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.ciandt.application.fellini.domain.crew.Actor;

public class CollectionHandlingUtil<T> {
    /**
     * Convert a data source in a new list with limited size
     * Note: Used on this app as a hack to show some lists to shrink large lists
     *
     * @param list
     * @param toMax
     * @return List
     */
    public List<T> limitListMaxViews(List<T> list, int toMax) {
        List<T> newList = new ArrayList<>();

        for (T t : list) {
            if (newList.size() < toMax) {
                newList.add(t);
            }
        }

        return newList;
    }
}
