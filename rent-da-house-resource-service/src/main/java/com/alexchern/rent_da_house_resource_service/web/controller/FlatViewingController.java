package com.alexchern.rent_da_house_resource_service.web.controller;

import com.alexchern.rent_da_house_resource_service.web.dto.FlatViewingDTO;
import com.alexchern.rent_da_house_resource_service.web.facade.BaseFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flat-viewings")
public class FlatViewingController extends AbstractController<FlatViewingDTO> {

    public FlatViewingController(BaseFacade<FlatViewingDTO> baseFacade) {
        super(baseFacade);
    }

    @Override
    @GetMapping
    public List<FlatViewingDTO> getAll() {
        return getBaseFacade().getAll();
    }

    @PostMapping
    public FlatViewingDTO create(
    ) {
        return getBaseFacade().create();
    }
}
