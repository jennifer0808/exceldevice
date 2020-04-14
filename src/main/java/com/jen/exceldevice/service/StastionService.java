package com.jen.exceldevice.service;

import com.jen.exceldevice.pojo.Stastion;


public interface StastionService {
    int getStastionListBy(Stastion stastion);

    int insertStastion(Stastion stastion);

    int updateStasttion(Stastion stastion);
}
