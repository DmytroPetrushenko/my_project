package com.vaadin.project.service.impl;

import com.vaadin.project.dao.JournalistDao;
import com.vaadin.project.model.Journalist;
import com.vaadin.project.service.JournalistService;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class JournalistServiceImpl implements JournalistService {
    public static final Integer FIRST_NAME = 0;
    public static final Integer SECOND_NAME = 1;
    public static final Integer EMAIL = 2;

    private final JournalistDao journalistDao;

    public JournalistServiceImpl(JournalistDao journalistDao) {
        this.journalistDao = journalistDao;
    }

    @Override
    public void parseToJdbc(InputStream inputStream) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            boolean flag;
            for (Sheet sheet : workbook) {
                flag = false;
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {
                    Optional<Journalist> optional = validAndSave(rowIterator.next());
                    if (optional.isEmpty()) {
                        break;
                    };
                    Journalist journalist = optional.get();
                    if (!flag) {
                        if (journalist.getFirstName().equals("firstName")
                                && journalist.getSecondName().equals("secondName")) {
                            flag = true;
                            continue;
                        }
                    }
                    journalistDao.save(journalist);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Journalist> findAll() {
        return journalistDao.findAll();
    }

    @Override
    public Journalist save(Journalist journalist) {
        return journalistDao.save(journalist);
    }

    private Optional<Journalist> validAndSave(Row cells) {
        int firstCellNum = cells.getFirstCellNum();
        int lastCellNum = cells.getLastCellNum();

        Journalist journalist = new Journalist();

        for (int i = firstCellNum; i < lastCellNum; i++) {
            Cell cell = cells.getCell(i);
            switch (i) {
                case 0:
                    if (cell == null) {
                        journalist.setFirstName(null);
                    } else {
                        journalist.setFirstName(cell.getStringCellValue());
                    }
                    break;
                case 1:
                    if (cell == null) {
                        journalist.setSecondName(null);
                    } else {
                        journalist.setSecondName(cell.getStringCellValue());
                    }
                    break;
                case 2:
                    if (cell == null) {
                        journalist.setEmail(null);
                    } else {
                        journalist.setEmail(cell.getStringCellValue());
                    }
                    break;
            }

        }
        return Optional.ofNullable(journalist);
    }
}
