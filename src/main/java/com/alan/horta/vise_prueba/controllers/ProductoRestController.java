package com.alan.horta.vise_prueba.controllers;

import com.alan.horta.vise_prueba.config.Translator;
import com.alan.horta.vise_prueba.data.models.producto.Producto;
import com.alan.horta.vise_prueba.data.models.requests.PrecioRequest;
import com.alan.horta.vise_prueba.data.services.IProductoService;
import com.alan.horta.vise_prueba.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200/", "*"})
@RestController
@RequestMapping("/api")
public class ProductoRestController {

    private static final Logger logger = LogManager.getLogger(ProductoRestController.class);

    @Autowired
    private IProductoService productoService;

    @GetMapping("/productos")
    public ResponseEntity<?> productos(){
        List<Producto> lista = null;
        Map<String,Object> response = new HashMap<>();
        try{
            lista = productoService.findAllProductos();
        }catch (DataAccessException e){
            response.put("codigo", Constants.CODIGO_ERROR_SERVER);
            lista = new ArrayList<>();
            response.put("data",lista);
            response.put("mensaje",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("codigo", Constants.CODIGO_EXITO);
        response.put("data",lista);
        response.put("mensaje", Translator.toLocale(Constants.MENSAJE_EXITO_ALL));
        logger.info(response);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<?> productoEspecifico(@PathVariable Long id){
        Producto producto = null;
        Map<String,Object> response = new HashMap<>();
        try{
            producto = productoService.findProductoById(id);
        }catch (DataAccessException e){
            response.put("codigo", Constants.CODIGO_ERROR_SERVER);
            response.put("data",producto);
            response.put("mensaje",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (producto == null){
            response.put("codigo", Constants.CODIGO_ERROR_LOGICA);
            response.put("data",producto);
            response.put("mensaje",Translator.toLocale(Constants.MENSAJE_ID_INEXISTENTE));
            logger.warn(response);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("codigo", Constants.CODIGO_EXITO);
        response.put("data",producto);
        response.put("mensaje",Translator.toLocale(Constants.MENSAJE_EXITO_ID_ENCONTRADO));
        logger.info(response);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/productos/new")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto){
        Producto productoNuevo = null;
        Map<String,Object> response = new HashMap<>();
        try{
            productoNuevo = productoService.guardarProducto(producto);
        }catch (DataAccessException e){
            response.put("codigo", Constants.CODIGO_ERROR_SERVER);
            response.put("data",productoNuevo);
            response.put("mensaje",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("codigo", Constants.CODIGO_EXITO);
        response.put("data",productoNuevo);
        response.put("mensaje",Translator.toLocale(Constants.MENSAJE_EXITO_PRODUCTO_CREADO));
        logger.info(response);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @PostMapping("/productos/edit")
    public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto){
        Map<String,Object> response = new HashMap<>();

        if (producto == null){
            response.put("codigo", Constants.CODIGO_ERROR_LOGICA);
            response.put("data",producto);
            response.put("mensaje",Translator.toLocale(Constants.MENSAJE_ID_INEXISTENTE));
            logger.warn(response);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }else if (producto.getId() == null){
            response.put("codigo", Constants.CODIGO_ERROR_LOGICA);
            response.put("data",producto);
            response.put("mensaje",Translator.toLocale(Constants.MENSAJE_ID_INEXISTENTE));
            logger.warn(response);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        Producto productoActual = productoService.findProductoById(producto.getId());
        Producto productoUpdated = null;

        if (productoActual == null){
            response.put("codigo", Constants.CODIGO_ERROR_LOGICA);
            response.put("data",productoActual);
            response.put("mensaje",Translator.toLocale(Constants.MENSAJE_ID_INEXISTENTE));
            logger.warn(response);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        try{
            productoActual.setNombre(producto.getNombre());
            productoActual.setMarca(producto.getMarca());
            productoActual.setHechoEn(producto.getHechoEn());
            productoActual.setPrecio(producto.getPrecio());

            productoUpdated = productoService.guardarProducto(productoActual);
        }catch (DataAccessException e){
            response.put("codigo", Constants.CODIGO_ERROR_SERVER);
            response.put("data",productoUpdated);
            response.put("mensaje",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("codigo", Constants.CODIGO_EXITO);
        response.put("data",productoUpdated);
        response.put("mensaje",Translator.toLocale(Constants.MENSAJE_EXITO_PRODUCTO_ACTUALIZADO));
        logger.info(response);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/productos/delete/{id}")
    public ResponseEntity<?> borrarEspecifico(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();

        try {
            productoService.borrarProducto(id);
        }catch (DataAccessException e){
            response.put("codigo", Constants.CODIGO_ERROR_SERVER);
            response.put("data",null);
            response.put("mensaje",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("codigo", Constants.CODIGO_EXITO);
        response.put("data",null);
        response.put("mensaje",Translator.toLocale(Constants.MENSAJE_EXITO_PRODUCTO_ELIMINADO));
        logger.info(response);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/productos/brand/{marca}")
    public ResponseEntity<?> productosMarca(@PathVariable String marca){
        List<Producto> lista = null;
        Map<String,Object> response = new HashMap<>();
        try{
            lista = productoService.findMarca(marca);
        }catch (DataAccessException e){
            response.put("codigo", Constants.CODIGO_ERROR_SERVER);
            lista = new ArrayList<>();
            response.put("data",lista);
            response.put("mensaje",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("codigo", Constants.CODIGO_EXITO);
        response.put("data",lista);
        response.put("mensaje",Translator.toLocale(Constants.MENSAJE_EXITO_ALL));
        logger.info(response);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/productos/precio")
    public ResponseEntity<?> productosPrecioMinimo(@RequestBody PrecioRequest precio){
        List<Producto> lista = null;
        Map<String,Object> response = new HashMap<>();

        try{
            if (precio.getMarca() != null){
                if (precio.getMarca().isBlank()){
                    precio.setMarca(null);
                }
            }

            if (precio.getMarca() == null && precio.getPrecioNecesario() == null){
                lista = productoService.findAllProductos();
            }else if (precio.getMarca() == null){
                lista = productoService.findPrecioAPartir((precio.getPrecioNecesario()));
            }else if (precio.getPrecioNecesario() == null){
                lista = productoService.findMarca(precio.getMarca());
            } else {
                lista = productoService.findMarcaYPrecioAPartir(precio.getMarca(), (precio.getPrecioNecesario()));
            }
        }catch (DataAccessException e){
            response.put("codigo", Constants.CODIGO_ERROR_SERVER);
            lista = new ArrayList<>();
            response.put("data",lista);
            response.put("mensaje",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("codigo", Constants.CODIGO_EXITO);
        response.put("data",lista);
        response.put("mensaje",Translator.toLocale(Constants.MENSAJE_EXITO_ALL));
        logger.info(response);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }
}
