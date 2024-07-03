/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.unisales.service;

import com.br.unisales.configuration.ConfigurationManager;
import com.br.unisales.table.Historico;
import com.br.unisales.table.Pet;
import com.br.unisales.table.Vacinas;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Luiz Guilherme
 */
public class VacinasService {
    private final ConfigurationManager config;

    public VacinasService(){
        this.config = new ConfigurationManager();
    }
    
    public List<Vacinas> listar(){
        try {
            TypedQuery<Vacinas> query = this.config.getEntityManager()
                .createQuery("FROM Vacinas ORDER BY id", Vacinas.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
            return new ArrayList<Vacinas>();
        }
    }
    
    public Vacinas buscarPorId(Integer id){
        TypedQuery<Vacinas> query = this.config.getEntityManager()
          .createQuery("FROM Vacinas WHERE id = :codigo", Vacinas.class);
        query.setParameter("codigo", id);
        return query.getSingleResult();
    }
    
    public List<Vacinas> buscarPorNome(String nome){
        TypedQuery<Vacinas> query = this.config.getEntityManager()
          .createQuery("FROM Vacinas WHERE nome like :codigo", Vacinas.class);
        query.setParameter("codigo", nome);
        return query.getResultList();
    }
    
    public Vacinas salvar(Integer id, String nome, String descricao, Date aplicacao, Date cadastro){
        Vacinas vac = new Vacinas(id, nome, descricao, aplicacao, cadastro);
        this.config.getEntityManager().getTransaction().begin();
            if (id==null) {
                this.config.getEntityManager().persist(vac);
            }else{
                this.config.getEntityManager().merge(vac);
            }
        this.config.getEntityManager().getTransaction().commit();
        return vac;
    }
    
    public String excluir(Integer id){
        if(id != null){
            Vacinas vac = this.buscarPorId(id);
            this.config.getEntityManager().getTransaction().begin();
            this.config.getEntityManager().remove(vac);
            this.config.getEntityManager().getTransaction().commit();
            return "ok";
        }
        return "ERROR";
    }
}    
