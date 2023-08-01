package com.yx.ssyx.excel.annotation;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Getter
public class Schema {

    private final String name;

    private final List<ColumnMetadata> columns;

    public Schema(String tableName, List<ColumnMetadata> columns) {
        this.name = tableName;
        this.columns = columns;
    }

    @Getter
    public static class ColumnMetadata {
        private final Field field;
        private final String name;
        private boolean primaryKey;
        private int keyOrder;

        public ColumnMetadata(Field field) {
            this.field = field;
            boolean hasAnnotation = field.isAnnotationPresent(Column.class);
            if (hasAnnotation) {
                Column annotation = field.getAnnotation(Column.class);
                this.name = StringUtils.hasText(annotation.name()) ? annotation.name() : field.getName();
                this.primaryKey = annotation.primaryKey();
                this.keyOrder = annotation.keyOrder();
            } else {
                this.name = field.getName();
            }
        }
    }

    public String deleteSql() {
        return String.format("delete from %s " +
                        "where (%s) in (%s)",
                name,
                columns.stream().filter(ColumnMetadata::isPrimaryKey)
                        .sorted(Comparator.comparing(ColumnMetadata::getKeyOrder))
                        .map(ColumnMetadata::getName).collect(Collectors.joining(",")),
                columns.stream().filter(ColumnMetadata::isPrimaryKey)
                        .map((c) -> "?").collect(Collectors.joining(",")));
    }

    public String insertSql() {
        return String.format("insert into %s" +
                        "( %s )" +
                        "values" +
                        "( %s )",
                name,
                columns.stream().map(ColumnMetadata::getName).collect(Collectors.joining(",")),
                columns.stream().map((c) -> "?").collect(Collectors.joining(",")));
    }


    @SneakyThrows
    public <T> Object[] getInsertParamArray(T row) {
        Object[] args = new Object[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            ColumnMetadata c = columns.get(i);
            Field field = c.getField();
            args[i] = fillDefaultValue(field.get(row), c);
        }
        return args;
    }

    @SneakyThrows
    public <T> Object[] getDeleteParamArray(T row, List<ColumnMetadata> primaryKey) {
        Object[] args = new Object[primaryKey.size()];
        for (int i = 0; i < primaryKey.size(); i++) {
            ColumnMetadata c = primaryKey.get(i);
            Field field = c.getField();
            args[i] = fillDefaultValue(field.get(row), c);
        }
        return args;
    }

    private Object fillDefaultValue(Object obj, ColumnMetadata c) {
        if (c.isPrimaryKey() && obj == null) {
            return "";
        }
        if (obj instanceof LocalDateTime || obj instanceof LocalDate) {
            return obj;
        }
        return Objects.toString(obj);
    }
}
