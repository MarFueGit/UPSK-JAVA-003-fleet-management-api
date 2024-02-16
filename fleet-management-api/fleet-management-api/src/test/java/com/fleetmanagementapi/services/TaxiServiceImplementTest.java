package com.fleetmanagementapi.services;

import com.fleetmanagementapi.dao.TaxiDao;
import com.fleetmanagementapi.models.Taxi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaxiServiceImplementTest {

    private TaxiServiceImplement taxiService;
    private TaxiDao taxiDao;

    @BeforeEach
    void setUp() {
        taxiDao = mock(TaxiDao.class);
        taxiService = new TaxiServiceImplement(taxiDao);
    }

    @Test
    void testFindAll() {
        // Mock data para simular la peticion a la  BD
        List<Taxi> mockTaxiList = List.of(new Taxi(), new Taxi());
        Page<Taxi> mockPage = new PageImpl<>(mockTaxiList);

        // Cuando invoquen a findAll hago que moquito responda
        when(taxiDao.findAll(any(Pageable.class))).thenReturn(mockPage);

        // Llamamos el metodo findALl
        Page<Taxi> result = taxiService.findAll(PageRequest.of(0, 10));

        // Verificamos
        verify(taxiDao).findAll(PageRequest.of(0, 10));

        // Revisamos que si este igual la respuesta esperada
        assertEquals(mockPage, result);
    }
}