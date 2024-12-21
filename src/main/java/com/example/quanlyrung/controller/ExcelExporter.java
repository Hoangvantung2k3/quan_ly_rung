package com.example.quanlyrung.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.util.Reflection;

import java.io.IOException;
import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExcelExporter<T> {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<T> list;
    private String sheetName;

    public ExcelExporter(List<T> list, String sheetName) {
        this.list = list;
        workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet(sheetName);
    }

    private void writeHeaderRow() {
    }

    private void writeDataRows() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int rowCount = 1;
        for (T value : list) {
            Row row = sheet.createRow(rowCount);

            Class tClass = value.getClass();
            Method[] methods = tClass.getMethods();
            List<String> listGetMethod = new ArrayList<>();
            for (Method m : methods) {
                if (m.getName().contains("Id") == false && m.getName().contains("get") && m.getName().contains("getClass") == false) {
                    listGetMethod.add(m.getName());
                }
            }
            int i = 0;
            for (String l : listGetMethod) {

                Cell cell = row.createCell(i);
                String data  = (String) tClass.getDeclaredMethod(l).invoke(value);
                cell.setCellValue(data);
                sheet.autoSizeColumn(i);

                i++;
            }
        rowCount++;
        }
    }
    public void export(HttpServletResponse response) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        writeDataRows();

        ServletOutputStream outputStream =response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }
}