package com.alexchern.rentahouse.web.controller;

import com.alexchern.rentahouse.web.dto.FlatViewingDTO;
import com.alexchern.rentahouse.web.facade.BaseFacade;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flat-viewings")
public class FlatViewingController extends AbstractController<FlatViewingDTO> {

    public FlatViewingController(BaseFacade<FlatViewingDTO> baseFacade) {
        super(baseFacade);
    }

    @Override
    @ApiOperation("Get all Flats Viewings")
    @GetMapping
    public List<FlatViewingDTO> getAll() {
        return getBaseFacade().getAll();
    }

    @Override
    @ApiOperation("Create new Flat Viewing")
    @PostMapping
    public FlatViewingDTO create(
            @RequestBody FlatViewingDTO flatViewingDTO
    ) {
        return getBaseFacade().create(flatViewingDTO);
    }

    @ApiOperation("Update Flat Viewing")
    @PutMapping("/{flatViewingId}")
    public FlatViewingDTO update(
            @PathVariable long flatViewingId,
            @RequestBody FlatViewingDTO flatViewingDTO
    ) {
        return getBaseFacade().update(flatViewingId, flatViewingDTO);
    }

    @ApiOperation("Delete FLat Viewing")
    @DeleteMapping("/{flatViewingId}")
    public void delete(@PathVariable long flatViewingId) {
        getBaseFacade().delete(flatViewingId);
    }
}
