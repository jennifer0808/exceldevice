package com.jen.exceldevice.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface LinkService {
    boolean readExcel(String fileName, MultipartFile file) throws IOException;




}
