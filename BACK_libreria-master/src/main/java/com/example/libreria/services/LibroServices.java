package com.example.libreria.services;

import com.example.libreria.model.Libro;
import com.example.libreria.repo.LibroRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LibroServices {

    @Autowired
    private  LibroRepo libroRepo;
    private final String error = "No se ha encontrado al Libro";
    private CategoriaServices categoriaService;

    public LibroServices(CategoriaServices categoriaService) {
        this.categoriaService = categoriaService;
    }

    public Libro    addLibro(Libro libro){
        return libroRepo.save(libro);
    }

    public List<Libro> findAllLibros(){
        List<Libro> libros = libroRepo.findAll();
        return libros;
    }

    public Libro findLibroById(Long id){
        return libroRepo.findLibroById(id).orElseThrow(() -> new IllegalArgumentException(error));
    }

    public void deleteLibro(Long id){
        libroRepo.deleteLibroById(id);
    }

    public Libro updateLibro(@NotNull Libro libro){
        if (libroRepo.findLibroById(libro.getId()).isPresent()){
            for (int i=0; i<categoriaService.findAllCategorias().size(); i++){
                if(categoriaService.findAllCategorias().get(i).getId()==libro.getCategoria().getId()){
                    libro.setCategoria(categoriaService.findAllCategorias().get(i));
                }
            }
               // libro.setCategoria(categoriaService.findCategoriaById(libro.getCategoria().getId()));
            return libroRepo.save(libro);
        }else{
            throw new IllegalArgumentException("El libro no existe");
        }

    }

    public List<Libro> findLibroByAutorId(String dni){
        return libroRepo.findLibroByAutorDni(dni);
    }

    public List<Libro> findLibroByCategoriaId(Long id){
        return libroRepo.findLibroByCategoriaId(id);
    }
}
