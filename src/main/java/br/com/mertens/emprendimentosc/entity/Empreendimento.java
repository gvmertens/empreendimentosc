package br.com.mertens.emprendimentosc.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "empreendimentos")
public class Empreendimento extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpreendimento;

    @Column(nullable = false, length = 200)
    private String nomeEmpreendimento;

    @Column(nullable = false, length = 200)
    private String nomeEmpreendedorResponsavel;

    @JoinColumns({@JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")})
    @ManyToOne(fetch = FetchType.LAZY)
    private Pessoa empreendedorResponsavel;

    @Override
    public Long getId() {
        return this.idEmpreendimento;
    }

    @Override
    public void setId(Long id) {
        this.idEmpreendimento = id;
    }
}
