package com.ssl.finalproject.controller;

import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.model.Tag;
import com.ssl.finalproject.service.ExpertService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ExpertController {


    private final Logger log = LoggerFactory.getLogger(TagController.class);

    private final ExpertService expertService;

    public ExpertController(ExpertService expertService) {
        this.expertService = expertService;
    }


    /**
     * crea una Expert en la BD
     * @param Expert
     * @return ResponseEntity<Expert>
     * @throws URISyntaxException
     */
    @PostMapping("/experts")
    public ResponseEntity<Expert> createExpert(@RequestBody Expert expert) throws URISyntaxException {
        log.debug("Create Expert");
        Expert resultado=null;
        if (expert.getId()!=null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        resultado=expertService.createExpert(expert);
        return  ResponseEntity.created(new URI("/api/expert/"+resultado.getId())).body(resultado);
    }


    /**
     * metodo que modifica un Expert
     * @param Expert
     * @return ResponseEntity<Expert>
     */
    @PutMapping(value = "/experts")
    public ResponseEntity<Expert> modifyExpert(@RequestBody Expert expert) {
        log.debug("Modify Expert");
        if (expert.getId()==null) {
            log.error("Error en Modify Tag");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Expert resultado = expertService.updateExpert(expert);
        return ResponseEntity.ok().body(resultado);
    }




    // http://localhost:8888/welcome/?name=David&age=28 -> Name: David, age: 28
    // http://localhost:8888/welcome?name=David -> Name: David, age: 0
    // http://localhost:8888/welcome?age=28 -> Name: null, age: 28
    // http://localhost:8888/welcome/" -> Whitelabel page





//,, etiqueta,

    @GetMapping("/experts")
    public List<Expert> findAllExperts(@RequestParam(name="nombre", required=false) String nombre,
                                      // @RequestParam(name="pagina", required=false) String pagina,
                                      // @RequestParam(name="limite", required=false) String limite,
                                       @RequestParam(name="modalidad", required=false) String modalidad,
                                       @RequestParam(name="estado", required=false) String estado){

        if(modalidad!=null) {
            return expertService.findAllByModalidad(modalidad);
        }else if(estado!=null) {
            return expertService.findAllByEstado(estado);
        }else if(nombre!=null){
            return expertService.findAllByNombre(nombre);
        }else{
            return expertService.findAllExpert();
        }

    }




    /**
     * Metodo que devuelve una lista de de Expert
     * @return List<Expert>
     */
//    @GetMapping("/experts")
//    public List<Expert> findAllExperts(){
//        log.debug("Rest request all Expert");
//        return expertService.findAllExpert();
//    }



    /**
     * devuelve una etiquetas  de la BD filtrando por id
     * @param id
     * @return ResponseEntity<Expert>
     */
    @GetMapping("/experts/{id}")
    public ResponseEntity<Expert> findOneExpert(@PathVariable Long id) {
        log.debug("Rest request a Expert with id: "+id);
        Optional<Expert> expOpt = expertService.findOneExpertById(id);
        if (expOpt.isPresent())
            return ResponseEntity.ok().body(expOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *method delete Expert by ID
     * @param id
     * @return noContent
     */
    @DeleteMapping(value = "/experts/{id}")
    @ApiOperation(value = "Borra un Expert por id")
    public ResponseEntity<Void> deleteOne(@ApiParam("Clave primaria tags para Eliminarlo")@PathVariable("id") Long id) {
        log.debug("Delete Expert");
        expertService.deleteOneExpertById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     *method delete all tags
     * @return noContent
     */
    @ApiIgnore
    @DeleteMapping(value = "/experts")
    @ApiOperation(value = "Borra todas los Expert")
    public ResponseEntity<Void> deleteAll() {
        log.debug("DeleteAll");
        expertService.deleteAllExperts();;
        return  ResponseEntity.noContent().build();
    }
}
