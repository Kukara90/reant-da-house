package com.alexchern.rent_da_house_resource_service.web.controller;

import com.alexchern.rent_da_house_resource_service.web.dto.FlatDTO;
import com.alexchern.rent_da_house_resource_service.web.facade.BaseFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flats")
public class FlatController extends AbstractController<FlatDTO> {

    public FlatController(BaseFacade<FlatDTO> baseFacade) {
        super(baseFacade);
    }

    @Override
    @GetMapping
    public List<FlatDTO> getAll() {
        return getBaseFacade().getAll();
    }

    @PostMapping
    public FlatDTO create() {
        return getBaseFacade().create();
    }

    /*@Override
    public FlatFacade getBaseFacade() {
        return new FlatFacade();
    }*/
}
