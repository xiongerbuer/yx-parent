package com.atguigu.ssyx.common.jpa;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.annotation.Nullable;
import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Collection;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    static <T> Specification<T> alwaysTrue() {
        return (root, query, cb) -> cb.isTrue(cb.literal(TRUE));
    }

    static <T> Specification<T> alwaysFalse() {
        return (root, query, cb) -> cb.isTrue(cb.literal(FALSE));
    }

    static <T, Y extends Comparable<? super Y>> Specification<T> isNull(SingularAttribute<T, Y> attr) {
        return (root, query, cb) -> cb.isNull(root.get(attr));
    }

    static <T, Y extends Comparable<? super Y>> Specification<T> isNotNull(SingularAttribute<T, Y> attr) {
        return (root, query, cb) -> cb.isNotNull(root.get(attr));
    }

    @Nullable
    static <T> Specification<T> eq(SingularAttribute<T, ?> attr, Object value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.equal(root.get(attr), value);
    }

    @Nullable
    static <T> Specification<T> notEq(SingularAttribute<T, ?> attr, Object value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.notEqual(root.get(attr), value);
    }

    @Nullable
    static <T> Specification<T> startsWith(SingularAttribute<T, ?> attr, Object value) {
        return isNullOrEmpty(value) ? null
                : (root, query, cb) -> cb.like(root.<String>get(attr.getName()), value.toString() + "%");
    }

    @Nullable
    static <T> Specification<T> in(SingularAttribute<T, ?> attr, Collection<?> values) {
        if (isNullOrEmpty(values)) {
            return null;
        }
        return (root, query, cb) -> {
            Path<?> expression = root.get(attr);
            return cb.isTrue(expression.in(values));
        };
    }

    @Nullable
    static <T> Specification<T> notIn(SingularAttribute<T, ?> attr, Collection<?> values) {
        if (isNullOrEmpty(values)) {
            return null;
        }
        return (root, query, cb) -> {
            Path<?> expression = root.get(attr);
            return cb.isTrue(expression.in(values).not());
        };
    }

    @Nullable
    static <T, Y extends Comparable<? super Y>> Specification<T> lt(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lessThan(root.get(attr), value);
    }

    @Nullable
    static <T, Y extends Comparable<? super Y>> Specification<T> lte(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attr), value);
    }

    @Nullable
    static <T, Y extends Comparable<? super Y>> Specification<T> gt(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.greaterThan(root.get(attr), value);
    }

    @Nullable
    static <T, Y extends Comparable<? super Y>> Specification<T> gte(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(attr), value);
    }

    @Nullable
    static <T> Specification<T> like(SingularAttribute<T, String> attr, String value) {
        if (isNullOrEmpty(value)) {
            return null;
        }
        // 由于mysql查询_会转义，like默认不带%的模糊搜索
        value = value.replaceAll("_", "\\\\_")
                .replaceAll("%", "\\\\%");
        String finalValue = value;
        return (root, query, cb) -> cb.like(root.get(attr), "%" + finalValue + "%");
    }

    @Nullable
    static <T, Y extends Comparable<? super Y>> Specification<T> between(SingularAttribute<T, Y> attr, Y from, Y to) {
        if (from == null) {
            return lte(attr, to);
        }
        if (to == null) {
            return gte(attr, from);
        }
        return (root, query, cb) -> cb.between(root.get(attr), from, to);
    }

    @Nullable
    static <T, Y extends Comparable<? super Y>> Specification<T> le(SingularAttribute<T, Y> attr, Y value) {
        return isNullOrEmpty(value) ? null : (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attr), value);
    }

    static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    static boolean isNullOrEmpty(Object obj) {
        return obj == null || obj instanceof String && ((String) obj).isEmpty();
    }

}
