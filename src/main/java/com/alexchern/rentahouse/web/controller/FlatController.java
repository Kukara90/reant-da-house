package com.alexchern.rentahouse.web.controller;

import com.alexchern.rentahouse.web.dto.FlatDTO;
import com.alexchern.rentahouse.web.facade.BaseFacade;
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

    @Override
    @PostMapping
    public FlatDTO create(FlatDTO flatDTO) {
        return getBaseFacade().create(flatDTO);
    }

    /*@Override
    public FlatFacade getBaseFacade() {
        return new FlatFacade();
    }*/
}
