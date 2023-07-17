package com.example.Todoserver.service;


import com.example.Todoserver.model.TodoEntity;
import com.example.Todoserver.model.TodoRequest;
import com.example.Todoserver.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


//실제적인 로직이 들어가는 service
@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    //todo 리스트 목록에 아이템을 추가
    public TodoEntity add(TodoRequest request) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setOrder(request.getOrder());
        todoEntity.setCompleted(request.getCompleted());
        return this.todoRepository.save(todoEntity);
    }

    //todo 리스트 목록 중 특정 아이템을 조회
    public TodoEntity searchById(Long id) {
        return this.todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //없을 경우, 404 에러 코드 보여줌
    }

    //todo 리스트 전체 목록을 조회
    public List<TodoEntity> searchAll() {
        return this.todoRepository.findAll();
    }

    //todo 리스트 목록 중 특정 아이템을 수정
    public TodoEntity updateById(Long id, TodoRequest request) {
        TodoEntity todoEntity = this.searchById(id); //엔티티의 특정 아이템의 아이디를 받아서
        if(request.getTitle() != null) { //request의 title값이 null값이 아니면,

            todoEntity.setTitle(request.getTitle()); //엔티티의 title값을 request의 title값으로 수정
        }
        if(request.getOrder() != null) {

            todoEntity.setOrder(request.getOrder());
        }
        if(request.getCompleted() != null) {
            todoEntity.setCompleted(request.getCompleted());
        }
        return this.todoRepository.save(todoEntity); //수정된 값을 레파지토리에 저장
    }

    //todo 리스트 목록 중 특정 아이템을 삭제
    public void deleteById(Long id) { //반환값이 필요없기 때문에 public void를 사용
        this.todoRepository.deleteById(id);
    }

    //todo 리스트 전체 목록을 삭제
    public void deleteAll() {
        this.todoRepository.deleteAll();
    }
}

