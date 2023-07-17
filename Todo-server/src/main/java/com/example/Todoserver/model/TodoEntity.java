package com.example.Todoserver.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@Entity
// 이렇게 되면 JPA에서 정의된 필드들을 바탕으로 데이터베이스에 테이블을 만들어준다.
@AllArgsConstructor //선언된 모든 필드를 파라미터로 갖는 생성자를 자동으로 만들어준다.
@NoArgsConstructor //파라미터가 아예없는 기본생성자를 자동으로 만들어준다.

public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //알아서 기본키 생성해줌

    private Long id; //변수는 보통 직접 접근 및 변경이 안되도록 private 선언자를 통해 지정한다.

    @Column(nullable = false)
    private String title;

    @Column(name = "todoOrder", nullable = false)
    private Long order;

    @Column(nullable = false)
    private Boolean completed;
}
