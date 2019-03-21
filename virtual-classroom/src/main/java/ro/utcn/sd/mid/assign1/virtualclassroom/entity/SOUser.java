package ro.utcn.sd.mid.assign1.virtualclassroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "sousers")
@NoArgsConstructor @AllArgsConstructor
public class SOUser implements IDEntity, NameEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sOUsername;
    private String sOPassword;
    //private Boolean banned;

    public SOUser(String username, String password) {
        this.sOUsername = username;
        this.sOPassword = password;
        //this.banned = false;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setName(String name) {
        sOUsername = name;
    }

    @Override
    public String getName() {
        return sOUsername;
    }
}
