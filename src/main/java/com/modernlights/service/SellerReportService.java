package com.modernlights.service;

import com.modernlights.model.Seller;
import com.modernlights.model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport( SellerReport sellerReport);

}
