package com.cas.casdemo.casservis.service.alias;

import com.cas.casdemo.casservis.dto.alias.AliasGetResponseDTO;
import com.cas.casdemo.casservis.dto.alias.AliasPostRequestDTO;
import com.cas.casdemo.casservis.dto.alias.AliasPutRequestDTO;
import com.cas.casdemo.casservis.entity.accountObject.AccountObject;
import com.cas.casdemo.casservis.entity.alias.Alias;
import com.cas.casdemo.casservis.repository.AccountObjectRepository;
import com.cas.casdemo.casservis.repository.AliasRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AliasService {

    private final AliasRepository aliasRepository;
    private final AccountObjectRepository accountObjectRepository;
    private final ModelMapper modelMapper;

    public AliasService(AliasRepository aliasRepository,
                        AccountObjectRepository accountObjectRepository,
                        ModelMapper modelMapper) {
        this.aliasRepository = aliasRepository;
        this.accountObjectRepository = accountObjectRepository;
        this.modelMapper = modelMapper;
    }


    public Long save(Long accountObjectId, AliasPostRequestDTO dto) {
        Alias alias = modelMapper.map(dto, Alias.class);
        AccountObject owner = accountObjectRepository.findById(accountObjectId)
                .orElseThrow(() -> new EntityNotFoundException("AccountObject not found"));
        alias.setAccountObject(owner);
        return aliasRepository.save(alias).getId();
    }


    public List<AliasGetResponseDTO> findAllByAccountObject(Long accountObjectId) {
        AccountObject owner = accountObjectRepository.findById(accountObjectId)
                .orElseThrow(() -> new EntityNotFoundException("AccountObject not found"));

        return aliasRepository.findAllByAccountObjectId(accountObjectId).stream()
                .map(a -> modelMapper.map(a, AliasGetResponseDTO.class))
                .toList();
    }


    public AliasGetResponseDTO findById(Long id) {
        Alias alias = aliasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alias not found"));
        return modelMapper.map(alias, AliasGetResponseDTO.class);
    }


    public AliasGetResponseDTO update(Long id, AliasPutRequestDTO dto) {
        Alias existing = aliasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alias not found"));

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, existing);

        Alias updated = aliasRepository.save(existing);
        return modelMapper.map(updated, AliasGetResponseDTO.class);
    }


    public void deleteById(Long id) {
        Alias alias = aliasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alias not found"));
        aliasRepository.delete(alias);
    }
}
