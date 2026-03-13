package com.managementsystem.managementsystembackend.Services;

import com.managementsystem.managementsystembackend.DTO.ProviderCreateDTO;
import com.managementsystem.managementsystembackend.DTO.ProviderResponseDTO;
import com.managementsystem.managementsystembackend.DTO.ProviderUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface IProviderService {
    List<ProviderResponseDTO> getAllProviders();
    ProviderResponseDTO getProviderById(UUID id);
    ProviderResponseDTO createProvider(ProviderCreateDTO dto);
    ProviderResponseDTO updateProvider(UUID id, ProviderUpdateDTO dto);
    ProviderResponseDTO updateProviderStatus(UUID id);
    void deleteProvider(UUID id);
}
