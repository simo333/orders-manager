package com.simo333.spring.projects.ordersmanager.data;

import com.simo333.spring.projects.ordersmanager.model.FurnitureType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FurnitureTypeRepository extends JpaRepository<FurnitureType, Long> {
        Optional<FurnitureType> findFurnitureTypeById(Long id);

    void deleteFurnitureTypeById(Long id);
}