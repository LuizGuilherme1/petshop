package com.br.unisales.service;

import java.util.ArrayList;
import java.util.List;

import com.br.unisales.configuration.ConfigurationManager;
import com.br.unisales.table.Pet;
import com.br.unisales.table.Proprietario;

import jakarta.persistence.TypedQuery;

public class ProprietarioService {
    private ConfigurationManager config;

    public ProprietarioService(){
        this.config = new ConfigurationManager();
    }

    public List<Proprietario> listar(){
        try {
            TypedQuery<Proprietario> query = this.config.getEntityManager()
                .createQuery("FROM Proprietario ORDER BY nome", Proprietario.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
            return new ArrayList<Proprietario>();
        }
    }

    public Proprietario buscarPorId(Integer id){
        TypedQuery<Proprietario> query = this.config.getEntityManager()
          .createQuery("FROM Proprietario WHERE id = :codigo", Proprietario.class);
        query.setParameter("codigo", id);
        return query.getSingleResult();
    }

    public List<Proprietario> buscarPorNomes(String nome){
        TypedQuery<Proprietario> query = this.config.getEntityManager()
          .createQuery("FROM Proprietario WHERE nome = :codigo", Proprietario.class);
        query.setParameter("codigo", nome);
        return query.getResultList();
    }
    
    public Proprietario buscarPorNome(String nome){
        TypedQuery<Proprietario> query = this.config.getEntityManager()
          .createQuery("FROM Proprietario WHERE nome = :codigo", Proprietario.class);
        query.setParameter("codigo", nome);
        return query.getSingleResult();
    }

    public Proprietario buscarPorCpf(String cpf){
        TypedQuery<Proprietario> query = this.config.getEntityManager()
          .createQuery("FROM Proprietario WHERE cpf = :codigo", Proprietario.class);
        query.setParameter("codigo", cpf);
        return query.getSingleResult();
    }

    public Proprietario salvar(Integer id, String nome, String genero, String cpf, String email, String celular, List<Pet> pet){
        Proprietario user = new Proprietario(id, nome, genero, cpf, email, celular, pet);
        this.config.getEntityManager().getTransaction().begin();
        if (id==null) {
            this.config.getEntityManager().persist(user);
        }else{
            this.config.getEntityManager().merge(user);
        }
        this.config.getEntityManager().getTransaction().commit();
        return user;
    }

    public String excluir(Integer id){
        if(id != null){
            Proprietario user = this.buscarPorId(id);
            this.config.getEntityManager().getTransaction().begin();
            this.config.getEntityManager().remove(user);
            this.config.getEntityManager().getTransaction().commit();
            return "ok";
        }
        return "ERROR";
    }
}
