package svi;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import svi.service.FilmeService;

@QuarkusTest
public class FilmeResourceTest {

    @Inject
    FilmeService filmeService;

    
}
