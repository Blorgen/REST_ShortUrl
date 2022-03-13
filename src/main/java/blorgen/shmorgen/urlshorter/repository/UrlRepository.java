package blorgen.shmorgen.urlshorter.repository;

import blorgen.shmorgen.urlshorter.domain.WrappingsHolder;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<WrappingsHolder, Long> {
    WrappingsHolder findByWrappedUrl(String wrappedUrl);
}
