package uz.pdp.democodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.democodingbat.entity.WebSite;
import uz.pdp.democodingbat.payload.ApiResponse;
import uz.pdp.democodingbat.repository.WebSiteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WebSiteService {
    @Autowired
    WebSiteRepository webSiteRepository;

    public List<WebSite> getWebSites() {
        return webSiteRepository.findAll();
    }

    public WebSite getWebSite(Integer id) {
        Optional<WebSite> byId = webSiteRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse addWebSite(WebSite webSite) {
        WebSite newWebSite=new WebSite();
        newWebSite.setName(webSite.getName());
        webSiteRepository.save(newWebSite);
        return new ApiResponse("WenSilte added",true);
    }

    public ApiResponse editWebSite(Integer id, WebSite webSite) {
        WebSite site = webSiteRepository.getById(id);
        site.setName(webSite.getName());
        webSiteRepository.save(site);
        return new ApiResponse("WebSite edited",true);
    }

    public ApiResponse deleteWebSite(Integer id) {
        webSiteRepository.deleteById(id);
        return new ApiResponse("WebSite deleted",true);
    }
}
