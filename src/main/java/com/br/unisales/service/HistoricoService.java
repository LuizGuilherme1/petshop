/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.unisales.service;

import com.br.unisales.configuration.ConfigurationManager;
import com.br.unisales.table.Historico;
import com.br.unisales.table.Pet;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Luiz Guilherme
 */
public class HistoricoService {
    private final ConfigurationManager config;

    public HistoricoService(){
        this.config = new ConfigurationManager();
    }
    
    public List<Historico> listar(){
        try {
            TypedQuery<Historico> query = this.config.getEntityManager()
                .createQuery("FROM Historico ORDER BY id", Historico.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
            return new ArrayList<Historico>();
        }
    }
    
    public Historico buscarPorId(Integer id){
        TypedQuery<Historico> query = this.config.getEntityManager()
          .createQuery("FROM Historico WHERE id = :codigo", Historico.class);
        query.setParameter("codigo", id);
        return query.getSingleResult();
    }
    
    public Historico salvar(Integer id, Double peso, Double altura, Date cadastro){
        Historico his = new Historico(id, peso, altura, cadastro);
        this.config.getEntityManager().getTransaction().begin();
            if (id==null) {
                this.config.getEntityManager().persist(his);
            }else{
                this.config.getEntityManager().merge(his);
            }
        this.config.getEntityManager().getTransaction().commit();
        return his;
    }
    
    public String excluir(Integer id){
        if(id != null){
            Historico his = this.buscarPorId(id);
            this.config.getEntityManager().getTransaction().begin();
            this.config.getEntityManager().remove(his);
            this.config.getEntityManager().getTransaction().commit();
            return "ok";
        }
        return "ERROR";
    }
}    
