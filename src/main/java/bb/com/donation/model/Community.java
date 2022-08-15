package bb.com.donation.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "community")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//TODO: implementar cache
public class Community  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Nome da comunidade não pode ficar em branco")
    @NotBlank
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Descrição da comunidade não pode ficar em branco")
    @NotBlank
    @NotEmpty
    @Column(name = "description", nullable = false)
    private String description;


    @OneToMany(mappedBy = "community", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Product> products = new LinkedHashSet<>();

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Community community = (Community) o;
        return id != null && Objects.equals(id, community.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ")";
    }
}