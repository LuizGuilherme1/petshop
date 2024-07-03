package com.br.unisales.service;

import java.util.ArrayList;
import java.util.List;

import com.br.unisales.configuration.ConfigurationManager;
import com.br.unisales.table.Pet;
import com.br.unisales.table.Proprietario;

import jakarta.persistence.TypedQuery;

public class PetService {
    private final ConfigurationManager config;

    public PetService(){
        this.config = new ConfigurationManager();
    }

    public List<Pet> listar(){
        try {
            TypedQuery<Pet> query = this.config.getEntityManager()
                .createQuery("FROM Pet ORDER BY nome", Pet.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
            return new ArrayList<Pet>();
        }
    }
    
    public List<Pet> achar(Proprietario proprietario){
        //todo
        try {
            TypedQuery<Pet> query = this.config.getEntityManager()
                    .createQuery("FROM Pet WHERE proprietario = :codigo "
                        + "ORDER BY nome", Pet.class);
                query.setParameter("codigo", proprietario);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
            return new ArrayList<Pet>();
        }
    }

    public Pet buscarPorId(Integer id){
        TypedQuery<Pet> query = this.config.getEntityManager()
          .createQuery("FROM Pet WHERE id = :codigo", Pet.class);
        query.setParameter("codigo", id);
        return query.getSingleResult();
    }

    public List<Pet> buscarPorNome(String nome){
        //todo
        TypedQuery<Pet> query = this.config.getEntityManager()
          .createQuery("FROM Pet WHERE nome like :codigo", Pet.class);
        query.setParameter("codigo", nome);
        return query.getResultList();
    }

    public List<Pet> buscarPorProprietario(Proprietario proprietario){
        TypedQuery<Pet> query = this.config.getEntityManager()
          .createQuery("FROM Pet WHERE proprietario = :codigo", Pet.class);
        query.setParameter("codigo", proprietario.getNome());
        return query.getResultList();
    }

    public Pet salvar(Integer id, String nome, Double peso, Double altura, Integer idade, 
            String genero, String especie, String raca, Proprietario proprietario){
        Pet pet = new Pet(id, nome, peso, altura, idade, genero, especie, raca, proprietario);
        this.config.getEntityManager().getTransaction().begin();
            if (id==null) {
                this.config.getEntityManager().persist(pet);
            }else{
                this.config.getEntityManager().merge(pet);
            }
        this.config.getEntityManager().getTransaction().commit();
        return pet;
    }

    public String excluir(Integer id){
        if(id != null){
            Pet pet = this.buscarPorId(id);
            this.config.getEntityManager().getTransaction().begin();
            this.config.getEntityManager().remove(pet);
            this.config.getEntityManager().getTransaction().commit();
            return "ok";
        }
        return "ERROR";
    }

}
