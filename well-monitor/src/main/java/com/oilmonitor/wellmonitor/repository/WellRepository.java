package com.oilmonitor.wellmonitor.repository;

import com.oilmonitor.wellmonitor.model.Well;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WellRepository extends JpaRepository<Well, Long> {
    List<Well> findByStatus(String status);
}