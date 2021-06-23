package uz.pdp.democodingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.pdp.democodingbat.entity.template.AbsName;

import javax.persistence.Entity;
@Data
@AllArgsConstructor
@Entity
public class WebSite extends AbsName {
    public WebSite(Integer id, String name) {
        super(id, name);
    }
}
