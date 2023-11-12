package com.example.firstmvn.controllers;

import com.example.firstmvn.entities.Factory;
import com.example.firstmvn.services.FactoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/factories")
public class FactoryController {

    @Autowired
    private final FactoryService factoryService;

    public static final String SUCCESSFUL_POST_MSG = "Thanks For Posting!!";
    public static final String SUCCESSFUL_UPDATE_MSG = "Thanks for Updating!!";
    public static final String SUCCESSFUL_DELETE_MSG = "Thanks for Deleting!!";

    public FactoryController(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAll() {
        List<Factory> factories =  factoryService.getAll();
        // pick up here wrap in object
        return new ResponseEntity<Object>(factories, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOne(@PathVariable Long id) {
        Factory factory = factoryService.getOne(id);
        return new ResponseEntity<Object>(factory, HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<String> addOne(@RequestBody Factory factory) {
        factoryService.addOne(factory);
        return new ResponseEntity<String>(SUCCESSFUL_POST_MSG, HttpStatus.OK);
    }

    @PutMapping("")
    @ResponseBody
    public ResponseEntity<String> updateOne(@RequestBody Factory factory) {
        factoryService.updateOne(factory);
        return new ResponseEntity<String>(SUCCESSFUL_UPDATE_MSG, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteOne(@PathVariable Long id) {
        factoryService.deleteOne(id);
        return new ResponseEntity<String>(SUCCESSFUL_DELETE_MSG, HttpStatus.OK);
    }

//    @PutMapping("")
//    @ResponseBody
//    public ResponseEntity<String> updateOneIfProductChanged(@RequestBody Factory factory) {
//        factoryService.updateOneIfProductChanged(factory);
//        return new ResponseEntity<String>(SUCCESSFUL_UPDATE_MSG, HttpStatus.OK);
//    }
}
