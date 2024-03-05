package com.fleetmanagementapi.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExcelServiceImpl implements IExcelService {
    @Override
    public Workbook createExcelFile(List<Object> data) throws IOException {
        // empezamos a darle forma al excel
        Workbook workbook = new XSSFWorkbook();

        // creamos una hoja de cálculo
        Sheet sheet = workbook.createSheet("Taxi Data");

        // Aquí creamos cabeceras
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID TAXI", "PLATE", "LATITUDE", "LONGITUDE", "DATE"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Rellenamos datos
        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            Row row = sheet.createRow(rowIndex + 1);
            Object[] rowData = (Object[]) data.get(rowIndex);
            for (int cellIndex = 0; cellIndex < rowData.length; cellIndex++) {
                Cell cell = row.createCell(cellIndex);
                Object value = rowData[cellIndex];
                if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                } else if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof LocalDateTime) {
                    LocalDateTime localDateTime = (LocalDateTime) value;
                    cell.setCellValue(localDateTime.toString());
                } else {
                    // Manejar otros tipos de datos o tipos no soportados
                    cell.setCellValue(value != null ? value.toString() : "");
                }
            }
        }
        return workbook;
    }

}
