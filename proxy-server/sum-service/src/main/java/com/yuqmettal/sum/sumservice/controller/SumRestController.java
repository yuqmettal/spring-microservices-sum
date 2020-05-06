package com.yuqmettal.sum.sumservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.yuqmettal.sum.sumservice.model.Sum;
import com.yuqmettal.sum.sumservice.repository.SumRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/sums")
public class SumRestController {
    
    @Autowired
    private SumRepository sumRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntityModel<Sum>> getSum(
        @RequestParam("userName") String userName,
        @PathVariable("id") Long id
    ) {
        Optional<Sum> sum = sumRepository.findByIdAndUserName(id, userName);
        if (!sum.isPresent()) {
            return new ResponseEntity<EntityModel<Sum>>(HttpStatus.NOT_FOUND);
        }
        EntityModel<Sum> sumModel = new EntityModel<Sum>(sum.get(),
                linkTo(methodOn(SumRestController.class).getSum(userName, sum.get().getId())).withSelfRel());
        return new ResponseEntity<EntityModel<Sum>>(sumModel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Sum>>> getAllSums(@RequestParam("userName") String userName) {
        List<Sum> sums = (List<Sum>) sumRepository.findByUserName(userName);
        Link links[] = { linkTo(methodOn(SumRestController.class).getAllSums(userName)).withRel("getAllSums") };
        List<EntityModel<Sum>> list = new ArrayList<EntityModel<Sum>>();
        for (Sum sum : sums) {
            list.add(new EntityModel<Sum>(sum,
                    linkTo(methodOn(SumRestController.class).getSum(userName, sum.getId())).withSelfRel()));
        }
        CollectionModel<EntityModel<Sum>> sumRes = new CollectionModel<EntityModel<Sum>>(list, links);
        return new ResponseEntity<CollectionModel<EntityModel<Sum>>>(sumRes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Sum>> postSum(
        @RequestParam("userName") String userName,
        @RequestBody Sum sum,
        UriComponentsBuilder uBuilder
    ) {
        sum.setUserName(userName);
        Sum newSum = sumRepository.save(sum);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uBuilder.path("/sums/{userName}/{id}").buildAndExpand(userName, sum.getId()).toUri());
        EntityModel<Sum> sumRes = new EntityModel<Sum>(newSum,
                linkTo(methodOn(SumRestController.class).getSum(userName, newSum.getId())).withSelfRel());
        return new ResponseEntity<EntityModel<Sum>>(sumRes, headers, HttpStatus.OK);

    }
}