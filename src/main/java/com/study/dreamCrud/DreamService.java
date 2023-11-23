package com.study.dreamCrud;

import com.study.dreamCrud.dto.CreateDream;
import com.study.dreamCrud.dto.UpdateDream;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DreamService {
    private final DreamRepository repository;

    public DreamService(final DreamRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }


    public void deleteById(final Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
    }

    public Dream getById(final Long id) {
        if(!repository.existsById(id)){
            throw new NotFoundException("Dream with id %s was not found".formatted(id));
        }

        return repository.getReferenceById(id);
    }

    public List<Dream> list() {
        return repository.findAll();
    }

    public Dream update(final Long id, final UpdateDream input) {
        if(!repository.existsById(id)){
            throw new NotFoundException("Dream with id %s was not found".formatted(id));
        }

        final Dream dream = repository.getReferenceById(id);

        return repository.save(dream.update(input));
    }

    public Dream create(final CreateDream input){
        return repository.save(new Dream(input.text(), input.tags(), input.type()));
    }
}
