package com.ssl.finalproject.controller;

import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.service.ExpertService;
import com.ssl.finalproject.service.TagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class ExpertController {


    private final Logger log = LoggerFactory.getLogger(TagController.class);
    private final ExpertService expertService;
    private final TagService tagService;

    public ExpertController(ExpertService expertService, TagService tagService) {
        this.expertService = expertService;
        this.tagService = tagService;
    }


    /**
     * crea una Expert en la BD
     * @param Expert
     * @return ResponseEntity<Expert>
     * @throws URISyntaxException
     */
    @PostMapping("/expertos")
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
    @PutMapping(value = "/expertos")
    public ResponseEntity<Expert> modifyExpert(@RequestBody Expert expert) {
        log.debug("Modify Expert");
        if (expert.getId()==null) {
            log.error("Error en Modify Tag");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Expert resultado = expertService.updateExpert(expert);
        return ResponseEntity.ok().body(resultado);
    }

    /**
     * Find All  con diferentes filtros y paginacion
     * @param nombre
     * @param modalidad
     * @param id
     * @param estado
     * @param etiqueta
     * @param pagina
     * @param limite
     * @return ResponseEntity<List<Expert>>
     */
    @GetMapping("/expertos")
    public List<Expert> findAllExperts(@RequestParam(name="nombre", required=false) String nombre,
                                       @RequestParam(name="puntuacion", required=false) Integer puntuacion,
                                       @RequestParam(name="modalidad", required=false) String modalidad,
                                       @RequestParam(name="id", required=false) Long id,
                                       @RequestParam(name="estado", required=false) String estado,
                                       @RequestParam(name="etiqueta", required=false) Long etiqueta,
                                       @RequestParam(name = "pagina", required = false, defaultValue = "0") Integer pagina,
                                       @RequestParam(name = "limite", required = false, defaultValue = "10") Integer limite){

        System.out.println(puntuacion);
        System.out.println(nombre);
        System.out.println(modalidad);
        System.out.println(id);
        System.out.println(estado);
        System.out.println(etiqueta);
        System.out.println(pagina);
        System.out.println(limite);
        if(puntuacion!=null){
           return expertService.findAllExpertByPuntuacion(puntuacion,pagina,limite);
        }else if(nombre!=null){
          return expertService.findAllByNombre(nombre,pagina,limite);
        }else if(estado!=null){
            return expertService.findAllByEstado(estado,pagina,limite);
        }else
            if(modalidad!=null){
            return expertService.findAllByModalidad(modalidad,pagina,limite);
        }
            //////////////////////////////////////////////////////////////////////////////////////////////////
      //  else  if(etiqueta !=null){

          //  return expertService.findAllExpertByTag(etiqueta,pagina,limite);

            /////////////////////////////////////////////////////////////////////////////////////////////////////
      //  }
            else if(id!=null){
            Optional<Expert> expertOpt=expertService.findOneExpertById(id);
            if (expertOpt.isPresent()) {
                List<Expert> expertList = Arrays.asList(expertOpt.get());
                return Arrays.asList(expertOpt.get());
            }
        }
            if(limite==0)limite=10;
            System.out.println("por aaqui campeon");
            return expertService.findAllExpert(pagina,limite);


    }

    /**
     * devuelve una etiquetas  de la BD filtrando por id
     * @param id
     * @return ResponseEntity<Expert>
     */
    @GetMapping("/expertos/{id}")
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
    @DeleteMapping(value = "/expertos/{id}")
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
    @DeleteMapping(value = "/expertos")
    @ApiOperation(value = "Borra todas los Expert")
    public ResponseEntity<Void> deleteAll() {
        log.debug("DeleteAll");
        expertService.deleteAllExperts();;
        return  ResponseEntity.noContent().build();
    }

/////////////********************************Metodos Estaticos ************************//////////////////////////

    /**
     * Metodo para Controlar los Opcional de Objetos
     * @param service
     * @param id
     * @return ResponseEntity<List<Expert>>
     */
    private static ResponseEntity<List<Expert>> controlarOpTObj( ExpertService service, Long id ){
        Optional<Expert> expertOpt=service.findOneExpertById(id);
        if (expertOpt.isPresent()){
            List<Expert>expertList= Arrays.asList(expertOpt.get());
            return ResponseEntity.ok().body(expertList);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

//    /**
//     * Metodo para Controlar los Opcional de Listas
//     * @param optListExpert
//     * @return ResponseEntity<List<Expert>>
//     */
////    private static ResponseEntity<List<Expert>> controlarOpTList(Optional<List<Expert>> optListExpert ){
////        if (optListExpert.isPresent()){
////            return ResponseEntity.ok().body(optListExpert.get());
////        }else{
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////
////    }
}
