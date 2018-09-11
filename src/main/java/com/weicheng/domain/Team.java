package com.weicheng.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.weicheng.config.hibernate.JsonBinaryType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Weicheng on 9/10/2018.
 */
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@jsonUUID")
@TypeDefs({
        @TypeDef(name = JsonBinaryType.JSONB, typeClass = JsonBinaryType.class)
})
@Table(name="team")
public class Team {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid2")
    @Type(type="pg-uuid")
    protected UUID id;

    @Column(name="name")
    private String name;

    @Column(name="manager", columnDefinition = JsonBinaryType.JSONB)
    @Type(type = JsonBinaryType.JSONB)
    private Manager manager;

    @Column(name="players", columnDefinition = JsonBinaryType.JSONB)
    @Type(type = JsonBinaryType.JSONB)
    private List<Player> players;

    private Team() {
    }

    public Team(String name) {
        this.name = name;
        players = new ArrayList<Player>();
    }

    public void hireManager(Manager manager) {
        this.manager = manager;
    }

    public void recruitPlayer(Player player) {
        players.add(player);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Manager getManager() {
        return manager;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
