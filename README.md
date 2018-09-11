# sping-hibernate-postgres-jsonb
This is a prototype project following [VLAD MIHALCEA's guide.](https://vladmihalcea.com/how-to-map-json-objects-using-generic-hibernate-types/#comment-9454)

1. Create JsonBinaryType
2. Describe Type Definition in Entity Class
```
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@jsonUUID")
@TypeDefs({
        @TypeDef(name = JsonBinaryType.JSONB, typeClass = JsonBinaryType.class)
})
@Table(name="team")
public class Team {
```
3. Describe the column type for jsonb fields
```
@Column(name="manager", columnDefinition = JsonBinaryType.JSONB)
@Type(type = JsonBinaryType.JSONB)
private Manager manager;

@Column(name="players", columnDefinition = JsonBinaryType.JSONB)
@Type(type = JsonBinaryType.JSONB)
private List<Player> players;
```
