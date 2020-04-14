package com.jen.exceldevice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface LinkService {
    boolean readExcel(String fileName, MultipartFile file) throws IOException;


}
