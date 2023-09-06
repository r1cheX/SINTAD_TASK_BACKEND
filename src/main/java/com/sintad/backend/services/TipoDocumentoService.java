package com.sintad.backend.services;

import com.sintad.backend.dataTransferObjects.request.TipoDocumentoRequest;

import java.util.List;

public interface TipoDocumentoService {
    public List<TipoDocumentoRequest> allTipoDocumento();

    public TipoDocumentoRequest oneTipoDocumento(Long id);

    public TipoDocumentoRequest createTipoDocumento(TipoDocumentoRequest tipoDocumentoRequest);

    public TipoDocumentoRequest updateTipoDocumento(TipoDocumentoRequest tipoDocumentoRequest, Long id);

    public void deleteTipoDocumento(Long id);
}
