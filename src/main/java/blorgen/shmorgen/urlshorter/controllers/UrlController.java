package blorgen.shmorgen.urlshorter.controllers;

import blorgen.shmorgen.urlshorter.domain.WrappingsHolder;
import blorgen.shmorgen.urlshorter.repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping
public class UrlController {

    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @PostMapping(path = "/", consumes = APPLICATION_JSON_VALUE)
    public WrappingsHolder wrapUrl(@RequestBody WrappingsHolder wrappingsHolder){
        String wrappedUrl = RandomStringUtils.randomAlphabetic(5);
        if (wrappingsHolder!=null){
            String urlDecoded = URLDecoder.decode(wrappingsHolder.getOriginalUrl());
            wrappingsHolder = new WrappingsHolder(null,wrappedUrl,urlDecoded);
            return urlRepository.save(wrappingsHolder);
        }else return null;
    }
    @GetMapping(path = "/{wrappedUrl}")
    public ResponseEntity unwrapUrl(@PathVariable("wrappedUrl") String wrappedUrl){
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
