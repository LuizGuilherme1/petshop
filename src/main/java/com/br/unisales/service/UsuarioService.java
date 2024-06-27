package com.br.unisales.service;

import com.br.unisales.table.Usuario;
import com.br.unisales.configuration.ConfigurationManager;
import java.util.List;

import jakarta.persistence.TypedQuery;
import java.util.ArrayList;

public class UsuarioService {
    private ConfigurationManager config;

    public UsuarioService(){
        this.config = new ConfigurationManager();
    }

    public List<Usuario> listar(){
        try {
            TypedQuery<Usuario> query = this.config.getEntityManager()
                .createQuery("FROM Usuario ORDER BY nome", Usuario.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
            return new ArrayList<Usuario>();
        }
    }

    public Usuario buscarPorId(Integer id){
        TypedQuery<Usuario> query = this.config.getEntityManager()
          .createQuery("FROM Usuario WHERE id = :codigo", Usuario.class);
        query.setParameter("codigo", id);
        return query.getSingleResult();
    }

    public List<Usuario> buscarPorNome(String nome){
        TypedQuery<Usuario> query = this.config.getEntityManager()
          .createQuery("FROM Usuario WHERE nome = :codigo", Usuario.class);
        query.setParameter("codigo", nome);
        return query.getResultList();
    }

    public Usuario buscarPorEmail(String email){
        TypedQuery<Usuario> query = this.config.getEntityManager()
          .createQuery("FROM Usuario WHERE email = :codigo", Usuario.class);
        query.setParameter("codigo", email);
        return query.getSingleResult();
    }

    public Usuario salvar(Integer id, String nome, String genero, String email, String senha){
        Usuario user = new Usuario(id, nome, genero, email, senha);
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
            Usuario user = this.buscarPorId(id);
            this.config.getEntityManager().getTransaction().begin();
            this.config.getEntityManager().remove(user);
            this.config.getEntityManager().getTransaction().commit();
            return "ok";
        }
        return "ERROR";
    }
}
