package hellojpa;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")

public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")  //DB 컬럼이름
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamID;

    @ManyToOne  //관계, member입장(테이블의 입장)에서 many to one
    @JoinColumn(name = "TEAM_ID") // 이 관계에서 조인할 컬럼을 말해줌
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {  //로직이 들어갔으므로 setTeam -> changeTeam으로 변경
        this.team = team;
        team.getMembers().add(this);  // 연관관계 편의메서드, 양방향 쪽에 모두 값을 세팅하는데, 이곳에 설정해놓으면 누락을 방지할 수 있음
    }
}
