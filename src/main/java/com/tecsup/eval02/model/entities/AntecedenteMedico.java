package com.tecsup.eval02.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "antecedente_medico")
public class AntecedenteMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_historia", nullable = false)
    private HistoriaClinica historia;

    private String tipo;
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    public AntecedenteMedico() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public HistoriaClinica getHistoria() { return historia; }
    public void setHistoria(HistoriaClinica historia) { this.historia = historia; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
