package com.fleetmanagementapi.services;
import com.fleetmanagementapi.dao.TrajectoryDao;
import com.fleetmanagementapi.models.Trajectorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrajectoryServiceImplement implements TrajectoryService {

    @Autowired
    private TrajectoryDao trajectoryDao;

    public TrajectoryServiceImplement(TrajectoryDao trajectoryDao) {
        this.trajectoryDao = trajectoryDao;
    }

    @Override
    public Page<Trajectorie> findByTaxiIdAndDateGreaterThanEqual(int taxiId, LocalDateTime date, Pageable pageable) {
        return trajectoryDao.findByTaxiIdAndDateGreaterThanEqual(taxiId, date, pageable);
    }

    @Override
    public List<Trajectorie> findLatestTrajectories() {
        return trajectoryDao.findLatestTrajectories();
    }
}
