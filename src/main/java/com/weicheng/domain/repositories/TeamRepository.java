package com.weicheng.domain.repositories;

import com.weicheng.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by Weicheng on 9/10/2018.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, UUID>{
}
