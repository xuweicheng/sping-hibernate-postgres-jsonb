package com.weicheng.controllers;

import com.weicheng.domain.Team;
import com.weicheng.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by Weicheng on 9/10/2018.
 */
@RestController
@RequestMapping("/appointments")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/get/{teamId}")
    public Team get(@PathVariable("teamId") UUID teamId) {
        return teamService.getById(teamId);
    }

    @PostMapping("/create")
    public UUID create(String name) {
        return teamService.create(name);
    }

    @PutMapping("/manager/{teamId}")
    public void hireManager(@PathVariable("teamId") UUID teamId,
                            @RequestParam(value="name") String name,
                            @RequestParam(value="years") Integer years) {
        teamService.hireManager(teamId, name, years);
    }

    @PutMapping("/player/{teamId}")
    public void recruitPlayer(@PathVariable("teamId") UUID teamId,
                              @RequestParam(value="name") String name,
                              @RequestParam(value="age") Integer age) {
        teamService.recruitPlayer(teamId, name, age);
    }
}
