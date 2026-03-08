package com.managementsystem.managementsystembackend.Services;

import com.managementsystem.managementsystembackend.DTO.ProviderCreateDTO;
import com.managementsystem.managementsystembackend.DTO.ProviderResponseDTO;
import com.managementsystem.managementsystembackend.DTO.ProviderUpdateDTO;
import com.managementsystem.managementsystembackend.Entity.Provider;
import com.managementsystem.managementsystembackend.Entity.ProviderStatus;
import com.managementsystem.managementsystembackend.Exceptions.ConflictException;
import com.managementsystem.managementsystembackend.Exceptions.NotFoundException;
import com.managementsystem.managementsystembackend.Repository.ProviderRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Slf4j
@Service
public class ProviderService implements IProviderService {
    private final ProviderRepository providerRepository;

    @Override
    public List<ProviderResponseDTO> getAllProviders() {
        log.info("Getting all providers");
        List<Provider> providers = this.providerRepository.findAll();

        return providers.stream().map(
                provider -> new ProviderResponseDTO(
                        provider.getId(),
                        provider.getName(),
                        provider.getAddress(),
                        provider.getPhone(),
                        provider.getEmail(),
                        provider.getStatus(),
                        provider.getDescription(),
                        provider.getCreatedAt(),
                        provider.getUpdatedAt()
                )
        ).toList();
    }

    @Override
    public ProviderResponseDTO getProviderById(UUID id) {
        log.info("Getting provider by id: {}", id);
        Provider provider = this.providerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Provider not found"));

        return new ProviderResponseDTO(
                provider.getId(),
                provider.getName(),
                provider.getAddress(),
                provider.getPhone(),
                provider.getEmail(),
                provider.getStatus(),
                provider.getDescription(),
                provider.getCreatedAt(),
                provider.getUpdatedAt()
        );
    }

    @Override
    public ProviderResponseDTO createProvider(ProviderCreateDTO dto) {
        var existProvider = this.isProviderExistsByEmail(dto.email());

        if(existProvider){
            throw new ConflictException("Provider already exists with this email: " + dto.email());
        }

        Provider provider = new Provider();
        provider.setName(dto.name());
        provider.setAddress(dto.address());
        provider.setPhone(dto.phone());
        provider.setEmail(dto.email());
        provider.setDescription(dto.description());
        provider.setStatus(ProviderStatus.ACTIVE);

        this.providerRepository.save(provider);
        log.info("Provider created successfully");

        return new ProviderResponseDTO(
                provider.getId(),
                provider.getName(),
                provider.getAddress(),
                provider.getPhone(),
                provider.getEmail(),
                provider.getStatus(),
                provider.getDescription(),
                provider.getCreatedAt(),
                provider.getUpdatedAt()
        );
    }

    @Override
    public ProviderResponseDTO updateProvider(UUID id, ProviderUpdateDTO dto) {
        var existProvider = this.providerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Provider not found"));

        var canUpdateEmail = this.canProviderEmailUpdate(dto.email(), id);
        if(canUpdateEmail)
            throw new ConflictException("Provider already exists with this email: " + dto.email());

        existProvider.setName(dto.name());
        existProvider.setAddress(dto.address());
        existProvider.setPhone(dto.phone());
        existProvider.setEmail(dto.email());
        existProvider.setDescription(dto.description());
        existProvider.setStatus(ProviderStatus.ACTIVE);

        this.providerRepository.save(existProvider);
        log.info("Provider updated successfully");
        return new ProviderResponseDTO(
                existProvider.getId(),
                existProvider.getName(),
                existProvider.getAddress(),
                existProvider.getPhone(),
                existProvider.getEmail(),
                existProvider.getStatus(),
                existProvider.getDescription(),
                existProvider.getCreatedAt(),
                existProvider.getUpdatedAt()
        );
    }

    @Override
    public void deleteProvider(UUID id) {
        var existProvider = this.providerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Provider not found"));

        this.providerRepository.delete(existProvider);
        log.info("Provider deleted successfully");
    }

    private boolean isProviderExistsByEmail(String email){
        return this.providerRepository.existsByEmail(email);
    }

    private boolean canProviderEmailUpdate(String email, UUID id) {
        return this.providerRepository.existsByEmailAndIdNot(email, id);
    }
}
