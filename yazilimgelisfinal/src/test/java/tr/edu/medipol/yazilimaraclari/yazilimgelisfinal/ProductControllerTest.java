package tr.edu.medipol.yazilimaraclari.yazilimgelisfinal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testListele() {
        var response = restTemplate.getForEntity("/urun/", Object[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testEkle() {
        var urun = new ProductController.Urun("YeniUrun", "10.00");
        var response = restTemplate.postForEntity("/urun/", urun, ProductController.Urun.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(urun, response.getBody());
    }

    @Test
    public void testSil() {
        var urun = new ProductController.Urun("Cips", "35.99");
        restTemplate.delete("/urun/{UrunAdi}", urun.UrunAdi());
        
        var response = restTemplate.getForEntity("/urun/", ProductController.Urun[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "HTTP durum kodu kontrolü");
        assertFalse(Arrays.asList(response.getBody()).stream().anyMatch(item -> item.UrunAdi().equals(urun.UrunAdi())));
    }
}

