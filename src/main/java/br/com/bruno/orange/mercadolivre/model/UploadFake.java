package br.com.bruno.orange.mercadolivre.model;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UploadFake {
    public Set<String> enviar(List<MultipartFile> imagens) {
        return imagens.stream().map(imagem -> "http://bucket.io/"
                +imagem.getOriginalFilename()).collect(Collectors.toSet());
    }
}
