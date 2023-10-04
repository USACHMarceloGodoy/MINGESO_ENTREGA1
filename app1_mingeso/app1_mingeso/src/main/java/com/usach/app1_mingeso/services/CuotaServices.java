package com.usach.app1_mingeso.services;
import com.usach.app1_mingeso.entities.CuotaEntity;
import com.usach.app1_mingeso.entities.EstudianteEntity;
import com.usach.app1_mingeso.repositories.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CuotaServices {
    @Autowired
    CuotaRepository CuotaRepository;
    public CuotaEntity guardarCuota(CuotaEntity cuota){
        return CuotaRepository.save(cuota);
    }
    public ArrayList<CuotaEntity> obtenerCuotas() {
        return (ArrayList<CuotaEntity>) CuotaRepository.findAll();
    }
}
