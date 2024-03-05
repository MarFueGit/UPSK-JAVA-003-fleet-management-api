package com.fleetmanagementapi.services;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.List;

public interface IExcelService {
    public Workbook createExcelFile(List<Object> data) throws IOException;
}
