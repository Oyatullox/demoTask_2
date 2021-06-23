package uz.pdp.democodingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.democodingbat.entity.template.AbsName;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbsName {

    @ManyToOne
    private WebSite webSite;
}
