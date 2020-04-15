package com.jen.exceldevice.service;

import com.jen.exceldevice.pojo.Link;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface LinkService {
    boolean readExcel(String fileName, MultipartFile file) throws IOException;


    List<Link> getLinkList(Link link);

    int insertLink(Link link);
}
