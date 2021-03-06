package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "condominio")
public class Condominio implements Serializable{

    @Id
    @SequenceGenerator(name = "seq_condominio", sequenceName = "seq_condominio_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_condominio", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 40, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 40, nullable = false)
    private String nome;
    
    @NotBlank(message = "O endereço não pode ser em branco")
    @Length(max = 100, message = "O endereço não pode ter mais que {max} caracteres")
    @Column(name = "endereco", length = 100, nullable = false)
    private String endereco;
    
    @NotBlank(message = "O número não pode ser em branco")
    @Length(max = 10, message = "O número não pode ter mais que {max} caracteres")
    @Column(name = "numero", length = 10, nullable = false)
    private String numero;
    
    @NotBlank(message = "O CEP não pode ser em branco")
    @Length(max = 8, message = "O CEP não pode ter mais que {max} caracteres")
    @Column(name = "cep", length = 8, nullable = false)    
    private String cep;
    
    @ManyToMany
    @JoinTable(name = "condominio_recurso",
            joinColumns = 
                    @JoinColumn(name = "condominio", referencedColumnName = "id", 
                            nullable = false),
            inverseJoinColumns = 
                    @JoinColumn(name = "recurso", referencedColumnName = "id", 
                            nullable = false)
            )
    private List<UnidadeCondominal> recursos = new ArrayList<>();
    
    @OneToMany(mappedBy = "condominio", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UnidadeCondominal> unidadesCondominais = new ArrayList<>();
     
    public Condominio() {
        
    }
    
    public void adicionarUnidadeCondominal(UnidadeCondominal obj) {
        obj.setCondominio(this);
        this.getUnidadesCondominais().add(obj);
    }
    
    public void removerUnidadeCondominal(int index) {
        this.unidadesCondominais.remove(index);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<UnidadeCondominal> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<UnidadeCondominal> recursos) {
        this.recursos = recursos;
    }
   
    public List<UnidadeCondominal> getUnidadesCondominais() {
        return unidadesCondominais;
    }

    public void setUnidadescondominais(List<UnidadeCondominal> unidadesCondominais) {
        this.unidadesCondominais = unidadesCondominais;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Condominio other = (Condominio) obj;
        return Objects.equals(this.id, other.id);
    }
}
