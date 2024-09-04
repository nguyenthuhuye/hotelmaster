package com.example.hotelmaster.service;

import com.example.hotelmaster.entity.Booking;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ExcelExportService {

    public ByteArrayInputStream exportBookingsToExcel(List<Booking> bookings) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Bookings");

            // Tạo tiêu đề cột
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Room Number", "CheckOut Date", "CheckIn Date"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Điền dữ liệu vào các dòng tiếp theo
            int rowIdx = 1;
            for (Booking booking : bookings) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(booking.getId());
                row.createCell(1).setCellValue(booking.getRoomNumber());
                row.createCell(2).setCellValue(booking.getCheckOutDate().toString());
                row.createCell(3).setCellValue(booking.getCheckInDate().toString());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
