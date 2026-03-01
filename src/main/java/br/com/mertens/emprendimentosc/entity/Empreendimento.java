package br.com.mertens.emprendimentosc.entity;

import br.com.mertens.emprendimentosc.enums.DominioAtivoInativo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "empreendimentos")
@EqualsAndHashCode(of = "idEmpreendimento", callSuper = false)
@Data
public class Empreendimento extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpreendimento;

    @Column(length = 500)
    private String descricao;

    @Column(length = 14, unique = true)
    private String cnpj;

    @Column(nullable = false, length = 255)
    private String nomeEmpreendimento;

    @Column(nullable = false, length = 200)
    private String cidade;

    @Column(nullable = false, length = 255)
    @Email
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empreendedor_id", nullable = false)
    private Pessoa empreendedor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "segmento_id", nullable = false)
    private Segmento segmento;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DominioAtivoInativo status;

    @Override
    public Long getId() {
        return this.idEmpreendimento;
    }

    @Override
    public void setId(Long id) {
        this.idEmpreendimento = id;
    }
}
