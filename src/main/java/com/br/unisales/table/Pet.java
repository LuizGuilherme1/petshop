package com.br.unisales.table;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    @Column(name = "peso", nullable = false)
    private Double peso;
    @Column(name = "altura", nullable = false)
    private Double altura;
    @Column(name = "idade", nullable = false)
    private Integer idade;
    @Column(name = "genero", nullable = false, length = 50)
    private String genero;
    @Column(name = "especie", nullable = false, length = 50)
    private String especie;
    @Column(name = "raca", nullable = false, length = 50)
    private String raca;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_proprietario")
    private Proprietario proprietario;
}
