package com.ssl.finalproject.controller;

import com.ssl.finalproject.model.Tag;
import com.ssl.finalproject.service.TagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
public class TagController {



    private final Logger log = LoggerFactory.getLogger(TagController.class);

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * crea una etiquetas en la BD
     * @param tag
     * @return ResponseEntity<Tag>
     * @throws URISyntaxException
     */
    @PostMapping("/tags")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) throws URISyntaxException {
        log.debug("Create Task");
        Tag resultado=null;
        if (tag.getId()!=null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        resultado=tagService.createTag(tag);
        return  ResponseEntity.created(new URI("/api/tags/"+resultado.getId())).body(resultado);
    }


    /**
     * metodo que modifica un etiquetas
     * @param tag
     * @return ResponseEntity<Tag>
     */
    @PutMapping(value = "/tags")
    public ResponseEntity<Tag> modifyTag(@RequestBody Tag tag) {
        log.debug("Modify Tag");
        if (tag.getId()==null) {
            log.error("Error en Modify Tag");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Tag resultado = tagService.updateTag(tag);
        return ResponseEntity.ok().body(resultado);
    }

    /**
     * Metodo que devuelve una lista de de etiquetas
     * @return List<Tag>
     */
    @GetMapping("/tags")
    public List<Tag> findTags(){
        log.debug("Rest request all Tags");
        return tagService.findAllTag();
    }

    /**
     * devuelve una etiquetas  de la BD filtrando por id
     * @param id
     * @return ResponseEntity<Tag>
     */
    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> findOneTag(@PathVariable Long id) {
        log.debug("Rest request a Tag with id: "+id);
        Optional<Tag> tagOpt = tagService.findOneTagById(id);
        if (tagOpt.isPresent())
            return ResponseEntity.ok().body(tagOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *method delete User by ID
     * @param id
     * @return noContent
     */
    @DeleteMapping(value = "/tags/{id}")
    @ApiOperation(value = "Borra un tag por id")
    public ResponseEntity<Void> deleteOne(@ApiParam("Clave primaria tags para Eliminarlo")@PathVariable("id") Long id) {
        log.debug("Delete Tag");
        tagService.deleteOneTagById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     *method delete all tags
     * @return noContent
     */
    @ApiIgnore
    @DeleteMapping(value = "/tags")
    @ApiOperation(value = "Borra todas los tags")
    public ResponseEntity<Void> deleteAll() {
        log.debug("DeleteAll");
        tagService.deleteAllTags();
        return  ResponseEntity.noContent().build();
    }
}
