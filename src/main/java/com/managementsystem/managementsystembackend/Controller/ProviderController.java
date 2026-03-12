package com.managementsystem.managementsystembackend.Controller;

import com.managementsystem.managementsystembackend.DTO.ProviderCreateDTO;
import com.managementsystem.managementsystembackend.DTO.ProviderResponseDTO;
import com.managementsystem.managementsystembackend.DTO.ProviderUpdateDTO;
import com.managementsystem.managementsystembackend.Services.ProviderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/providers")
@AllArgsConstructor
public class ProviderController {
    private final ProviderService providerService;

    @GetMapping
    public ResponseEntity<List<ProviderResponseDTO>> getAllProviders(){
        return ResponseEntity.ok( providerService.getAllProviders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderResponseDTO> getProviderById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(providerService.getProviderById(id));
    }

    @PostMapping
    public ResponseEntity<ProviderResponseDTO> createProvider(
            @Valid @RequestBody ProviderCreateDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.providerService.createProvider(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProviderResponseDTO> updateProvider(
            @PathVariable UUID id,
            @Valid @RequestBody ProviderUpdateDTO dto
    ) {
        return ResponseEntity.ok(this.providerService.updateProvider(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable UUID id) {
        this.providerService.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }
}
