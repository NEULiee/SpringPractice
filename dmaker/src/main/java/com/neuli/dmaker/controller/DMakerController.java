package com.neuli.dmaker.controller;

import com.neuli.dmaker.dto.CreateDeveloper;
import com.neuli.dmaker.dto.DeveloperDetailDto;
import com.neuli.dmaker.dto.DeveloperDto;
import com.neuli.dmaker.dto.EditDeveloper;
import com.neuli.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @RestController
 * RestController 라는 타입의 bean 으로 등록하겠다.
 *
 * @Controller + @ResponseBody
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {

    private final DMakerService dMakerService;

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
