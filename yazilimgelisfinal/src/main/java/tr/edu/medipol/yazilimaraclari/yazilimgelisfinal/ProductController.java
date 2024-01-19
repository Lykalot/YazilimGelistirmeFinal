package tr.edu.medipol.yazilimaraclari.yazilimgelisfinal;

import 	org.springframework.web.bind.annotation.*;
import  java.util.*;

@RestController
@RequestMapping("/urun")

	public class ProductController {
		public record Urun(String UrunAdi, String urunFiyati) {} ;
	
		private static final List<Urun> URUN_LISTESI = new ArrayList<>();
				static {
			URUN_LISTESI.add(new Urun("Cips","35.99"));
			URUN_LISTESI.add(new Urun("Kola","55.00"));
			URUN_LISTESI.add(new Urun("Noddle","5.99"));
	}
		@GetMapping("/")
		public List<Urun> listele(){
			return URUN_LISTESI;
		}
		
		@PostMapping("/")
		public Urun Ekle (@RequestBody Urun urun) {
			URUN_LISTESI.add(urun);
			return urun;
		}
		@DeleteMapping("/{UrunAdi}")
	    public boolean sil  (@PathVariable String UrunAdi)
	    {
	        for(Urun urun :URUN_LISTESI) 
	        {
	            if(urun.UrunAdi().equals(UrunAdi)) 
	            {
	                URUN_LISTESI.remove(urun);
	                return true;
	            }
	        }
	        return false;
	    }

}
