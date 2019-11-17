package com.palibra.walletapi.controller;

import com.palibra.walletapi.controller.common.ApiResponse;
import com.palibra.walletapi.domain.test.TempTable;
import com.palibra.walletapi.domain.test.TestRepository;
import com.palibra.walletapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestRepository testRepository;

    @GetMapping("/what/{keyword}")
    public ResponseEntity<?> testWhatever(@PathVariable String keyword) {

        TempTable test = testRepository.findByName(keyword)
                .orElseThrow( () -> new ResourceNotFoundException("TempTable", "name", keyword) );

        return ApiResponse.Success(test);
    }
}
