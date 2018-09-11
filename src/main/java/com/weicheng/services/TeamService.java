package com.weicheng.services;

import com.weicheng.domain.Manager;
import com.weicheng.domain.Player;
import com.weicheng.domain.Team;

import java.util.UUID;

/**
 * Created by Weicheng on 9/10/2018.
 */
public interface TeamService {
    Team getById (UUID id);

    UUID create(String name);

    void hireManager(UUID teamId, String name, int years);

    void recruitPlayer(UUID teamId, String name, int age);
}
