package com.br.unisales.table;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proprietario")
public class Proprietario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    @Column(name = "genero", nullable = false, length = 1)
    private String genero;
    @Column(name = "cpf", nullable = false, length = 14, unique = true)
    private String cpf;
    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;
    @Column(name = "celular", nullable = false, length = 15, unique = true)
    private String celular;
    @OneToMany(mappedBy = "nome", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST
    ,CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Pet> ownpet;
}
