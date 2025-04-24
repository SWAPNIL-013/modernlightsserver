package com.modernlights.service;

import com.modernlights.model.Home;
import com.modernlights.model.HomeCategory;

import java.util.List;

public interface HomeService {

    Home creatHomePageData(List<HomeCategory> categories);

}
