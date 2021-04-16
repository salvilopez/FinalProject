package com.ssl.finalproject.controller;

import com.ssl.finalproject.model.Tag;
import com.ssl.finalproject.service.TagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api")
public class TagController {


    private final Logger log = LoggerFactory.getLogger(TagController.class);

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * crea una etiquetas en la BD
     *
     * @param tag
     * @return ResponseEntity<Tag>
     * @throws URISyntaxException
     */
    @PostMapping("/etiquetas")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) throws URISyntaxException {
        log.debug("Create Task");
        Tag resultado = null;
        if (tag.getId() != null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        resultado = tagService.createTag(tag);
        return ResponseEntity.created(new URI("/api/tags/" + resultado.getId())).body(resultado);
    }


    /**
     * metodo que modifica un etiquetas
     *
     * @param tag
     * @return ResponseEntity<Tag>
     */
    @PutMapping(value = "/etiquetas")
    public ResponseEntity<Tag> modifyTag(@RequestBody Tag tag) {
        log.debug("Modify Tag");
        if (tag.getId() == null) {
            log.error("Error en Modify Tag");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Tag resultado = tagService.updateTag(tag);
        return ResponseEntity.ok().body(resultado);
    }

    /**
     * Metodo que devuelve una lista de de etiquetas
     *
     * @return List<Tag>
     */
    @GetMapping("/etiquetas")
    public ResponseEntity<List<Tag>> findTags(@RequestParam(name = "nombre", required = false) String nombre,
                                              @RequestParam(name = "id", required = false) Long id,
                                              @RequestParam(name = "pagina", required = false, defaultValue = "0") Integer pagina,
                                              @RequestParam(name = "limite", required = false, defaultValue = "10") Integer limite) {
        if(id!=null){
            Optional<Tag>optTag=tagService.findOneTagById(id);
            if(optTag.isPresent()){
                List<Tag>tagList= Arrays.asList(optTag.get());
                return ResponseEntity.ok().body(tagList);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else if (nombre != null) {
            Optional<List<Tag>> pageTagOpt = tagService.findAllByNombre(nombre, pagina, limite);
            if (pageTagOpt.isPresent()) {
                return ResponseEntity.ok().body(pageTagOpt.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            List<Tag> pageTag = tagService.findAll(pagina, limite);
            return ResponseEntity.ok().body(pageTag);
        }
    }


    /**
     * devuelve una etiquetas  de la BD filtrando por id
     *
     * @param id
     * @return ResponseEntity<Tag>
     */
    @GetMapping("/etiquetas/{id}")
    public ResponseEntity<Tag> findOneTag(@PathVariable Long id) {
        log.debug("Rest request a Tag with id: " + id);
        Optional<Tag> tagOpt = tagService.findOneTagById(id);
        if (tagOpt.isPresent())
            return ResponseEntity.ok().body(tagOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * method delete User by ID
     *
     * @param id
     * @return noContent
     */
    @DeleteMapping(value = "/etiquetas/{id}")
    @ApiOperation(value = "Borra un tag por id")
    public ResponseEntity<Void> deleteOne(@ApiParam("Clave primaria tags para Eliminarlo") @PathVariable("id") Long id) {
        log.debug("Delete Tag");
        tagService.deleteOneTagById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * method delete all tags
     *
     * @return noContent
     */
    @ApiIgnore
    @DeleteMapping(value = "/etiquetas")
    @ApiOperation(value = "Borra todas los tags")
    public ResponseEntity<Void> deleteAll() {
        log.debug("DeleteAll");
        tagService.deleteAllTags();
        return ResponseEntity.noContent().build();
    }

}

