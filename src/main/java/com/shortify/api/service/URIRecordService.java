package com.shortify.api.service;

import com.shortify.api.entity.URIRecordEntity;
import com.shortify.api.helper.CRC32HashingHelper;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

public class URIRecordService {
    public String createUriResource(String uri) {
        // Checking if longUri already exists in the database
        URIRecordEntity existantUriRecord = URIRecordEntity.findByLongUri(uri);

        if (existantUriRecord != null) {
            existantUriRecord.setUpdateTime(LocalDateTime.now());
            existantUriRecord.persist();

            return existantUriRecord.getShortUriHash();
        };

        URIRecordEntity uriRecord = new URIRecordEntity(uri);
        String uriHash = CRC32HashingHelper.getCRC32Representation(uri);

        uriRecord.setShortUriHash(uriHash);
        uriRecord.setCreationTime(LocalDateTime.now());
        uriRecord.setUpdateTime(LocalDateTime.now());

        uriRecord.persistAndFlush();

        return uriHash;
    }
}
