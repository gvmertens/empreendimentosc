package br.com.mertens.emprendimentosc.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pessoas")
public class Pessoa extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa", nullable = false, precision = 22, scale = 0)
    private Long idPessoa;

    @Column(nullable = false, length = 200)
    private String nomeEmpreendedor;

    @Override
    public Long getId() {
        return this.idPessoa;
    }

    @Override
    public void setId(Long id) {
        this.idPessoa = id;
    }
}
