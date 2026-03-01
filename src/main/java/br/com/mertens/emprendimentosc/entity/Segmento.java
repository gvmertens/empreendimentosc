package br.com.mertens.emprendimentosc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "segmentos")
@EqualsAndHashCode(of = "idSegmento", callSuper = false)
@Data
public class Segmento extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSegmento;

    @Column(length = 100)
    private String descricao;

    @Override
    public Long getId() {
        return this.idSegmento;
    }

    @Override
    public void setId(Long id) {
        this.idSegmento = id;
    }
}
