package com.yx.gkyx.common.utils;

import com.google.common.collect.*;
import com.google.common.collect.MultimapBuilder.SetMultimapBuilder;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@UtilityClass
public class StreamUtils {

    public static <E> List<E> filter(Iterable<E> c, Predicate<E> condition) {
        return Streams.stream(c).filter(condition).collect(Collectors.toList());
    }

    public static <E, O> List<O> mapToList(Iterable<E> c, Function<E, O> mapper) {
        return Streams.stream(c).map(mapper).collect(Collectors.toList());
    }

    public static <E, O> Set<O> mapToSet(Iterable<E> c, Function<E, O> mapper) {
        return Streams.stream(c).map(mapper).collect(Collectors.toSet());
    }

    public static <E, O> List<O> mapNonNullToList(Iterable<E> c, Function<E, O> mapper) {
        return Streams.stream(c).map(mapper).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static <E, O> Set<O> mapNonNullToSet(Iterable<E> c, Function<E, O> mapper) {
        return Streams.stream(c).map(mapper).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    public static <E, K> Map<K, E> toMap(
            Iterable<E> c,
            Function<E, K> keyMapper) {
        return toMap(c, keyMapper, Function.identity());
    }

    public static <E, K, V> Map<K, V> toMap(
            Iterable<E> c,
            Function<E, K> keyMapper,
            Function<E, V> valueMapper) {
        return Streams.stream(c).collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <E, K> Map<K, E> groupFirst(
            Iterable<E> c,
            Function<E, K> keyMapper,
            Comparator<E> comparator) {
        return Streams.stream(c).collect(Collectors.toMap(keyMapper, Function.identity(),
                (e1, e2) -> Ordering.from(comparator).max(e1, e2)));
    }

    public static <E, K, V> Map<K, V> groupFirst(
            Iterable<E> c,
            Function<E, K> keyMapper,
            Function<E, V> valueMapper,
            Comparator<V> comparator) {
        return Streams.stream(c).collect(Collectors.toMap(keyMapper, valueMapper,
                (e1, e2) -> Ordering.from(comparator).max(e1, e2)));
    }

    public static <E, K> ListMultimap<K, E> groupToList(
            Iterable<E> c,
            Function<E, K> keyMapper) {
        return group(c, keyMapper, Function.identity(), ArrayListMultimap::create);
    }

    public static <E, K, V> ListMultimap<K, V> groupToList(
            Iterable<E> c,
            Function<E, K> keyMapper,
            Function<E, V> valueMapper) {
        return group(c, keyMapper, valueMapper, ArrayListMultimap::create);
    }

    public static <E, K, V, M extends ListMultimap<K, V>> M groupToList(
            Iterable<E> c,
            Function<E, K> keyMapper,
            Function<E, V> valueMapper,
            Supplier<M> multimapSupplier) {
        return group(c, keyMapper, valueMapper, multimapSupplier);
    }

    public static <E, K> SetMultimap<K, E> groupToSet(
            Iterable<E> c,
            Function<E, K> keyMapper) {
        return group(c, keyMapper, Function.identity(), SetMultimapBuilder.hashKeys().hashSetValues()::build);
    }

    public static <E, K, V> SetMultimap<K, V> groupToSet(
            Iterable<E> c,
            Function<E, K> keyMapper,
            Function<E, V> valueMapper) {
        return group(c, keyMapper, valueMapper, SetMultimapBuilder.hashKeys().hashSetValues()::build);
    }

    public static <E, K, V, M extends SetMultimap<K, V>> M groupToSet(
            Iterable<E> c,
            Function<E, K> keyMapper,
            Function<E, V> valueMapper,
            Supplier<M> multimapSupplier) {
        return group(c, keyMapper, valueMapper, multimapSupplier);
    }

    public static <E, K, V, M extends Multimap<K, V>> M group(
            Iterable<E> c,
            Function<E, K> keyMapper,
            Function<E, V> valueMapper,
            Supplier<M> multimapSupplier) {
        return Streams.stream(c).collect(Multimaps.toMultimap(keyMapper, valueMapper, multimapSupplier));
    }

}
