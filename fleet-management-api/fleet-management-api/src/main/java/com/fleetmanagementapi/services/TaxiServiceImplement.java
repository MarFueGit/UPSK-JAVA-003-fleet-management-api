package com.fleetmanagementapi.services;

import com.fleetmanagementapi.dao.TaxiDao;
import com.fleetmanagementapi.models.Taxi;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxiServiceImplement implements  TaxiService{

    @Autowired
    private TaxiDao taxiDao;
    @Override
    @Transactional
    public List<Taxi> findAll() {
        return (List<Taxi>) taxiDao.findAll();
    }
}
