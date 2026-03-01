package br.com.mertens.emprendimentosc.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "empreendimentos")
public class Empreendimento extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpreendimento;

    @Column(length = 14, unique = true)
    private String cnpj;

    @Column(nullable = false, length = 200)
    private String nomeEmpreendimento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empreendedor_id", nullable = false)
    private Pessoa empreendedor;

    @Override
    public Long getId() {
        return this.idEmpreendimento;
    }

    @Override
    public void setId(Long id) {
        this.idEmpreendimento = id;
    }
}
