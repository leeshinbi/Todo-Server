package com.example.Todoserver.controller;


import com.example.Todoserver.model.TodoEntity;
import com.example.Todoserver.model.TodoRequest;
import com.example.Todoserver.model.TodoResponse;
import com.example.Todoserver.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ObjectUtils;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin //보안의 이유로 http요청을 안전하게 할 수 있게 하기 위한 어노테이션
@AllArgsConstructor
@RequestMapping("/") //들어온 요청을 특정 method와 매핑하기 위해 사용하는 어노테이션


public class TodoController {
    private final TodoService service;


    @PostMapping //C : CREATE
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        System.out.println("CREATE");

        if (ObjectUtils.isEmpty(request.getTitle()))  //title값이 비어있다면 오류
            return ResponseEntity.badRequest().build();

        if (ObjectUtils.isEmpty(request.getOrder()))  //Order값은 비어있어도 오류가 아니기에, 기본 값 '0L'을 넣어줌
            request.setOrder(0L);

        if (ObjectUtils.isEmpty(request.getCompleted()))
            request.setCompleted(false);

        TodoEntity result = this.service.add(request);
            return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("{id}") //R : READ 'ONE' 하나를 조회
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id){
        System.out.println("READ ONE");
        TodoEntity result = this.service.searchById(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping //R: READ 'ALL' 전체를 조회
    public ResponseEntity<List<TodoResponse>> readAll(){ //전체 값을 읽어오니까 리스트 값을 넣어줌
        System.out.println("READ ALL");
        List<TodoEntity> list = this.service.searchAll();

        //TodoResponse 클래스의 생성자를 호출하여 TodoEntity를 TodoResponse로 매핑합니다.
        List<TodoResponse> response = list.stream().map(TodoResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}") //U : UPDATE
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request){
        System.out.println("UPDATE");
        TodoEntity result = this.service.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("{id}")  //D : DELETE 'ONE'
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        System.out.println("DELETE ONE");
        this.service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping //D : DELETE 'ALL'
    public ResponseEntity<?> deleteAll(){ //All은 따로 @PathVariable 할 필요 없음
        System.out.println("DELETE ALL");
        this.service.deleteAll();
        return ResponseEntity.ok().build();
    }


}
