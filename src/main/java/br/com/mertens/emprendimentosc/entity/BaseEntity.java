package br.com.mertens.emprendimentosc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity<PK extends Serializable> implements Serializable {

    @Column(name = "data_inclusao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dataInclusao;

    public abstract PK getId();

    public abstract void setId(PK id);

    @PrePersist
    public void prePersist() {
        this.dataInclusao = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataInclusao = this.dataInclusao == null ? new Date() : this.dataInclusao;
    }
}
