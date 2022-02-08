package com.alexchern.rentahouse.web.controller;

import com.alexchern.rentahouse.web.dto.FlatDTO;
import com.alexchern.rentahouse.web.facade.BaseFacade;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flats")
public class FlatController extends AbstractController<FlatDTO> {

    public FlatController(BaseFacade<FlatDTO> baseFacade) {
        super(baseFacade);
    }

    @Override
    @ApiOperation("Get all Flats")
    @GetMapping
    public List<FlatDTO> getAll() {
        return getBaseFacade().getAll();
    }

    @Override
    @ApiOperation("Create new FLat")
    @PostMapping
    public FlatDTO create(@RequestBody FlatDTO flatDTO) {
        return getBaseFacade().create(flatDTO);
    }

    @ApiOperation("Get FLat by ID")
    @GetMapping("/{flatId}")
    public FlatDTO getById(@PathVariable long flatId) {
        return getBaseFacade().getByID(flatId);
    }

    @ApiOperation("Update Flat")
    @PutMapping("/{flatId}")
    public FlatDTO update(@PathVariable long flatId, @RequestBody FlatDTO flatDTO) {
        return getBaseFacade().update(flatId, flatDTO);
    }

    @ApiOperation("Dlete Flat")
    @DeleteMapping("/{flatId}")
    public List<FlatDTO> delete(@PathVariable long flatId) {
        getBaseFacade().delete(flatId);

        return getBaseFacade().getAll();
    }
}
