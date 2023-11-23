package com.study.dreamCrud;

import com.study.dreamCrud.dto.CreateDream;
import com.study.dreamCrud.dto.DreamResponse;
import com.study.dreamCrud.dto.UpdateDream;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "dreams")
@Tag(name = "Dreams")
public class DreamController {
    private final DreamService service;

    public DreamController(final DreamService service) {
        this.service = Objects.requireNonNull(service);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "List all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<List<DreamResponse>> list() {
        return ResponseEntity.ok(service.list().stream().map(DreamResponse::from).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Cart was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<DreamResponse> getById(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(DreamResponse.from(service.getById(id)));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<Void> deleteById(@PathVariable(name = "id") final Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a dream")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<DreamResponse> create(@RequestBody final CreateDream input) {
        final var output = service.create(input);
        return ResponseEntity.created(URI.create("/dreams/" + output.getId())).body(DreamResponse.from(output));
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a planet by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planet update successfully"),
            @ApiResponse(responseCode = "404", description = "Planet was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<DreamResponse> updateById(@PathVariable(name = "id") final Long id, @RequestBody final UpdateDream input) {
        return ResponseEntity.ok(DreamResponse.from(service.update(id, input)));
    }

}
