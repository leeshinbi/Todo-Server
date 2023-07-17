package com.example.Todoserver.repository;

import com.example.Todoserver.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository //Entity에 의해 생성된 DB에 접근하는 메서드(ex) findAll()) 들을 사용하기 위한 인터페이스

//Jpa 사용, TodoEntity에서 id에 해당하는 Long 타입 반환
//JpaRepository<대상으로 지정할 엔티티, 해당 엔티티의 PK의 타입>
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
