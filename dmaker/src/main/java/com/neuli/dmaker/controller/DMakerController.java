package com.neuli.dmaker.controller;

import com.neuli.dmaker.dto.CreateDeveloper;
import com.neuli.dmaker.dto.DeveloperDetailDto;
import com.neuli.dmaker.dto.DeveloperDto;
import com.neuli.dmaker.dto.EditDeveloper;
import com.neuli.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @RestController RestController 라는 타입의 bean 으로 등록하겠다.
 * @Controller + @ResponseBody
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {

    private final DMakerService dMakerService;

    /**
     *  정보를 Entity 로 응답을 내려주는 방식은 좋지않은 패턴이다.
     *  1. 불필요한 정보를 내려줄 수 도 있다.
     *  2. transaction 이 제대로 없는 정보를 접근히먄 안된다.
     */
    @GetMapping("/developers")
    public List<DeveloperDto> getAllEmployedDevelopers() {
        log.info("GET /developers HTTP/1.1");
        return dMakerService.getAllEmployedDevelopers();
    }

    @GetMapping("/developer/{memberId}")
    public DeveloperDetailDto getDeveloperByMemberId(@PathVariable String memberId) {
        log.info("GET /developer/{memberId} HTTP/1.1");
        return dMakerService.getDeveloperDetail(memberId);
    }


    // @Valid annotation 을 달아주어야 requestBody 에 담으면서 validation 이 동작한다.
    // methodArgumentNotValidException 발생
    @PostMapping("/create-developer")
    public CreateDeveloper.Response createDevelopers(@Valid @RequestBody CreateDeveloper.Request request) {
        log.info("request : {}", request);
        return dMakerService.createDeveloper(request);
    }

    // @PutMapping 은 전부 수정, @PatchMapping 은 일부 수정
    @PutMapping("/developer/{memberId}")
    public DeveloperDetailDto editDeveloperByMemberId(
            @PathVariable String memberId,
            @Valid @RequestBody EditDeveloper.Request request) {
        log.info("PUT /developer/{memberId} HTTP/1.1");
        return dMakerService.editDeveloperDetail(memberId, request);
    }

    @DeleteMapping("/delete-developer")
    public void deleteAllDevelopers() {
        log.info("DELETE /delete-developer");
        dMakerService.deleteAllDevelopers();
    }

    @DeleteMapping("/delete-developer/{memberId}")
    public DeveloperDetailDto deleteDeveloperByMemberId(@PathVariable String memberId) {
        log.info("DELETE /delete-developer/{memberId}");
        return dMakerService.deleteDeveloperByMemberId(memberId);
    }
}
