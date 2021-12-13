package com.neuli.dmaker.controller;

import com.neuli.dmaker.dto.CreateDeveloper;
import com.neuli.dmaker.dto.DeveloperDetailDto;
import com.neuli.dmaker.dto.DeveloperDto;
import com.neuli.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {

    private final DMakerService dMakerService;

    @GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers() {
        log.info("GET /developers HTTP/1.1");
        return dMakerService.getAllDevelopers();
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
}
