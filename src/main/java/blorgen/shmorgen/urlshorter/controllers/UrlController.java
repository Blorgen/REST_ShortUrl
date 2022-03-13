package blorgen.shmorgen.urlshorter.controllers;

import blorgen.shmorgen.urlshorter.domain.WrappingsHolder;
import blorgen.shmorgen.urlshorter.repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class UrlController {

    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @PostMapping(path = "/")
    public String wrapUrl(String originalUrl){
        String wrappedUrl = "http://localhost:8080/"+ RandomStringUtils.randomAlphabetic(5);
        // TODO: 13.03.2022 finish
        return wrappedUrl;

    }
    @GetMapping(path = "/{wrappedUrl}")
    public ResponseEntity unwrapUrl(@PathVariable String wrappedUrl){
        WrappingsHolder wrappingsHolder = urlRepository.findByWrappedUrl(wrappedUrl);
        if (wrappedUrl != null){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Location",wrappingsHolder.getOriginalUrl());
            return new ResponseEntity<String>(httpHeaders, HttpStatus.FOUND);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
