package com.simo333.spring.projects.ordersmanager.resources;

import com.simo333.spring.projects.ordersmanager.model.Model;
import com.simo333.spring.projects.ordersmanager.service.ModelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/models")
public class ModelController {

    private final ModelService service;

    public ModelController(ModelService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<Page<Model>> findAllModelsInPages(Pageable page) {
        Page<Model> models = service.findAllModelsInPages(page);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Model> findModelById(@PathVariable("id") Long id) {
        Model actualModel = service.findModelById(id);
        if (actualModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actualModel, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Model> newModel(@RequestBody Model model) {
        Model newModel = service.addModel(model);
        return new ResponseEntity<>(newModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateModel(@RequestBody Model model, @PathVariable("id") Long id) {
        Model actualModel = service.findModelById(id);
        if (actualModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        model.setId(id);
        actualModel = service.updateModel(model);
        return new ResponseEntity<>(actualModel, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteModel(@PathVariable("id") Long id) {
        if(service.findModelById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteModel(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
