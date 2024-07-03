package com.br.unisales.table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vacinas")
public class Vacinas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@one to one
    private Integer id;
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;
    @Column(name = "aplicacao", nullable = false)
    private Date aplicacao; 
    @Column(name = "cadastro", nullable = false)
    private Date cadastro;
}
