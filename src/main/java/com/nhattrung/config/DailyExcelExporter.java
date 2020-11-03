/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhattrung.config;
import com.nhattrung.entity.Customer;
import com.nhattrung.entity.OrderList;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import javax.servlet.ServletOutputStream;


import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;



import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DailyExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<OrderList> orderLists;
     
    public DailyExcelExporter(List<OrderList> orderLists) {
        this.orderLists = orderLists;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Orders");
        
        CellStyle styleDate = workbook.createCellStyle();
        XSSFFont fontDate = workbook.createFont();
        fontDate.setBold(true);
        fontDate.setFontHeight(20);
        styleDate.setFont(fontDate);
        for (OrderList orderList : orderLists) {
        //DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss");
        //String date = dateFormatter.format(orderList.getOrderDate()); 
        Row rowdate = sheet.createRow(0);
        createCell(rowdate, 2, "Daily Report " + orderList.getOrderDate(), styleDate); 
        }
        Row row = sheet.createRow(1);  
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        
        
        createCell(row, 0, "Order Id", style);         
        createCell(row, 1, "Customer Address", style);  
        createCell(row, 2, "Phone", style);  
        createCell(row, 3, "Username", style);  
        createCell(row, 4, "Total", style);       
        
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else  {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 2;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();        
        font.setFontHeight(14);
        style.setFont(font);
        
        
                 
        for (OrderList orderList : orderLists) {
            Customer customer = new Customer();
            customer.setOrderLists(orderLists);
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, orderList.getOrderId(), style);                       
            createCell(row, columnCount++, orderList.getCustomer().getCustomerAddress(), style);
            createCell(row, columnCount++, orderList.getCustomer().getPhone(), style);
            createCell(row, columnCount++, orderList.getCustomer().getUsername(), style);
            createCell(row, columnCount++, orderList.getAmount(), style);     
        }
        Row sumRow = sheet.createRow(rowCount);
        Cell sumRowTitle = sumRow.createCell(0);
        sumRowTitle.setCellValue("Total Money In Date: ");
        sumRowTitle.setCellStyle(style);
        
        String strFomula = "SUM(E3:E"+rowCount+")";
        Cell sumCell = sumRow.createCell(4);
        sumCell.setCellFormula(strFomula);
        sumCell.setCellValue(true);
        sumCell.setCellStyle(style);
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}