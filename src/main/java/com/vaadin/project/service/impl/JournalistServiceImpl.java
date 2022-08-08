package com.vaadin.project.service.impl;

import com.vaadin.project.model.Address;
import com.vaadin.project.model.Journalist;
import com.vaadin.project.repository.AddressRepository;
import com.vaadin.project.repository.JournalistRepository;
import com.vaadin.project.service.JournalistService;
import com.vaadin.project.util.FieldsEnum;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class JournalistServiceImpl implements JournalistService {

    private final JournalistRepository journalistRepository;
    private final AddressRepository addressRepository;

    public JournalistServiceImpl(JournalistRepository journalistRepository,
                                 AddressRepository addressRepository) {
        this.journalistRepository = journalistRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void parseToJdbc(InputStream inputStream) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            for (Sheet sheet : workbook) {
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                while (rowIterator.hasNext()) {
                    Journalist journalist = validAndSave(rowIterator.next());
                    save(journalist);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Journalist> findAll() {
        return journalistRepository.findAll();
    }

    @Override
    public Journalist save(Journalist journalist) {
        journalist.setAddress(addressRepository.save(journalist.getAddress()));
        return journalistRepository.save(journalist);
    }

    @Override
    public void delete(Journalist journalist) {
        journalistRepository.delete(journalist);
    }

    private Journalist validAndSave(Row cells) {
        int count = 0;
        Journalist journalist = new Journalist();
        Address address = new Address();
        journalist.setAddress(address);

        for (FieldsEnum fieldsEnum : FieldsEnum.values()) {
            Cell cell = cells.getCell(count);
            count++;
            switch (fieldsEnum) {
                case firstName:
                    journalist.setFirstName(checkCell(cell));
                    break;
                case lastName:
                    journalist.setLastName(checkCell(cell));
                    break;
                case title:
                    journalist.setTitle(checkCell(cell));
                case mediaOutlets:
                    journalist.setMediaOutlets(checkCell(cell));
                    break;
                case emailAddress:
                    journalist.setEmailAddress(checkCell(cell));
                    break;
                case twitterName:
                    journalist.setTwitterName(checkCell(cell));
                    break;
                case twitterUrl:
                    journalist.setTwitterUrl(checkCell(cell));
                    break;
                case linkedInUrl:
                    journalist.setLinkedInUrl(checkCell(cell));
                    break;
                case facebookUrl:
                    journalist.setFacebookUrl(checkCell(cell));
                    break;
                case instagramUrl:
                    journalist.setInstagramUrl(checkCell(cell));
                    break;
                case mediaOutletAddress:
                    address.setMediaOutletAddress(checkCell(cell));
                    break;
                case address2:
                    address.setAddress2(checkCell(cell));
                    break;
                case city:
                    address.setCity(checkCell(cell));
                    break;
                case state:
                    address.setState(checkCell(cell));
                    break;
                case country:
                    address.setCountry(checkCell(cell));
                    break;
                case zipCode:
                    address.setZipCode(checkCell(cell));
                    break;
                case mediaOutletPhoneNumber:
                    journalist.setMediaOutletPhoneNumber(checkCell(cell));
                    break;
            }
        }
        return journalist;
    }

    private String checkCell(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
