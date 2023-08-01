package com.yx.ssyx.excel.mvc;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.pig4cloud.plugin.excel.annotation.ExcelLine;
import com.pig4cloud.plugin.excel.handler.ListAnalysisEventListener;
import com.pig4cloud.plugin.excel.kit.Validators;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class EnhancedAnalysisEventListener extends ListAnalysisEventListener<Object> {

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException e = (ExcelDataConvertException) exception;
            getErrors().add(new ErrorMessage(
                    lineNum++,
                    Collections.singleton(String.format("%s列数据格式不正确", e.getColumnIndex()))
            ));
        }
    }

    private final List<Object> list = new ArrayList<>();

    private final List<ErrorMessage> errorMessageList = new ArrayList<>();

    private Long lineNum = 1L;

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        lineNum++;

        Set<ConstraintViolation<Object>> violations = Validators.validate(o);
        if (!violations.isEmpty()) {
            Set<String> messageSet = violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            errorMessageList.add(new ErrorMessage(lineNum, messageSet));
        } else {
            Field[] fields = o.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(ExcelLine.class) && field.getType() == Long.class) {
                    try {
                        field.setAccessible(true);
                        field.set(o, lineNum);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            list.add(o);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.debug("Excel read analysed");
    }

    @Override
    public List<Object> getList() {
        return list;
    }

    @Override
    public List<ErrorMessage> getErrors() {
        return errorMessageList;
    }

}
