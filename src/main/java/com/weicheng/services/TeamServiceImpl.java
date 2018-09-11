package com.weicheng.services;

import com.weicheng.domain.Manager;
import com.weicheng.domain.Player;
import com.weicheng.domain.Team;
import com.weicheng.domain.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by Weicheng on 9/10/2018.
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

//    public TeamServiceImpl(@Autowired TeamRepository teamRepository) {
//        this.teamRepository = teamRepository;
//    }

    @Override
    public Team getById(UUID id) {
        Team team = teamRepository.findOne(id);
        return team;
    }

    @Override
    @Transactional
    public UUID create(String name) {
        Team team = new Team(name);
        teamRepository.save(team);
        return team.getId();
    }

    @Override
    @Transactional
    public void hireManager(UUID teamId, String name, int years) {
        Manager manager = new Manager(name, years);
        Team team = teamRepository.findOne(teamId);
        team.hireManager(manager);
    }

    @Override
    @Transactional
    public void recruitPlayer(UUID teamId, String name, int age) {
        Player player = new Player(name, age);
        Team team = teamRepository.findOne(teamId);
        team.recruitPlayer(player);
    }


}
