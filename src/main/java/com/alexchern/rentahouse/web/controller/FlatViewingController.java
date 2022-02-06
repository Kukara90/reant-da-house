package com.alexchern.rentahouse.web.controller;

import com.alexchern.rentahouse.web.dto.FlatViewingDTO;
import com.alexchern.rentahouse.web.facade.BaseFacade;
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

    @Override
    @PostMapping
    public FlatViewingDTO create(
            @RequestBody FlatViewingDTO flatViewingDTO
    ) {
        return getBaseFacade().create(flatViewingDTO);
    }
}
