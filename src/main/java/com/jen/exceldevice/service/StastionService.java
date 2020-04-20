package com.jen.exceldevice.service;

import com.jen.exceldevice.pojo.Stastion;

import java.util.List;


public interface StastionService {
    int getStastionListBy(Stastion stastion);

    List<Stastion> getStastionList(Stastion stastion);


    int insertStastion(Stastion stastion);

    int updateStasttion(Stastion stastion);

    List<Stastion> getStastionInnerAll();
}
