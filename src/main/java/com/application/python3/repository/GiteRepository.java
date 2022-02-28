package com.application.python3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.python3.entities.Gite;
import com.application.python3.entities.GiteId;

/* Data layer dell'applicativo: DAO */

@Repository("giteRepository")
public interface GiteRepository extends JpaRepository<Gite, GiteId> {
}