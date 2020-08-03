package com.transwarp.util;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;

/**
 * @Author zhouchaolong
 * @Description
 * @Date 2020/3/1211:25
 **/
public class NewExportParams  extends ExportParams {
    /**
     * Excel 导出版本
     */
    private ExcelType type              = ExcelType.XSSF;

    public ExcelType getType() {
        return type;
    }

    public void setType(ExcelType type) {
        this.type = type;
    }
}
